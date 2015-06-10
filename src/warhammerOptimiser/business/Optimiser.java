package warhammerOptimiser.business;

import static warhammerOptimiser.business.Character.*;
import static warhammerOptimiser.business.Characteristic.*;
import static warhammerOptimiser.business.DiceType.*;
import static warhammerOptimiser.business.ActionLibrary.*;
import static warhammerOptimiser.business.Skill.*;
import static warhammerOptimiser.business.TargetType.*;
import static warhammerOptimiser.business.ResultType.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import com.roxolan.warhammeroptimiserapp.AppFileManager;

import android.content.Context;
import static warhammerOptimiser.business.Stance.*;
import static com.roxolan.warhammeroptimiserapp.AppFileManager.*;

/**
 * A collection of static methods to do optimisation calculations.
 * @author H
 */
/**
 * @author H
 *
 */
/**
 * @author H
 *
 */
/**
 * @author H
 *
 */
/**
 * @author H
 *
 */
/**
 * @author H
 *
 */
public class Optimiser {
	private Optimiser() {}	//Fully static class, should never be instantiated
	
	/**
	 * Creates a full Action from its String name.
	 * @param name The String name of the action (must exist as a .txt in the card folder).
	 * @param character The character using the action, used to add the "critical hit" line.
	 * @return The action card as an Action.
	 * @throws IOException 
	 */
	public static Action setupAction(Context context, String cardName, Character character) throws IOException {
		BufferedReader reader = cardReader(context, cardName);
		Action action = new Action(reader);
		action.addAllEffectLines(ActionLibrary.UNIVERSAL_ATTACK);
		action.addEffectLine(ActionLibrary.weaponCritical(character.getWeapon()));
		return action;
	}
	
	/**
	 * Creates the list of possible results of the dice pool inferred from an attack on an enemy.
	 * @param character The attacking Character.
	 * @param enemy The defending Character.
	 * @return A ResultPoolList of all possible results.
	 */
	public static ResultPoolList buildResultPoolList(Character character, Character enemy) {
		ResultPoolList r = new ResultPoolList();
		r.addDice(EXP, character.getSkill(WEAPON_SKILL));
		r.addDice(FOR, character.getCharacteristicFortune(STRENGTH));
		
		Dice stanceDice;
		int stanceValue;
		if(character.getStance() == CONSERVATIVE) {
			stanceDice = CON;
			stanceValue = character.getConservativeValue();
		} else {
			stanceDice = REC;
			stanceValue = character.getRecklessValue();
		}
		int nbStanceDice = Math.min(stanceValue, character.getCharacteristic(STRENGTH));
		int nbCharDice = character.getCharacteristic(STRENGTH) - nbStanceDice;
		r.addDice(stanceDice, nbStanceDice);
		r.addDice(CHA, nbCharDice);
		
		if(character.getWeapon().hasFor()) {
			r.addDice(FOR);
		}
		if(character.getWeapon().hasMis()) {
			r.addDice(MIS);
		}
		
		r.addDice(CHL);
		r.addDice(MIS, enemy.defence());
		return r;
	}
	
	/**
	 * Calculates the maximal utility of an Action given a specific ResultPool.
	 * @param pool The ResultPool used.
	 * @param action The Action used.
	 * @param character The attacking Character.
	 * @param enemy The defending Character.
	 * @param utility The utility given to every possible ResultType.
	 * @return The maximal utility.
	 */
	public static int calculateUtility(ResultPool pool, Action action, Character character, Character enemy, ResultTypeUtility utility) {
		int total = 0;
		Action anAction = new Action(action);
		EffectLine success = anAction.takeBestEffectLine(character.getStance(), EffectType.S, pool.getS(), false, character, enemy, utility);
		boolean hasSuccess = (success != null);
		if(hasSuccess) {
			total += success.getTotalUtility(hasSuccess, character, enemy, utility);
		}
		for(EffectType effectType : EffectType.values()) {		//S,O,A,C,H
			if(effectType != EffectType.S){		//TODO rendre plus élégant
				int nbEffect = pool.getEffectType(effectType);
				EffectLine curEffect = anAction.takeBestEffectLine(character.getStance(), effectType, nbEffect, hasSuccess, character, enemy, utility);
				while(curEffect != null && nbEffect > 0) {
					total += curEffect.getTotalUtility(hasSuccess, character, enemy, utility);
					nbEffect -= curEffect.getCost().getNb();
					curEffect = anAction.takeBestEffectLine(character.getStance(), effectType, nbEffect, hasSuccess, character, enemy, utility);
				}
			}
		}
		
		if(pool.getC() > 0) {
			pool.setC(pool.getC() - 1);
			pool.setS(pool.getS() + 1);
			pool.balance();
			total = Math.max(total, calculateUtility(pool, action, character, enemy, utility));
			pool.setS(pool.getS() - 1);
			pool.setO(pool.getO() + 1);
			pool.balance();
			total = Math.max(total, calculateUtility(pool, action, character, enemy, utility));
			pool.setO(pool.getO() - 1);
			pool.setC(pool.getC() + 1);
			pool.balance();
		}
		if(pool.getH() > 0) {
			pool.setH(pool.getH() - 1);
			pool.setA(pool.getA() + 1);
			pool.balance();
			total = Math.min(total, calculateUtility(pool, action, character, enemy, utility));
			pool.setA(pool.getA() - 1);
			pool.setH(pool.getH() + 1);
			pool.balance();
		}
		return total;
	}
	
	/**
	 * Calculates the average utility of an Action over each ResultPool in a ResultPoolList.
	 * @param resultPoolList The ResultPools to be tested.
	 * @param action The Action used.
	 * @param character The attacking Character.
	 * @param enemy The defending Character.
	 * @param utility The utility given to every possible ResultType.
	 * @return
	 */
	public static double averageUtility(ResultPoolList resultPoolList, Action action, Character character, Character enemy, ResultTypeUtility utility) {
		System.out.println("averageUtility for resultPoolList of size " + resultPoolList.size() 
				+ " for action " + action.getTitle() 
				+ " with rank " + character.getLevel() + " and skull " + enemy.getLevel() 
				+ ", utility of damage = " + utility.getUtility(DEAL, DAMAGE));
		double total = 0;
		if(character.optimiseEquipment()) {
			int nbWeapons = WeaponEnum.values().length;
			int nbShields = ShieldEnum.values().length;
			int nbArmours = ArmourEnum.values().length;
			double[][][] utilityForEquippedCharacter = new double[nbArmours][nbWeapons][nbWeapons + nbShields];
			Character anEquippedCharacter = new Character(character);
			anEquippedCharacter.setOptimiseEquipment(false);
			for(int i = 0; i < nbArmours; ++i) {
				anEquippedCharacter.setArmour(ArmourEnum.values()[i].armour());
				for(int j = 0; j < nbWeapons; ++j) {
					anEquippedCharacter.setWeapon(WeaponEnum.values()[j].weapon());
					for(int k = 0; k < nbWeapons; ++k) {
						anEquippedCharacter.setWeaponSecondary(WeaponEnum.values()[k].weapon());
						anEquippedCharacter.setShield(Shield.NOTHING);
						utilityForEquippedCharacter[i][j][k] = averageUtility(resultPoolList, action, anEquippedCharacter, enemy, utility);
						System.out.println(((double)Math.round(utilityForEquippedCharacter[i][j][k] * 100000) / 100000)
										+ " " + anEquippedCharacter.getWeapon().getName()
										+ " " + anEquippedCharacter.getWeaponSecondary().getName()
										+ " " + anEquippedCharacter.getShield().getName()
										+ " " + anEquippedCharacter.getArmour().getName());
					}
					for(int k = 0; k < nbShields; ++k) {
						anEquippedCharacter.setWeaponSecondary(Weapon.NOTHING);
						anEquippedCharacter.setShield(ShieldEnum.values()[k].shield());
						utilityForEquippedCharacter[i][j][k + nbWeapons] = averageUtility(resultPoolList, action, anEquippedCharacter, enemy, utility);
						utilityForEquippedCharacter[i][j][k] = averageUtility(resultPoolList, action, anEquippedCharacter, enemy, utility);
						System.out.println(((double)Math.round(utilityForEquippedCharacter[i][j][k] * 100000) / 100000)
								+ " " + anEquippedCharacter.getWeapon().getName()
								+ " " + anEquippedCharacter.getWeaponSecondary().getName()
								+ " " + anEquippedCharacter.getShield().getName()
								+ " " + anEquippedCharacter.getArmour().getName());
					}
				}
			}
			int[] maxPosition = maxPositionOfMatrix(utilityForEquippedCharacter);
			character.setArmour(ArmourEnum.values()[maxPosition[0]].armour());
			character.setWeapon(WeaponEnum.values()[maxPosition[1]].weapon());
			if(maxPosition[2] < nbWeapons) {
				character.setWeaponSecondary(WeaponEnum.values()[maxPosition[2]].weapon());
				character.setShield(Shield.NOTHING);
			} else {
				character.setWeaponSecondary(Weapon.NOTHING);
				character.setShield(ShieldEnum.values()[maxPosition[2] - nbWeapons].shield());
			}
			total = utilityForEquippedCharacter[maxPosition[0]][maxPosition[1]][maxPosition[2]];
		}
		else {
			for(int i = 0; i < resultPoolList.size(); ++i) {
				total += resultPoolList.getNb(i) * calculateUtility(resultPoolList.getPool(i), action, character, enemy, utility);
			}
			total /= resultPoolList.totalNb();
		}
		return total;
	}
	/**
	 * Calculates the average utility of an action (inferred from its String name) given an attacker and an enemy.
	 * @param context The Activity context (used to get at the cards stored in the "assets" app folder.
	 * @param actionName The String name of an action (must exist as a .txt in the card folder).
	 * @param character The attacking Character.
	 * @param enemy The defending Character.
	 * @param utility The utility given to every possible ResultType.
	 * @return the average utility of the action
	 * @throws IOException if there was a problem reading the actionCard's .txt file.
	 */
	public static double averageUtility(Context context, String actionName, Character character, Character enemy, ResultTypeUtility utility) throws IOException {
		return averageUtility(buildResultPoolList(character, enemy), setupAction(context, actionName, character), character, enemy, utility);
	}
	
	private static int[] maxPositionOfMatrix(double[][][] matrix) {
		double max = matrix[0][0][0];
		int[] maxPosition = {0,0,0};
		for(int i = 0; i < matrix.length; ++i)
			for(int j = 0; j < matrix[i].length; ++j)
				for(int k = 0; k < matrix[i][j].length; ++k)
					if(matrix[i][j][k] > max) {
						max = matrix[i][j][k];
						maxPosition[0] = i;
						maxPosition[1] = j;
						maxPosition[2] = k;
					}
		return maxPosition;
	}
	
	/**
	 * Finds the name of the action cards with the highest expected utility.
	 * @param context The Activity context (used to get at the cards stored in the "assets" app folder.
	 * @param nbCards The number of cards to find ("top N cards").
	 * @param character The attacking character.
	 * @param enemy The defending character.
	 * @param utility The utility given to every possible ResultType.
	 * @return an ArrayList of the names of the action cards with the highest expected utility.
	 * @throws IOException if there was a problem reading the actionCard's .txt file.
	 */
	public static ArrayList<NameUtilityPair> bestActionCards(Context context, int nbCards, Character character, Character enemy, ResultTypeUtility utility) throws IOException {
		ArrayList<String> allCardNames = AppFileManager.getAllCardNames(context);
//		String bestActionCards[] = new String[nbCards];
//		int averageUtilities[] = new int[nbCards];
		ArrayList<NameUtilityPair> allCardsWithUtility = new ArrayList<NameUtilityPair>();
				
		for(int i = 0; i < allCardNames.size(); ++i) {
			String cardName = allCardNames.get(i);
			double cardUtility = averageUtility(context, cardName, character, enemy, utility);
			NameUtilityPair cardUtilityPair = new NameUtilityPair(cardName, cardUtility);
			allCardsWithUtility.add(cardUtilityPair);
		}
		
		Collections.sort(allCardsWithUtility, new Comparator<NameUtilityPair>() {
			public int compare(NameUtilityPair pair1, NameUtilityPair pair2) {
				return Double.compare(pair1.getUtility(), pair2.getUtility());
			}
		});
		Collections.reverse(allCardsWithUtility);
		
		ArrayList<NameUtilityPair> bestCards = new ArrayList<NameUtilityPair>();
		for(int i = 0; i < nbCards && i < allCardsWithUtility.size(); ++i) {
			bestCards.add(allCardsWithUtility.get(i));
		}
		
		return bestCards;
	}
}
