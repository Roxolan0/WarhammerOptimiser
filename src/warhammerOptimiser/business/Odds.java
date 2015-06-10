package warhammerOptimiser.business;
import static warhammerOptimiser.business.DiceType.*;
import warhammerOptimiser.business.EffectType;

public class Odds {
	
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
	
	public static double totalUtility(ResultPoolList resultPoolList, Action action, Character character, Character enemy, ResultTypeUtility utility) {
		double total = 0;
		for(int i = 0; i < resultPoolList.size(); ++i) {
			total += resultPoolList.getNb(i) * calculateUtility(resultPoolList.getPool(i), action, character, enemy, utility);
		}
		total /= resultPoolList.totalNb();
		return total;
	}
}
