package warhammerOptimiser.business;

public enum ShieldEnum {
	NOTHING(Shield.NOTHING),
	BUCKLER(Shield.BUCKLER),
	SPIKED_BUCKLER(Shield.SPIKED_BUCKLER),
	ROUND(Shield.ROUND),
	TOWER(Shield.TOWER);
	
	private Shield shield;
	ShieldEnum(Shield newShield) {
		shield = newShield;
	}
	Shield shield() {return shield;}
}
