package warhammerOptimiser.business;
import static warhammerOptimiser.business.ResultType.*;
import static warhammerOptimiser.business.TargetType.*;

public class ResultTypeUtility {

	public ResultTypeUtility() {
		utility = new int[TARGET_NB][RESULT_NB];		//TODO improve?
		setupValue(999);
	}
	
	public void setupValue(int nb) {
		for(int i = 0; i < RESULT_NB; ++i) {
			utility[GAIN][i] = nb;
			utility[SUFFER][i] = -nb;
			utility[FACE][i] = -nb;
			utility[DEAL][i] = nb;
		}
	}
	
	public void adjustDamage() {
		for(int i = 0; i < (SPECIAL + 1); ++i) {
			utility[GAIN][i] = 0;
			utility[SUFFER][i] = 0;
			utility[FACE][i] = 0;
			if(i != CRITICAL
					&& i != DAMAGE
					&& i != FATIGUE
					&& i != OBEDIENCE
					&& i != STRESS
					&& i != WOUND
					//&& i != SOAK
					) {
				utility[DEAL][i] = 0;
			}
		}
	}
	public void adjustMonster() {
		utility[DEAL][FATIGUE] = utility[DEAL][WOUND];
		utility[DEAL][OBEDIENCE] = utility[DEAL][WOUND];
		utility[DEAL][STRESS] = utility[DEAL][WOUND];
	}
	
	public void setupRegular() {
		utility[DEAL][BLINDED] = 20;
		utility[DEAL][COWED] = 2;
		utility[DEAL][CRITICAL] = 23;
		utility[DEAL][DAMAGE] = 10;
		utility[DEAL][DISENGAGE] = 3;
		utility[DEAL][DROP_WEAPON] = 10;
		utility[DEAL][EXPOSED] = 10;
		utility[DEAL][FATIGUE] = 9;
		utility[DEAL][IMMOBILIZED] = 4;
		utility[DEAL][MISFORTUNE] = 5;
		utility[DEAL][PRONE] = 8;
		utility[DEAL][RATTLED] = 2;
		utility[DEAL][REDUCE_SOAK] = 26;
		utility[DEAL][SPECIAL] = 1;
		utility[DEAL][STAGGERED] = 5;
		utility[DEAL][STRESS] = 8;
		utility[DEAL][SLUGGISH] = 9;
		utility[DEAL][WOUND] = 10;
		
		utility[FACE][DISENGAGE] = -3;
		utility[FACE][DAMAGE] = -10;
		utility[FACE][FORTUNE] = -5;
		utility[FACE][SPECIAL] = -1;
		
		utility[GAIN][EXPERTISE] = 8;
		utility[GAIN][FATIGUE] = 7;
		utility[GAIN][SPECIAL] = 1;
		utility[GAIN][STANCE] = 2;
		utility[GAIN][STRESS] = 6;
		utility[GAIN][FORTUNE] = 5;
		utility[GAIN][DISENGAGE] = 4;
		utility[GAIN][ENGAGE] = 4;
		utility[GAIN][MANOEUVRE] = 9;
		utility[GAIN][MOVEMENT] = 6;
		utility[GAIN][RECHARGE_ANY] = 5;
		utility[GAIN][RECHARGE_DEFENCE] = 4;
		utility[GAIN][RECHARGE_SPECIFIC] = 4;
		utility[GAIN][RECHARGE_THIS] = 4;
		
		utility[SUFFER][CRITICAL] = -25;
		utility[SUFFER][DROP_SHIELD] = -7;
		utility[SUFFER][DROP_WEAPON] = -9;
		utility[SUFFER][EXPOSED] = -10;
		utility[SUFFER][FATIGUE] = -8;
		utility[SUFFER][MISFORTUNE] = -5;
		utility[SUFFER][NEUTRAL] = -7;
		utility[SUFFER][OBEDIENCE] = 6;
		utility[SUFFER][RECHARGE_ANY] = -4;
		utility[SUFFER][RECHARGE_DEFENCE] = -4;
		utility[SUFFER][RECHARGE_THIS] = -3;
		utility[SUFFER][SLUGGISH] = -7;
		utility[SUFFER][SPECIAL] = -1;
		utility[SUFFER][STRESS] = -7;
		utility[SUFFER][WOUND] = -10;
	}
	
	public void setupDamage() {
		setupValue(0);
		utility[DEAL][DAMAGE] = 1;
		utility[DEAL][WOUND] = 1;
		utility[DEAL][CRITICAL] = 2;
	}
	public int getUtility(int target, int result) throws IllegalArgumentException {
		if(target < 0 || target >= TARGET_NB || result < 0 || result >= RESULT_NB) {
			throw new IllegalArgumentException("Tried to get utility [" + target + "|" + result + "]");
		}
		if(utility[target][result] == 999) {	//TODO make more elegant?
			System.out.println("Utility [" + TargetType.name(target) + "|" + ResultType.name(result) + "] has not been defined. Defaulting to 10.");
			return 10;
		}
		if(utility[target][result] == -999) {
			System.out.println("Utility [" + TargetType.name(target) + "|" + ResultType.name(result) + "] has not been defined. Defaulting to -10.");
			return -10;
		}
		return utility[target][result];
	}
	public void setUtility(int target, int result, int value) {
		utility[target][result] = value;
	}
	
	private int[][] utility;

}
