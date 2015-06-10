package warhammerOptimiser.business;

import java.util.Comparator;

public class NameUtilityPair implements Comparator<NameUtilityPair> {
	private String name;
	private double utility;
	
	public NameUtilityPair(String newName, double newUtility) {
		name = newName;
		utility = newUtility;
	}
	public NameUtilityPair() {
		name = "";
		utility = 0;
	}
	public String getName() {
		return name;
	}
	public void setName(String newName) {
		name = newName;
	}
	public double getUtility() {
		return utility;
	}
	public void setUtility(double newUtility) {
		utility = newUtility;
	}
	public String toString() {
		return name + " " + utility;
	}
	@Override
	public int compare(NameUtilityPair pair1, NameUtilityPair pair2) {
		return Double.compare(pair1.getUtility(), pair2.getUtility());
	}
}
