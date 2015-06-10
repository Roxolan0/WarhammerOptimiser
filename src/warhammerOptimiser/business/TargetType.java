package warhammerOptimiser.business;

import java.io.Serializable;

public class TargetType implements Serializable {
	private static final long serialVersionUID = 705113200709580787L;

	public static final int TARGET_NB = 4;

	public static final int GAIN = 0;
	public static final int SUFFER = 1;
	public static final int FACE = 2;
	public static final int DEAL = 3;
	
	public static boolean isValid(int targetType) {
		return(targetType == GAIN || targetType == SUFFER || targetType == FACE || targetType == DEAL);
	}

	public static String name(int targetType) throws IllegalArgumentException {
		String s = "";
		if(targetType == GAIN) {
			s += "gain";
		} else if(targetType == SUFFER) {
			s += "suffer";
		} else if(targetType == FACE) {
			s += "face";
		} else if(targetType == DEAL) {
			s += "deal";
		} else {
			throw new IllegalArgumentException("Tried to get name of TargetType " + targetType + ".");
		}
		return s;
	}
}

//GAIN (0),
//SUFFER (1),
//FACE(2),
//DEAL(3);
//
//public int getNb() {
//	return nb;
//}
//
//private TargetType(int newNb) {
//	nb = newNb;
//}
//
//private final int nb;
