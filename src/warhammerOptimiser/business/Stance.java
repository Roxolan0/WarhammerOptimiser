package warhammerOptimiser.business;

public enum Stance {
	CONSERVATIVE(0), RECKLESS(1), VERSATILE (2), ALL(3), NONE(4);

	private int code;

	private Stance(int i) {
		code = i;
	}
}
