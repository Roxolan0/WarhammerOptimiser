package warhammerOptimiser.business;
import java.io.Serializable;
import java.util.ArrayList;

public class ActionStance implements Serializable {
	private static final long serialVersionUID = 7096508443019224557L;
	
	public ActionStance(String newTitle, int newRecharge, int newChallenge, int newMisfortune, ArrayList<EffectLine> newEffectLines) {
		if(newRecharge < 0 || newMisfortune < 0 || newChallenge < 0) {
			throw new IllegalArgumentException("Invalid Action parameter.");
		}
		title = newTitle;
		recharge = newRecharge;
		challenge = newChallenge;
		misfortune = newMisfortune;
//		successes = newSuccesses;
		effectLines = newEffectLines;
	}
	
	public ActionStance(String newTitle, int newRecharge, ArrayList<EffectLine> newEffectLines) {
		this(newTitle, newRecharge, 0, 0, newEffectLines);
	}
	
	public ActionStance(String newTitle, int newRecharge, int newChallenge, int newMisfortune, EffectLine[] newEffectLinesArray) {
		this(newTitle, newRecharge, newChallenge, newMisfortune, new ArrayList<EffectLine>());
		for(EffectLine effectLine : newEffectLinesArray) {
			effectLines.add(effectLine);
		}
	}
	
	public ActionStance(String newTitle, int newRecharge, EffectLine[] newEffectLinesArray) {
		this(newTitle, newRecharge, 0, 0, newEffectLinesArray);
	}
	
	public ActionStance() {
		this("", 0, new ArrayList<EffectLine>());
	}
	
	public ActionStance(ActionStance actionStance) {
		this.challenge = actionStance.challenge;
		this.effectLines = new ArrayList<EffectLine>(actionStance.effectLines);
		this.expertise = actionStance.expertise;
		this.fortune = actionStance.fortune;
		this.misfortune = actionStance.misfortune;
		this.recharge = actionStance.recharge;
		this.title = actionStance.title;
	}
	
	public String getTitle() {
		return title;
	}
	public int getRecharge() {
		return recharge;
	}
	public int getChallenge() {
		return challenge;
	}
	public int getMisfortune() {
		return misfortune;
	}
	public int getFortune() {
		return fortune;
	}
	public int getExpertise() {
		return expertise;
	}
	
	public void fillNonEffects(ActionStance stance) {
		if(challenge == 0)	challenge = stance.getChallenge();
		if(expertise == 0)	expertise = stance.getExpertise();
		if(fortune == 0)	fortune = stance.getFortune();
		if(misfortune == 0)	misfortune = stance.getMisfortune();
		if(recharge == 0)	recharge = stance.getRecharge();
		if(title.equals(""))	title = stance.getTitle();
	}
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	public void setRecharche(int newRecharge) {
		recharge = newRecharge;
	}
	public void setChallenge(int newChallenge) {
		challenge = newChallenge;
	}
	public void setMisfortune(int newMisfortune) {
		misfortune = newMisfortune;
	}
	public void setFortune(int newFortune) {
		fortune = newFortune;
	}
	public void setExpertise(int newExpertise) {
		expertise = newExpertise;
	}
	//TODO: imperfection: if for some reason a S/O/C effectLine has a large negative utility, 
	//it'll be considered "best" by the method. Vice versa for A/C effectLines.
	public EffectLine takeStrongestEffectLine(EffectType effectType, int effectNb, boolean hasSuccess, 
			Character character, Character enemy, ResultTypeUtility utilityTable, boolean isPositive) {
		int position = -1;
		int minCost = Integer.MAX_VALUE;
		int maxAbsUtility = Integer.MIN_VALUE;
		if(effectNb > 0) {
			for(int i = 0; i < effectLines.size(); ++i) {
				if(effectLines.get(i).getCost().getType() == effectType 
						&& effectLines.get(i).getCost().getNb() <= effectNb 
						&& (Math.abs(effectLines.get(i).getTotalUtility(hasSuccess, character, enemy, utilityTable)) > maxAbsUtility 
								|| (Math.abs(effectLines.get(i).getTotalUtility(hasSuccess, character, enemy, utilityTable)) == maxAbsUtility 
										&& effectLines.get(i).getCost().getNb() < minCost)) ) {
					position = i;
					minCost = effectLines.get(i).getCost().getNb();
					maxAbsUtility = Math.abs(effectLines.get(i).getTotalUtility(hasSuccess, character, enemy, utilityTable));
				}
			}
		}
		if(position == -1) {
			return null;
		} else {
			EffectLine effectLine = effectLines.get(position);
			effectLines.remove(position);
			return effectLine;
		}
	}
	
	public EffectLine takeBestEffectLine(EffectType effectType, int effectNb,
			boolean hasSuccess, Character character, Character enemy, ResultTypeUtility utilityTable) {
		return takeStrongestEffectLine(effectType, effectNb, hasSuccess, character, enemy, utilityTable, true);
	}
	
	public void addAllEffectLines(ActionStance newActionStance) {
		if(!effectLines.addAll(newActionStance.effectLines)) {
			throw new IllegalArgumentException("Attempt to compline two ActionStances failed.");
		}
	}
	
	public void addEffectLine(EffectLine line) {
		effectLines.add(line);
	}
	
	public int nbEffectLines() {
		return effectLines.size();
	}
	
	public String toString() {
		return toCardString();
	}
	public String toCardString() {
		String s = "";
		s += title + "\r\n";
		s += toCardStringNoTitle();
		return s;
	}
	public String toCardStringNoTitle() {
		String s = "";
		s += "Rec " + recharge + "\r\n";
		if(challenge != 0)
		{
			s += "Chl " + challenge + "\r\n";
		}
		if(misfortune != 0) {
			s += "Mis " + misfortune + "\r\n";
		}
		if(fortune != 0) {
			s += "For " + fortune + "\r\n";
		}
		if(expertise != 0) {
			s += "Exp " + expertise + "\r\n";
		}
		for(int i = 0; i < effectLines.size(); ++i) {
			s += effectLines.get(i).toCardString() + "\r\n";
		}
		return s;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof ActionStance)) return false;
		ActionStance other = (ActionStance) o;
		return (this.title.equals(other.title)
				&& this.challenge == other.challenge
				&& this.misfortune == other.misfortune
				&& this.recharge == other.recharge
				&& this.effectLines.equals(other.effectLines)
				);
	}
	
	private String title;
	private int recharge;
	private int challenge;
	private int misfortune;
	private int fortune;
	private int expertise;
//	private Skill skill;
//	private Ability ability;
//	private Skill targetSkill;
//  private Ability targetAbility;
//	private String requirements;
//	private String special;
	private ArrayList<EffectLine> effectLines;
}

//public EffectLine takeWorstEffectLine(EffectType effectType, int effectNb,
//boolean hasSuccess, ResultTypeUtility utilityTable) {
//return takeStrongestEffectLine(effectType, effectNb, hasSuccess, utilityTable, false);
//}

//public EffectLine takeBestSuccessLine(int successNb, ResultTypeUtility utilityTable) {
//return takeBestEffectLine(EffectType.S, successNb, true, utilityTable);
//}

