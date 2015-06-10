package warhammerOptimiser.business;

public enum Characteristic {
	STRENGTH (0),
	TOUGHNESS (1),
	AGILITY (2),
	INTELLIGENCE (3),
	WILLPOWER (4),
	FELLOWSHIP (5),
	VERSATILE (6),
	ALL (7),
	NONE (8);

	public static int NB_CHARACTERISTICS = 6;
	
	private int code;
	
	private Characteristic(int i) {
		code = i;
	}
	
	public int getCode() {
		return code;
	}
}
