package warhammerOptimiser.business;
import java.io.*;

import static com.roxolan.warhammeroptimiserapp.AppFileManager.cardReader;
import static warhammerOptimiser.business.Stance.*;

public class Action implements Serializable {
	private static final long serialVersionUID = 804962272000778613L;
	
	public Action() {
		conservative = new ActionStance("", 0, new EffectLine[]{}); 
		reckless = new ActionStance(conservative);
	}
	
	public Action(String fileName) throws IllegalArgumentException, FileNotFoundException {
		this(TextParser.parseFile(fileName));
	}
	public Action(BufferedReader reader) throws IllegalArgumentException, FileNotFoundException {
		this(TextParser.parseFile(reader));
	}
	
	public Action(ActionStance newConservative, ActionStance newReckless) {
		conservative = newConservative;
		reckless = newReckless;
	}

	public Action(Action action) {
		this.conservative = new ActionStance(action.conservative);
		this.reckless = new ActionStance(action.reckless);
	}

	public String getTitle(Stance stance) {
		return getActionStance(stance).getTitle();
	}
	public String getTitle() {
		return getTitle(CONSERVATIVE);
	}
	
	public void setStance(Stance stance, ActionStance newActionStance) {
		if(stance == CONSERVATIVE) {
			conservative = newActionStance;
		} else {
			reckless = newActionStance;
		}
	}
	
	public void setChallenge(Stance stance, int newChallenge) {
		getActionStance(stance).setChallenge(newChallenge);
	}
	public void setChallenge(int newChallenge) {
		setChallenge(CONSERVATIVE, newChallenge);
		setChallenge(RECKLESS, newChallenge);
	}
	
	public void setMisfortune(Stance stance, int newMisfortune) {
		getActionStance(stance).setMisfortune(newMisfortune);
	}
	public void setMisfortune(int newMisfortune) {
		setMisfortune(CONSERVATIVE, newMisfortune);
		setMisfortune(RECKLESS, newMisfortune);
	}
		
	public void setRecharge(Stance stance, int newRecharge) {
		getActionStance(stance).setRecharche(newRecharge);
	}
	public void setRecharge(int newRecharge) {
		setRecharge(CONSERVATIVE, newRecharge);
		setRecharge(RECKLESS, newRecharge);
	}
	
	public void setTitle(Stance stance, String newTitle) {
		getActionStance(stance).setTitle(newTitle);
	}
	public void setTitle(String newTitle) {
		setTitle(CONSERVATIVE, newTitle);
		setTitle(RECKLESS, newTitle);
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Action)) return false;
		Action other = (Action) o;
		return (this.conservative.equals(other.conservative)
				&& this.reckless.equals(other.reckless));
	}
	
	public EffectLine takeBestEffectLine(Stance stance, EffectType effectType, int effectNb, 
			boolean hasSuccess, Character character, Character enemy, ResultTypeUtility utilityTable) {
		return getActionStance(stance).takeBestEffectLine(effectType, effectNb, hasSuccess, character, enemy, utilityTable);
	}
	
	public void addEffectLine(Stance stance, EffectLine line) {
		getActionStance(stance).addEffectLine(line);
	}
	
	public void addEffectLine(EffectLine line) {
		conservative.addEffectLine(line);
		reckless.addEffectLine(line);
	}
	
	public void addAllEffectLines(Action newAction) {
		conservative.addAllEffectLines(newAction.conservative);
		reckless.addAllEffectLines(newAction.reckless);
	}
	
	public int nbEffectLines(Stance stance) {
		return getActionStance(stance).nbEffectLines();
	}
	
	public String toString() {
		String s = "";
		s += "[conservative]";
		s += conservative.toString();
		s += "--------\r\n";
		s += "[reckless]";
		s += reckless.toString();
		s += "--------\r\n";
		return(s);
	}
	public String toCardString() {
		String s = "";
		s += conservative.toCardString();
		s += "--------\r\n";
		s += reckless.toCardStringNoTitle();
		s += "--------\r\n";
		return(s);
	}	
	public void writeToText(String folder) {
		try {
			FileWriter outFile = new FileWriter(folder + "/" + conservative.getTitle() + ".txt");
			PrintWriter out = new PrintWriter(outFile);
			out.print(toCardString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void writeToText() {
		writeToText("cards/");
	}
	
	public ActionStance getActionStance(Stance stance) {
		if(stance == CONSERVATIVE) {
			return conservative;
		} else {
			return reckless;
		}
	}
	
	public ActionStance conservative() {
		return conservative;
	}
	public ActionStance reckless() {
		return reckless;
	}
	
//	private Set set;
	private ActionStance conservative;
	private ActionStance reckless;
}

//public Action(String newTitle, Action baseAction, Action addAction) {
//this.title = newTitle;
//this.conservative = new ActionStance(baseAction.conservative, addAction.conservative);
//this.reckless = new ActionStance(action1.reckless, action2.reckless);
//}

//public EffectLine takeWorstEffectLine(boolean isConservative, EffectType effectType, int effectNb, boolean hasSuccess, ResultTypeUtility utilityTable) {
//if(isConservative) {
//	return conservative.takeWorstEffectLine(effectType, effectNb, hasSuccess, utilityTable);
//} else {
//	return reckless.takeWorstEffectLine(effectType, effectNb, hasSuccess, utilityTable);
//}
//}

//public EffectLine takeBestSuccessLine(boolean isConservative, int successNb, ResultTypeUtility utilityTable) {
//return stance(isConservative).takeBestSuccessLine(successNb, utilityTable);
//}

