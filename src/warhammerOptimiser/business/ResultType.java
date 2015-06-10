package warhammerOptimiser.business;

import java.io.Serializable;

public class ResultType implements Serializable {
	private static final long serialVersionUID = 2686275265543930325L;

	public static final int RESULT_NB = 100;
	
	//Damage types
	public static final int DAMAGE = 1;
	public static final int WOUND = 2;
	public static final int CRITICAL = 3;
	public static final int INSANITY = 4;
	public static final int FATIGUE = 5;
	public static final int STRESS = 6;
	public static final int OBEDIENCE = 25;
	
	//Conditions
	public static final int BLINDED = 7;
	public static final int COWED = 31;
	public static final int EXPOSED = 8;
	public static final int RATTLED = 27;
	public static final int SLUGGISH = 9;
	public static final int STAGGERED = 10;
	
	//Dice
	public static final int FORTUNE = 11;
	public static final int MISFORTUNE = 12;
	public static final int EXPERTISE = 13;
	
	//Manoeuvre
	public static final int MANOEUVRE = 14;
	public static final int DISENGAGE = 15;
	public static final int MOVEMENT = 16;
	public static final int PRONE = 17;
	public static final int DROP_WEAPON = 18;
	public static final int DROP_SHIELD = 19;
	public static final int ENGAGE = 30;
	
	//Recharge
	public static final int RECHARGE_ANY = 20;
	public static final int RECHARGE_DEFENCE = 24;
	public static final int RECHARGE_SPECIFIC = 21;
	public static final int RECHARGE_THIS = 22;
	
	//Stat buff/debuff, fake conditions
	public static final int IMMOBILIZED = 26;
	public static final int NEUTRAL = 28;
	public static final int REDUCE_SOAK = 23;
	public static final int STANCE = 29;
	
	//Others
	public static final int SPECIAL = 32;
	
	public static boolean isValid(int resultType) {
		return(resultType > 0 && resultType <= SPECIAL);
	}
	
	public static String name(int resultType) throws IllegalArgumentException {
		String s = "";
		if(resultType == BLINDED) {
			s += "blind";
		} else if(resultType == COWED) {
			s += "cowed";
		} else if(resultType == CRITICAL) {
			s += "critical";
		} else if(resultType == DAMAGE) {
			s += "damage";
		} else if(resultType == DISENGAGE) {
			s += "disengage";
		} else if(resultType == DROP_SHIELD) {
			s += "drop_shield";
		} else if(resultType == DROP_WEAPON) {
			s += "drop_weapon";
		} else if(resultType == ENGAGE) {
			s += "engage";
		} else if(resultType == EXPERTISE) {
			s += "expertise";
		} else if(resultType == EXPOSED) {
			s += "exposed";
		} else if(resultType == FATIGUE) {
			s += "fatigue";
		} else if(resultType == FORTUNE) {
			s += "fortune";
		} else if(resultType == IMMOBILIZED) {
			s += "immobilized";
		} else if(resultType == INSANITY) {
			s += "insanity";
		} else if(resultType == MANOEUVRE) {
			s += "manoeuvre";
		} else if(resultType == MISFORTUNE) {
			s += "misfortune";
		} else if(resultType == MOVEMENT) {
			s += "movement";
		} else if(resultType == NEUTRAL) {
			s += "neutral";
		} else if(resultType == OBEDIENCE) {
			s += "obedience";
		} else if(resultType == PRONE) {
			s += "prone";
		} else if(resultType == RATTLED) {
			s += "rattled";
		} else if(resultType == RECHARGE_ANY) {
			s += "recharge_any";
		} else if(resultType == RECHARGE_DEFENCE) {
			s += "recharge_defence";
		} else if(resultType == RECHARGE_SPECIFIC) {
			s += "recharge_specific";
		} else if(resultType == RECHARGE_THIS) {
			s += "recharge_this";
		} else if(resultType == REDUCE_SOAK) {
			s += "reduce_soak";
		} else if(resultType == SLUGGISH) {
			s += "sluggish";
		} else if(resultType == SPECIAL) {
			s += "SPECIAL";
		} else if(resultType == STAGGERED) {
			s += "staggered";
		} else if(resultType == STANCE) {
			s += "stance";
		} else if(resultType == STRESS) {
			s += "stress";
		} else if(resultType == WOUND) {
			s += "wound";
		} else {
//			s += "[unknown_" + resultType + "]";
			throw new IllegalArgumentException("Tried to get name of ResultType " + resultType + ".");
		}
		return s;
	}

	public static int parseResultType(String s1) {
		if(s1.toLowerCase().startsWith("bli")) {
			return BLINDED;
		} else if(s1.toLowerCase().startsWith("cow")) {
			return COWED;
		} else if(s1.toLowerCase().startsWith("cri")) {
			return CRITICAL;
		} else if(s1.toLowerCase().startsWith("dam") 
				|| s1.toLowerCase().startsWith("dmg")) {
			return DAMAGE;
		} else if(s1.toLowerCase().startsWith("dis")) {
			return DISENGAGE;
		} else if(s1.toLowerCase().startsWith("dro_shi") 
				|| s1.toLowerCase().startsWith("dro_shl") 
				|| s1.toLowerCase().startsWith("drop_shi")
				|| s1.toLowerCase().startsWith("drop_shl")) {
			return DROP_SHIELD;
		} else if(s1.toLowerCase().startsWith("dro_wea") 
				|| s1.toLowerCase().startsWith("dro_wpn") 
				|| s1.toLowerCase().startsWith("drop_wea")
				|| s1.toLowerCase().startsWith("drop_wpn")) {
			return DROP_WEAPON;
		} else if(s1.toLowerCase().startsWith("eng")) {
			return ENGAGE;
		} else if(s1.toLowerCase().startsWith("expe")) {
			return EXPERTISE;
		} else if(s1.toLowerCase().startsWith("expo")) {
			return EXPOSED;
		} else if(s1.toLowerCase().startsWith("fat")) {
			return FATIGUE;
		} else if(s1.toLowerCase().startsWith("for")) {
			return FORTUNE;
		} else if(s1.toLowerCase().startsWith("imm")) {
			return IMMOBILIZED;
		} else if(s1.toLowerCase().startsWith("ins")) {
			return INSANITY;
		} else if(s1.toLowerCase().startsWith("man") 
				|| s1.toLowerCase().startsWith("mnv")) {
			return MANOEUVRE;
		} else if(s1.toLowerCase().startsWith("mis")) {
			return MISFORTUNE;
		} else if(s1.toLowerCase().startsWith("mov")) {
			return MOVEMENT;
		} else if(s1.toLowerCase().startsWith("neu")) {
			return NEUTRAL;
		} else if(s1.toLowerCase().startsWith("obe")) {
			return OBEDIENCE;
		} else if(s1.toLowerCase().startsWith("pro")) {
			return PRONE;
		} else if(s1.toLowerCase().startsWith("rat")) {
			return RATTLED;
		} else if(s1.toLowerCase().startsWith("rec_any") 
				|| s1.toLowerCase().startsWith("rech_any") 
				|| s1.toLowerCase().startsWith("recharge_any")) {
			return RECHARGE_ANY;
		} else if(s1.toLowerCase().startsWith("rec_def") 
				|| s1.toLowerCase().startsWith("rech_def") 
				|| s1.toLowerCase().startsWith("recharge_def")) {
			return RECHARGE_DEFENCE;
		} else if(s1.toLowerCase().startsWith("rec_spe") 
				|| s1.toLowerCase().startsWith("rech_spe") 
				|| s1.toLowerCase().startsWith("recharge_spe")) {
			return RECHARGE_SPECIFIC;
		} else if(s1.toLowerCase().startsWith("rec_thi") 
				|| s1.toLowerCase().startsWith("rech_thi") 
				|| s1.toLowerCase().startsWith("recharge_thi")) {
			return RECHARGE_THIS;
		} else if(s1.toLowerCase().startsWith("red_soa") 
				|| s1.toLowerCase().startsWith("reduce_soak")) {
			return REDUCE_SOAK;
		} else if(s1.toLowerCase().startsWith("slu")) {
			return SLUGGISH;
		} else if(s1.toLowerCase().startsWith("spe")) {
			return SPECIAL;
		} else if(s1.toLowerCase().startsWith("stag")) {
			return STAGGERED;
		} else if(s1.toLowerCase().startsWith("stan")) {
			return STANCE;
		} else if(s1.toLowerCase().startsWith("str")) {
			return STRESS;
		} else if(s1.toLowerCase().startsWith("wou") 
				|| s1.toLowerCase().startsWith("wnd")) {
			return WOUND;
		}
		return -1;
	}
}
