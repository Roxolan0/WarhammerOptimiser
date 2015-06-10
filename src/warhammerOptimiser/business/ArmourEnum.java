package warhammerOptimiser.business;

public enum ArmourEnum {
	NOTHING(Armour.NOTHING),
	CLOTH(Armour.CLOTH),
	ROBES(Armour.ROBES),
	LEATHER(Armour.LEATHER),
	BRIGANDINE(Armour.BRIGANDINE),
	MAIL_SHIRT(Armour.MAIL_SHIRT),
	CHAINMAIL(Armour.CHAINMAIL),
	SCALE(Armour.SCALE),
	ULTHUAN_SCALE(Armour.ULTHUAN_SCALE),
	BREASTPLATE(Armour.BREASTPLATE),
	FULL_PLATE(Armour.FULL_PLATE);

	private Armour armour;
	ArmourEnum(Armour newArmour) {
		armour = newArmour;
	}
	public Armour armour() {return armour;}
}
