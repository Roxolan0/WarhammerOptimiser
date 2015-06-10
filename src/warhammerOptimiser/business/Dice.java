package warhammerOptimiser.business;

public class Dice {
	public Dice(ResultPool[] newSides) {
		sides = newSides;
	}
	
	public ResultPool[] getSides() {
		return sides;
	}
	
	public String toString() {
		String s = "";
		for(ResultPool side : sides) {
			s += "[" + side + "]\r\n";
		}
		return s;
	}
	
	private ResultPool[] sides;
}
