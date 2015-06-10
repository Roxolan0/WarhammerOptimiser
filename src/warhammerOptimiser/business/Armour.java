package warhammerOptimiser.business;

public class Armour {
	public Armour(String newName, int newDefence, int newSoak) {
		name = newName;
		defence = newDefence;
		soak = newSoak;
	}
	public Armour(int newDefence, int newSoak) {
		this("custom", newDefence, newSoak);
	}
	public Armour(Armour newArmour) {
		name = newArmour.name;
		defence = newArmour.defence;
		soak = newArmour.soak;
	}
	public Armour(ArmourEnum armourEnum) {
		this(armourEnum.armour());
	}
	public String getName() {
		return name;
	}
	public int getDefence() {
		return defence;
	}
	public int getSoak() {
		return soak;
	}
	
	public static final Armour NOTHING = new Armour("Nothing", 0,0);
	public static final Armour CLOTH = new Armour("Cloth", 0,1);
	public static final Armour ROBES = new Armour("Robes", 1,0);
	public static final Armour LEATHER = new Armour("Leather", 0,2);
	public static final Armour BRIGANDINE = new Armour("Brigandine", 1,1);
	public static final Armour MAIL_SHIRT = new Armour("Mail Shirt", 1,2);
	public static final Armour CHAINMAIL = new Armour("Chainmail", 0,3);
	public static final Armour SCALE = new Armour("Scale", 0,4);
	public static final Armour ULTHUAN_SCALE = new Armour("Ulthuan Scale", 1,3);
	public static final Armour BREASTPLATE = new Armour("Breastplate", 1,4);
	public static final Armour FULL_PLATE = new Armour("Full Plate", 1,5);
	
	private String name;
	private int defence;
	private int soak;
}
