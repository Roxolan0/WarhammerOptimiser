package warhammerOptimiser.business;

public enum OldWeapon {
	NOTHING(0,999),
	DAGGER(4,3),	//TODO can be thrown as improvised throwing dagger
	FLAIL(7,3),
	GAUNTLET(4,4),
	GREAT_WEAPON(7,2),
	HALBERD(6,2),	//TODO can be used as a spear
	HAND_WEAPON(5,3),
	IMPROVISED_MELEE(3,3),
	LANCE(6,2),
	MAIN_GAUCHE(4,4),
	MORNING_STAR(6,3),	//TODO add_recharge_this against Block or Parry
	QUARTER_STAFF(4,4),
	RAPIER(5,3),
	SPEAR(5,2),	//TODO +1 dr when two-handed
	UNARMED(3,4),
	SABRE(5,3),
	
	BLUNDERBUSS(5,2,1),
	CROSSBOW(6,3,3),
	CROSSBOW_PISTOL(4,3,1),
	HANDGUN(6,2,2),
	HOCHLAND_LONG_RIFLE(6,2,3),
	IMPROVISED_RANGED(3,4,1),
	JAVELIN(5,3,1),	//TODO can be melee as improvised weapon
	LASSO(0,999,1),	//TODO can be used to perform an entangling stunt
	LONGBOW(5,3,3),
	NET(0,999,1),
	PISTOL(6,2,1),
	REPEATER_CROSSBOW(4,3,2),	//TODO 10 ammo, 4 manoeuvres to reload afterwards
	REPEATER_HANDGUN(6,2,2),	//TODO 6 ammo, 6 manoeuvres to reload afterwards
	REPEATER_PISTOL(6,2,1),	//TODO 6 ammo, 6 manoeuvres to reload afterwards
	SHORTBOW(5,3,2),
	SLING(4,3,3),	//TODO can't be superior quality
	SPEAR_RANGED(5,3,1),
	STAFF_SLING(5,3,3),
	THROWING_AXE(5,3,1),	//TODO is improvised melee weapon
	THROWING_DAGGER(4,4,1),	//TODO is improvised melee weapon
	WHIP(3,5,1);
	static{		//Weapon special properties
		DAGGER.fast = true;
		FLAIL.slow = true;
		FLAIL.vicious = true;
		FLAIL.twoHanded = true;
		GREAT_WEAPON.twoHanded = true;
		HALBERD.twoHanded = true;
		LANCE.pierce = 1;
		LANCE.mounted = true;	//TODO improvised weapon when not mounted
		MAIN_GAUCHE.fast = true;
		MAIN_GAUCHE.defensive = true;	//TODO can be used as Ordinary, but loses fast
		MORNING_STAR.slow = true;
		QUARTER_STAFF.defensive = true;
		RAPIER.fast = true;
		SPEAR.fast = true;
		SABRE.mounted = true;
		BLUNDERBUSS.blast = true;
		BLUNDERBUSS.reload = true;
		BLUNDERBUSS.twoHanded = true;
		BLUNDERBUSS.unreliable = 2;
		CROSSBOW.twoHanded = true;
		CROSSBOW.reload = true;
		CROSSBOW_PISTOL.reload = true;
		HANDGUN.pierce = 1;
		HANDGUN.reload = true;
		HANDGUN.twoHanded = true;
		HANDGUN.unreliable = 2;
		HOCHLAND_LONG_RIFLE.pierce = 1;
		HOCHLAND_LONG_RIFLE.reload = true;
		HOCHLAND_LONG_RIFLE.twoHanded = true;
		HOCHLAND_LONG_RIFLE.unreliable = 2;	//TODO lose this if superior quality
		IMPROVISED_RANGED.thrown = true;
		JAVELIN.thrown = true;
		LASSO.entangling = true;
		LONGBOW.pierce = 1;
		LONGBOW.twoHanded = true;
		LONGBOW.extreme = true;
		NET.entangling = true;
		PISTOL.pierce = 1;
		PISTOL.reload = true;
		PISTOL.unreliable = 2;
		REPEATER_CROSSBOW.twoHanded = true;
		REPEATER_HANDGUN.pierce = 1;
		REPEATER_HANDGUN.unreliable = 1;
		REPEATER_PISTOL.pierce = 1;
		REPEATER_PISTOL.unreliable = 1;
		SHORTBOW.twoHanded = true;
		SPEAR_RANGED.thrown = true;
		STAFF_SLING.twoHanded = true;
		STAFF_SLING.extreme = true;
		THROWING_AXE.thrown = true;
		THROWING_DAGGER.thrown = true;
		WHIP.entangling = true;
	}
	
	OldWeapon (int newDr, int newCr, int newRange) {
		dr = newDr;
		cr = newCr;
		range = newRange;
	}
	OldWeapon(int newDr, int newCr) {
		this(newDr, newCr, 0);
	}
	public int getDr() {
		return dr;
	}
	public int getCr() {
		return cr;
	}
	public boolean isTwoHanded() {
		return twoHanded;
	}
	public boolean isRanged() {
		return (range > 0);
	}
//	public boolean hasFor() {	//TODO make it work again
//		return hasFor;
//	}
//	public boolean hasMis() {	//TODO make it work again
//		return hasMis;
//	}
		
	private int dr;
	private int cr;
//	private boolean hasFor, hasMis;
	private int attuned, pierce, unreliable;
	private boolean blast, defensive, entangling, extreme, fast, mounted, 
					reload, slow, thrown, twoHanded, vicious;
	private int range;
//	private Group group;
//	private int cost;
//	private int encumbrance;
//	private int rarity;
}




//public static final Weapon NOTHING;
//public static final Weapon DAGGER;
//public static final Weapon FLAIL;
//public static final Weapon GAUNTLET;
//public static final Weapon GREAT_WEAPON;
//public static final Weapon HALBERD;
//public static final Weapon HAND_WEAPON;
//public static final Weapon IMPROVISED_MELEE;
//public static final Weapon LANCE;
//public static final Weapon MAIN_GAUCHE;
//public static final Weapon MORNING_STAR;
//public static final Weapon QUARTER_STAFF;
//public static final Weapon RAPIER;
//public static final Weapon SPEAR;
//public static final Weapon UNARMED;
//public static final Weapon SABRE;
//
//public static final Weapon BLUNDERBUSS;
//public static final Weapon CROSSBOW;
//public static final Weapon CROSSBOW_PISTOL;
//public static final Weapon HANDGUN;
//public static final Weapon HOCHLAND_LONG_RIFLE;
//public static final Weapon IMPROVISED_RANGED;
//public static final Weapon JAVELIN;
//public static final Weapon LASSO;
//public static final Weapon LONGBOW;
//public static final Weapon NET;
//public static final Weapon PISTOL;
//public static final Weapon REPEATER_CROSSBOW;
//public static final Weapon REPEATER_HANDGUN;
//public static final Weapon REPEATER_PISTOL;
//public static final Weapon SHORTBOW;
//public static final Weapon SLING;
//public static final Weapon SPEAR_RANGED;
//public static final Weapon STAFF_SLING;
//public static final Weapon THROWING_AXE;
//public static final Weapon THROWING_DAGGER;
//public static final Weapon WHIP;
//
//static {
//	NOTHING = new Weapon(0,999);
//	DAGGER = new Weapon(4,3);	//TODO can be thrown as improvised throwing dagger
//	DAGGER.fast = true;
//	FLAIL = new Weapon(7,3);
//	FLAIL.slow = true;
//	FLAIL.vicious = true;
//	FLAIL.twoHanded = true;
//	GAUNTLET = new Weapon(4,4);
//	GREAT_WEAPON = new Weapon(7,2);
//	GREAT_WEAPON.twoHanded = true;
//	HALBERD = new Weapon(6,2);	//TODO can be used as a spear
//	HALBERD.twoHanded = true;
//	HAND_WEAPON = new Weapon(5,3);
//	IMPROVISED_MELEE = new Weapon(3,3);
//	LANCE = new Weapon(6,2);
//	LANCE.pierce = 1;
//	LANCE.mounted = true;	//TODO improvised weapon when not mounted
//	MAIN_GAUCHE = new Weapon(4,4);
//	MAIN_GAUCHE.fast = true;
//	MAIN_GAUCHE.defensive = true;	//TODO can be used as Ordinary, but loses fast
//	MORNING_STAR = new Weapon(6,3);	//TODO add_recharge_this against Block or Parry
//	MORNING_STAR.slow = true;
//	QUARTER_STAFF = new Weapon(4,4);
//	QUARTER_STAFF.defensive = true;
//	RAPIER = new Weapon(5,3);
//	RAPIER.fast = true;
//	SPEAR = new Weapon(5,2);	//TODO +1 dr when two-handed
//	SPEAR.fast = true;
//	UNARMED = new Weapon(3,4);
//	SABRE = new Weapon(5,3);
//	SABRE.mounted = true;
//	
//	BLUNDERBUSS = new Weapon(5,2,1);
//	BLUNDERBUSS.blast = true;
//	BLUNDERBUSS.reload = true;
//	BLUNDERBUSS.twoHanded = true;
//	BLUNDERBUSS.unreliable = 2;
//	CROSSBOW = new Weapon(6,3,3);
//	CROSSBOW.twoHanded = true;
//	CROSSBOW.reload = true;
//	CROSSBOW_PISTOL = new Weapon(4,3,1);
//	CROSSBOW_PISTOL.reload = true;
//	HANDGUN = new Weapon(6,2,2);
//	HANDGUN.pierce = 1;
//	HANDGUN.reload = true;
//	HANDGUN.twoHanded = true;
//	HANDGUN.unreliable = 2;
//	HOCHLAND_LONG_RIFLE = new Weapon(6,2,3);
//	HOCHLAND_LONG_RIFLE.pierce = 1;
//	HOCHLAND_LONG_RIFLE.reload = true;
//	HOCHLAND_LONG_RIFLE.twoHanded = true;
//	HOCHLAND_LONG_RIFLE.unreliable = 2;	//TODO lose this if superior quality
//	IMPROVISED_RANGED = new Weapon(3,4,1);
//	IMPROVISED_RANGED.thrown = true;
//	JAVELIN = new Weapon(5,3,1);	//TODO can be melee as improvised weapon
//	JAVELIN.thrown = true;
//	LASSO = new Weapon(0,999,1);	//TODO can be used to perform an entangling stunt
//	LASSO.entangling = true;
//	LONGBOW = new Weapon(5,3,3);
//	LONGBOW.pierce = 1;
//	LONGBOW.twoHanded = true;
//	LONGBOW.extreme = true;
//	NET = new Weapon(0,999,1);
//	NET.entangling = true;
//	PISTOL = new Weapon(6,2,1);
//	PISTOL.pierce = 1;
//	PISTOL.reload = true;
//	PISTOL.unreliable = 2;
//	REPEATER_CROSSBOW = new Weapon(4,3,2);	//TODO 10 ammo, 4 manoeuvres to reload afterwards
//	REPEATER_CROSSBOW.twoHanded = true;
//	REPEATER_HANDGUN = new Weapon(6,2,2);	//TODO 6 ammo, 6 manoeuvres to reload afterwards
//	REPEATER_HANDGUN.pierce = 1;
//	REPEATER_HANDGUN.unreliable = 1;
//	REPEATER_PISTOL = new Weapon(6,2,1);	//TODO 6 ammo, 6 manoeuvres to reload afterwards
//	REPEATER_PISTOL.pierce = 1;
//	REPEATER_PISTOL.unreliable = 1;
//	SHORTBOW = new Weapon(5,3,2);
//	SHORTBOW.twoHanded = true;
//	SLING = new Weapon(4,3,3);	//TODO can't be superior quality
//	SPEAR_RANGED = new Weapon(5,3,1);
//	SPEAR_RANGED.thrown = true;
//	STAFF_SLING = new Weapon(5,3,3);
//	STAFF_SLING.twoHanded = true;
//	STAFF_SLING.extreme = true;
//	THROWING_AXE = new Weapon(5,3,1);	//TODO is improvised melee weapon
//	THROWING_AXE.thrown = true;
//	THROWING_DAGGER = new Weapon(4,4,1);	//TODO is improvised melee weapon
//	THROWING_DAGGER.thrown = true;
//	WHIP = new Weapon(3,5,1);
//	WHIP.entangling = true;
//}
