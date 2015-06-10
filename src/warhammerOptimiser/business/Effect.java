package warhammerOptimiser.business;

import java.io.Serializable;

public class Effect implements Serializable {
	private static final long serialVersionUID = -6839621406813120966L;
	
	public Effect(int newTargetType, int newResultType, Amount newAmount, int newDuration, boolean newNeedsSuccess) 
	throws IllegalArgumentException {
		if(!TargetType.isValid(newTargetType)) {
			throw new IllegalArgumentException("Tried to create effect with targetType " + newTargetType);
		}
		if(!ResultType.isValid(newResultType)) {
			throw new IllegalArgumentException("Tried to create effect with resultType " + newResultType);
		}
		if(newDuration < 1) {
			throw new IllegalArgumentException("Tried to create effect with duration " + newDuration);
		}
		targetType = newTargetType;
		resultType = newResultType;
		amount = new Amount(newAmount);
		duration = newDuration;
		needsSuccess = newNeedsSuccess;
	}
//	public Effect(int newTargetType, int newResultType, Amount newAmount, int newDuration) {
//		this(newTargetType, newResultType, newAmount, newDuration, false);
//	}
//	public Effect(int newTargetType, int newResultType, Amount newAmount, boolean newNeedsSuccess) {
//		this(newTargetType, newResultType, newAmount, 1, newNeedsSuccess);
//	}
	public Effect(int newTargetType, int newResultType, Amount newAmount) {
		this(newTargetType, newResultType, newAmount, 1, false);
	}
//	public Effect(int newTargetType, int newResultType, boolean newNeedsSuccess) {
//		this(newTargetType, newResultType, new Amount(1), 1, newNeedsSuccess);
//	}
	public Effect(int newTargetType, int newResultType) {
		this(newTargetType, newResultType, new Amount(1), 1, false);
	}
	
	public int getTargetType() {
		return targetType;
	}
	public int getResultType() {
		return resultType;
	}
	public Amount getAmount() {
		return amount;
	}
	public int getDuration() {
		return duration;
	}
	public boolean needsSuccess() {
		return needsSuccess;
	}
	public int getUtility(boolean hasSuccess, Character character, Character enemy, ResultTypeUtility utilityTable) {
		if(resultType == ResultType.CRITICAL && !hasSuccess) {
			return utilityTable.getUtility(targetType, ResultType.WOUND) * amount.total(character, enemy) * duration;
		} else {
			return utilityTable.getUtility(targetType, resultType) * amount.total(character, enemy) * duration;
		}
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Effect)) return false;
		Effect other = (Effect) o;
//		if(!(this.amount == other.amount)) System.out.println("'"+this.getAmount().toString() + "' '" + other.getAmount().toString()+"'");
		return this.targetType == other.targetType
			&& this.resultType == other.resultType
			&& this.amount.equals(other.amount)
			&& this.duration == other.duration;
	}
	

	public String toString() {
		return toCardString();
	}
public String toCardString() {
		String s = "";
		if(needsSuccess) {
			s += "+";
		}
		s += TargetType.name(targetType) + " ";
		if(!amount.toCardString().equals("1")) {
			s += amount.toCardString() + " ";
		}
		s += ResultType.name(resultType);
		if(duration != 1) {
			s += " " + duration + " rounds";
		}
		return s;
	}
	
	private int targetType;
	private int resultType;
	private Amount amount;
	private int duration;
	private boolean needsSuccess;
}
