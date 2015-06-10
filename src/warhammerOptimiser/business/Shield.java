package warhammerOptimiser.business;

public class Shield {
	public Shield(String newName, int newDefence, int newSoak) {
		name = newName;
		defence = newDefence;
		soak = newSoak;
	}
	public Shield(int newDefence, int newSoak) {
		this("custom", newDefence, newSoak);
	}
	public Shield(Shield newShield) {
		name = newShield.name;
		defence = newShield.defence;
		soak = newShield.soak;
	}
	public Shield(ShieldEnum shieldEnum) {
		this(shieldEnum.shield());
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
	
	public static final Shield NOTHING = new Shield("Nothing", 0,0);
	public static final Shield BUCKLER = new Shield("Buckler", 1,0);
	public static final Shield SPIKED_BUCKLER = new Shield("Spiked Buckler", 1,0);	//TODO is improvised gauntlet in melee
	public static final Shield ROUND = new Shield("Round Shield", 1,1);	//TODO is improvised weapon in melee
	public static final Shield TOWER = new Shield("Tower Shield", 2,1);	
	
	private String name;
	private int defence;
	private int soak;
}
