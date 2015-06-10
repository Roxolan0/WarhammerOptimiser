package warhammerOptimiser.business;

import static warhammerOptimiser.business.Skill.*;
import static warhammerOptimiser.business.Character.RANK;
import static warhammerOptimiser.business.Characteristic.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.util.Observable;

//TODO various exception throws

public class Character extends Observable {
	public Character() {
		name = "";
		characteristics = new int[NB_CHARACTERISTICS];
		characteristicsFortune = new int[NB_CHARACTERISTICS];
		skills = new int[NB_SKILLS];
		actionCards = new ArrayList<String>();
		actionCards.add("Melee Strike");
		setup(RANK, 0);
	}
	public Character(Character newCharacter) {
		name = newCharacter.name;
		myLevel = newCharacter.myLevel;
		isCustomLevel = newCharacter.isCustomLevel;
		characteristics = newCharacter.characteristics;
		characteristicsFortune = newCharacter.characteristicsFortune;
		skills = newCharacter.skills;
		myStance = newCharacter.myStance;
		levelStance = newCharacter.levelStance;
		optimiseStance = newCharacter.optimiseStance;
		conservativeValue = newCharacter.conservativeValue;
		recklessValue = newCharacter.recklessValue;
		levelEquipment = newCharacter.levelEquipment;
		optimiseEquipment = newCharacter.optimiseEquipment;
		weapon = newCharacter.weapon;
		weaponSecondary = newCharacter.weaponSecondary;
		shield = newCharacter.shield;
		armour = newCharacter.armour;
		allies = newCharacter.allies;
		actionCards = newCharacter.actionCards;
	}
	public Character(int type, int level) {
		this();
		setup(type, level);
	}
	
	public void setupQuickCharacter(String newName, int newRank, Characteristic mainCharacteristic, Stance mainStance) {
		name = newName;
		myLevel = newRank;
		
		if(mainCharacteristic == Characteristic.VERSATILE) {
			setCharacteristics(VERSATILE_CHARACTERISTIC[RANK][newRank]);
			setCharacteristicsFortune(VERSATILE_CHARACTERISTIC_FORTUNE[RANK][newRank]);
		} else if(mainCharacteristic == Characteristic.ALL) {
			setCharacteristics(MAIN_CHARACTERISTIC[RANK][newRank]);
			setCharacteristicsFortune(MAIN_CHARACTERISTIC_FORTUNE[RANK][newRank]);
		} else if(mainCharacteristic == Characteristic.NONE) {
			setCharacteristics(SECONDARY_CHARACTERISTIC[RANK][newRank]);			
			setCharacteristicsFortune(SECONDARY_CHARACTERISTIC_FORTUNE[RANK][newRank]);			
		} else {
			setCharacteristics(SECONDARY_CHARACTERISTIC[RANK][newRank]);
			setCharacteristic(mainCharacteristic, MAIN_CHARACTERISTIC[RANK][newRank]);
			setCharacteristicsFortune(SECONDARY_CHARACTERISTIC_FORTUNE[RANK][newRank]);
			setCharacteristicFortune(mainCharacteristic, MAIN_CHARACTERISTIC_FORTUNE[RANK][newRank]);
		}
		
		setSkills(SKILL[RANK][newRank]);
		
		if(mainStance == Stance.VERSATILE) {
			setStancesValues(VERSATILE_STANCE[RANK][newRank]);
		} else if(mainStance == Stance.ALL) {
			setStancesValues(MAIN_STANCE[RANK][newRank]);
		} else if(mainStance == Stance.NONE) {
			setStancesValues(SECONDARY_STANCE[RANK][newRank]);
		} else if(mainStance == Stance.CONSERVATIVE) {
			setConservativeValue(MAIN_STANCE[RANK][newRank]);
			setRecklessValue(SECONDARY_STANCE[RANK][newRank]);
		} else {
			setConservativeValue(SECONDARY_STANCE[RANK][newRank]);
			setRecklessValue(MAIN_STANCE[RANK][newRank]);
		}
		
		setWeapon(WEAPON[RANK][newRank]);
		setWeaponSecondary(WEAPON[RANK][newRank]);
		setArmour(ARMOUR[RANK][newRank]);
		setShield(SHIELD[RANK][newRank]);
		setAllies(3);
	}
	
	public void setup(int type, int level, boolean stats, boolean stance, boolean equipment) throws IllegalArgumentException {
//		System.out.println("Setting up character of type " + type + " at level " + level + ", "
//				+ "stats = " + stats + ", stance = " + stance + ", equipment = " + equipment);//TODO remove
		myLevel = level;
		if(type != RANK && type != SKULL) {
			throw new IllegalArgumentException("Tried to setup a character of unknown type " + type);
		}
		if(level < 0 || level > MAX[type]) {
			throw new IllegalArgumentException("Tried to setup a character of type " + type + " to illegal level " + level);
		}
		if(stats) {
			setCharacteristics(MAIN_CHARACTERISTIC[type][level]);
			setCharacteristicsFortune(MAIN_CHARACTERISTIC_FORTUNE[type][level]);
			setSkills(SKILL[type][level]);
		}
		if(stance) {
			setConservativeValue(MAIN_STANCE[type][level]);
			setRecklessValue(MAIN_STANCE[type][level]);
		}
		if(equipment) {
			setWeapon(WEAPON[type][level]);
			setWeaponSecondary(WEAPON[type][level]);
			setArmour(ARMOUR[type][level]);
			setShield(SHIELD[type][level]);
		}
		//setChanged();
	}
	public void setup(int type, int level) {
		setup(type, level, true, true, true);
	}
	
	public void changeLevel(int type, int newLevel) {
		myLevel = newLevel;
		isCustomLevel = false;
		setup(type, newLevel, true, levelStance, levelEquipment);
	}
	
	public void setupEquipmentAtLevel() {
		levelEquipment = true;
		optimiseEquipment = false;
		if(!isCustomLevel) {
			setup(RANK, myLevel, false, false, true);
		}
	}
	
	public void setupStanceAtValue(int newStanceValue) {
		conservativeValue = newStanceValue;
		recklessValue = newStanceValue;
		levelStance = true;
		optimiseStance = false;
		if(!isCustomLevel) {
			setup(RANK, myLevel, false, true, false);
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String newName) {
		name = newName;
	}
	
	public int getLevel() {
		return myLevel;
	}
	public void setLevel(int newLevel) {
		myLevel = newLevel;
		//setChanged();
		}
	public boolean isCustomLevel() {
		return isCustomLevel;
	}
	public void setIsCustomLevel(boolean newIsCustomLevel) {
		isCustomLevel = newIsCustomLevel;
		//setChanged();
	}
	
	public int getCharacteristic(Characteristic characteristic) {
		return characteristics[characteristic.getCode()];
	}
	public void setCharacteristics(int newValue) {
		for(int i = 0; i < NB_CHARACTERISTICS; ++i) {
			characteristics[i] = newValue;
		}
		//setChanged();
	}
	public void setCharacteristic(Characteristic characteristic, int newValue) {
		characteristics[characteristic.getCode()] = newValue;
		//setChanged();
	}
	public void incrCharacteristic(Characteristic characteristic) {
		characteristics[characteristic.getCode()]++;
		//setChanged();
	}
	public void decrCharacteristic(Characteristic characteristic) {
		characteristics[characteristic.getCode()]--;
		//setChanged();
	}
	public void addCharacteristic(Characteristic characteristic, int toAdd) {
		if(characteristics[characteristic.getCode()] + toAdd <= 99
				&& characteristics[characteristic.getCode()] + toAdd >= 0) {
			characteristics[characteristic.getCode()] += toAdd;
		}
		//setChanged();
	}
	
	public int getCharacteristicFortune(Characteristic characteristic) {
		return characteristicsFortune[characteristic.getCode()];
	}
	public void setCharacteristicsFortune(int newValue) {
		for(int i = 0; i < NB_CHARACTERISTICS; ++i) {
			characteristicsFortune[i] = newValue;
		}
		//setChanged();
	}
	public void setCharacteristicFortune(Characteristic characteristic, int newValue) {
		characteristicsFortune[characteristic.getCode()] = newValue;
		//setChanged();
	}
	public void addCharacteristicFortune(Characteristic characteristic, int toAdd) {
		if(characteristicsFortune[characteristic.getCode()] + toAdd <= 99
				&& characteristicsFortune[characteristic.getCode()] + toAdd >= 0) {
			characteristicsFortune[characteristic.getCode()] += toAdd;
		}
		//setChanged();
	}
	
	public int getSkill(Skill skill) {
		return skills[skill.getCode()];
	}
	public void setSkills(int newValue) {
		for(int i = 0; i < NB_SKILLS; ++i) {
			skills[i] = newValue;
		}
		//setChanged();
	}
	public void setSkill(Skill skill, int newValue) {
		skills[skill.getCode()] = newValue;
		//setChanged();
	}
	
	public Stance getStance() {
		return myStance;
	}
	public void setMyStance(Stance newStance) {
		myStance = newStance;
		//setChanged();
	}
	public boolean levelStance() {
		return levelStance;
	}
	public void setLevelStance(boolean newLevelStance) {
		levelStance = newLevelStance;
		//setChanged();
	}
	public boolean optimiseStance() {
		return optimiseStance;
	}
	public void setOptimiseStance(boolean newOptimiseStance) {
		optimiseStance = newOptimiseStance;
		//setChanged();
	}
	public int getConservativeValue() {
		return conservativeValue;
	}
	public void setConservativeValue(int newConservativeValue) {
		conservativeValue = newConservativeValue;
		//setChanged();
	}
	public int getRecklessValue() {
		return recklessValue;
	}
	public void setRecklessValue(int newRecklessValue) {
		recklessValue = newRecklessValue;
		//setChanged();
	}
	public void setStancesValues(int newStancesValue) {
		setConservativeValue(newStancesValue);
		setRecklessValue(newStancesValue);
	}
	
	public boolean levelEquipment() {
		return levelEquipment;
	}
	public void setLevelEquipment(boolean newLevelEquipment) {
		levelEquipment = newLevelEquipment;
		//setChanged();
	}
	public boolean optimiseEquipment() {
		return optimiseEquipment;
	}
	public void setOptimiseEquipment(boolean newOptimiseEquipment) {
		optimiseEquipment = newOptimiseEquipment;
		//setChanged();
	}
	public Weapon getWeapon() {
		return weapon;
	}
	public void setWeapon(Weapon newWeapon) {
		weapon = newWeapon;
		//setChanged();
	}
	public Weapon getWeaponSecondary() {
		return weaponSecondary;
	}
	public void setWeaponSecondary(Weapon newWeaponSecondary) {
		weaponSecondary = newWeaponSecondary;
		//setChanged();
	}
	public Shield getShield() {
		return shield;
	}
	public void setShield(Shield newShield) {
		shield = newShield;
		//setChanged();
	}
	public Armour getArmour() {
		return armour;
	}
	public void setArmour(Armour newArmour) {
		armour = newArmour;
		//setChanged();
	}
	
	public int getAllies() {
		return allies;
	}
	public int getGroup() {
		return (allies + 1);
	}
	public void setAllies(int newAllies) {
		this.allies = newAllies;
		//setChanged();
	}
	
	public ArrayList<String> getActionCards() {
		return actionCards;
	}
	public boolean hasActionCard(String cardName) {
		return actionCards.contains(cardName);
	}
	public void addActionCard(String cardName) {
		actionCards.add(cardName);
	}
	public void removeActionCard(String cardName) {
		actionCards.removeAll(Collections.singleton(cardName));
	}
	
	public int normalDamage() {
		return (getCharacteristic(STRENGTH) + weapon.getDr());	//TODO improve
	}
	public int secondaryDamage() {
		return (getCharacteristic(STRENGTH) + weaponSecondary.getDr());	//TODO improve
	}
	public int unarmedDamage() {
		return (getCharacteristic(STRENGTH) + Weapon.UNARMED.getDr());
	}
	public int defence() {
		return (shield.getDefence() + armour.getDefence());
	}
	public int soak() {
		return (shield.getSoak() + armour.getSoak());
	}
	
	private String name;
	
	//Rank/Skull
	int myLevel;
	boolean isCustomLevel;				//true = myLevel doesn't mean anything.
	
	//Stats
	private int[] characteristics;
	private int[] characteristicsFortune;
	
	//Skills
	private int[] skills;
	
	//Stance
	private Stance myStance;
	private boolean levelStance;
	private boolean optimiseStance;
	private int conservativeValue = 0;	//TODO give it an effect
	private int recklessValue = 0;
	
	//Equipment
	private boolean levelEquipment;		//true = equipment is kept at level "myLevel".
	private boolean optimiseEquipment;	//true = equipment will be changed by optimiser functions.
	private Weapon weapon;
	private Weapon weaponSecondary;
	private Shield shield;
	private Armour armour;
	
	//Group
	private int allies = 3;
	
	//Action cards
	private ArrayList<String> actionCards;
	
	//Defaults for a PC/monster at various ranks/skulls
	public static final int RANK = 0;
	public static final int SKULL = 1;
	public static final int MAX[] = {	/*RANK*/ 3, 
										/*SKULL*/ 5};
	public static final String[][] NAME = {	/*RANK*/ {"Goldfish", "Basic", "Intermediate", "Advanced", "Elite", "Heroic"},
											/*SKULL*/ {"Goldfish", "Snotling", "Goblin", "Orc", "Black Orc", "Minotaur", 
														"Vampire", "Dragon Ogre Shaggoth", "Dragon", "Great Unclean One"}};
	//TODO make it legal with character creation rules
	private static final int[][] MAIN_CHARACTERISTIC = {		/*RANK*/ {0,5,5,6}, 
																/*SKULL*/ {0,4,4,5,5,6}};
	private static final int[][] VERSATILE_CHARACTERISTIC = {	/*RANK*/ {0,3,3,4}, 
																/*SKULL*/ {0,3,3,4,4,5}};
	private static final int[][] SECONDARY_CHARACTERISTIC = {	/*RANK*/ {0,2,2,3}, 
																/*SKULL*/ {0,2,3,4,4,5}};
	private static final int[][] MAIN_CHARACTERISTIC_FORTUNE = {		/*RANK*/ {0,0,2,3}, 
																		/*SKULL*/ {0,0,1,1,1,1}};
	private static final int[][] VERSATILE_CHARACTERISTIC_FORTUNE = {	/*RANK*/ {0,0,1,1}, 
																		/*SKULL*/ {0,0,0,0,1,1}};
	private static final int[][] SECONDARY_CHARACTERISTIC_FORTUNE = {	/*RANK*/ {0,0,0,0}, 
																		/*SKULL*/ {0,0,0,0,0,0}};
	private static final int[][] MAIN_STANCE = {		/*RANK*/ {0,3,5,6}, 
														/*SKULL*/ {0,1,1,2,2,3}};
	private static final int[][] VERSATILE_STANCE = {	/*RANK*/ {0,2,3,4}, 
														/*SKULL*/ {0,1,1,1,1,1}};
	private static final int[][] SECONDARY_STANCE = {	/*RANK*/ {0,1,2,2}, 
														/*SKULL*/ {0,0,0,0,0,0}};
	private static final int[][] SKILL = {	/*RANK*/ {0,1,2,3}, 
											/*SKULL*/ {0,0,0,0,0,0}};
	private static final Weapon[][] WEAPON = {	/*RANK*/ {Weapon.NOTHING, Weapon.HAND_WEAPON, Weapon.HAND_WEAPON, Weapon.HAND_WEAPON}, 
												/*SKULL*/ {Weapon.NOTHING, Weapon.NOTHING, Weapon.NOTHING, 
															Weapon.NOTHING, Weapon.NOTHING, Weapon.NOTHING}};
	private static final Armour[][] ARMOUR = {	/*RANK*/ {Armour.NOTHING, Armour.BRIGANDINE, Armour.CHAINMAIL, Armour.BREASTPLATE}, 
												/*SKULL*/ {Armour.NOTHING, new Armour(1,1), new Armour(2,1), 
															new Armour(3,1), new Armour(4,2), new Armour(4,2)}};
	private static final Shield[][] SHIELD = {	/*RANK*/ {Shield.NOTHING, Shield.ROUND, Shield.ROUND, Shield.TOWER}, 
												/*SKULL*/ {Shield.NOTHING, Shield.NOTHING, Shield.NOTHING, 
															Shield.NOTHING, Shield.NOTHING, Shield.NOTHING}};
}