package warhammerOptimiser.business;

public enum Skill {
	ANIMAL_HANDLING (0),
	ATHLETICS (1),
	BALLISTIC_SKILL (2),
	CHANNELLING (3),
	CHARM (4),
	COORDINATION (5),
	DISCIPLINE (6),
	EDUCATION (7),
	FIRST_AID (8),
	FOLKLORE (9),
	GUILE (10),
	INTIMIDATE (11),
	INTUITION (12),
	INVOCATION (13),
	LEADERSHIP (14),
	MAGICAL_SIGHT (15),
	MEDICINE (16),
	NATURE_LORE (17),
	OBSERVATION (18),
	PIETY (19),
	RESILIENCE (20),
	RIDE (21),
	SKULDUGGERY (22),
	SPELLCRAFT (23),
	STEALTH (24),
	TRADECRAFT (25),
	WEAPON_SKILL (26);
	
	public static int NB_SKILLS = 27;
	
	private int code;
	
	private Skill(int i) {
		code = i;
	}
	
	public int getCode() {
		return code;
	}
}
