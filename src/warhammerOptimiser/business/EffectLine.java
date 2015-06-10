package warhammerOptimiser.business;

import java.io.Serializable;
import java.util.ArrayList;

public class EffectLine implements Serializable {
	private static final long serialVersionUID = 2251118678026843403L;
	
	public EffectLine(Cost newCost, ArrayList<Effect> newEffects) {
		cost = newCost;
		effects = newEffects;
	}
	public EffectLine(Cost newCost, Effect[] newEffects) {
		cost = newCost;
		effects = new ArrayList<Effect>();
		for(int i = 0; i < newEffects.length; ++i) {
			effects.add(newEffects[i]);
		}
	}
	public Cost getCost() {
		return cost;
	}
	public ArrayList<Effect> getEffects() {
		return effects;
	}
	public Effect getEffect(int i) {
		if(i >= size()) {
			throw new IllegalArgumentException("This line does not have " + (i+1) + "effects.");
		}
		return effects.get(i);
	}
	public void addEffect(Effect e) {
		effects.add(e);
	}
	public int size() {
		return effects.size();
	}
	
	public int getTotalUtility(boolean hasSuccess, Character character, Character enemy, ResultTypeUtility utilityTable) {
		int total = 0;
		for(int i = 0; i < size(); ++i) {
			if(hasSuccess || !effects.get(i).needsSuccess()) {
				total += effects.get(i).getUtility(hasSuccess, character, enemy, utilityTable);
			}
		}
		return total;
	}
	
	public String toString() {
		return toCardString();
	}
	public String toCardString() {
		String s = "";
		s += cost.toCardString();
		s += ": ";
		for(int i = 0; i < effects.size(); ++i) {
			s += effects.get(i).toCardString() + ", ";
		}
		s = s.substring(0, s.length() - 2);	//removes last ", "
		return s;
	}

	public boolean equals(Object o) {
		if(!(o instanceof EffectLine)) return false;
		EffectLine other = (EffectLine) o;
		return (this.cost.equals(other.cost)
				&& this.effects.equals(other.effects));
	}
	
	private Cost cost;
	private ArrayList<Effect> effects;
}
