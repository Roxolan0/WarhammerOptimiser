package warhammerOptimiser.business;

import static warhammerOptimiser.business.Weapon.*;

public enum WeaponEnum {
	NOTHING(Weapon.NOTHING),
	DAGGER(Weapon.DAGGER),
	FLAIL(Weapon.FLAIL),
	GAUNTLET(Weapon.GAUNTLET),
	GREAT_WEAPON(Weapon.GREAT_WEAPON),
	HALBERD(Weapon.HALBERD),
	HAND_WEAPON(Weapon.HAND_WEAPON),
	IMPROVISED_MELEE(Weapon.IMPROVISED_MELEE),
	LANCE(Weapon.LANCE),
	MAIN_GAUCHE(Weapon.MAIN_GAUCHE),
	MORNING_STAR(Weapon.MORNING_STAR),
	QUARTER_STAFF(Weapon.QUARTER_STAFF),
	RAPIER(Weapon.RAPIER),
	SPEAR(Weapon.SPEAR),
	UNARMED(Weapon.UNARMED),
	SABRE(Weapon.SABRE),
	
	BLUNDERBUSS(Weapon.BLUNDERBUSS),
	CROSSBOW(Weapon.CROSSBOW),
	CROSSBOW_PISTOL(Weapon.CROSSBOW_PISTOL),
	HANDGUN(Weapon.HANDGUN),
	HOCHLAND_LONG_RIFLE(Weapon.HOCHLAND_LONG_RIFLE),
	IMPROVISED_RANGED(Weapon.IMPROVISED_RANGED),
	JAVELIN(Weapon.JAVELIN),
	LASSO(Weapon.LASSO),
	LONGBOW(Weapon.LONGBOW),
	NET(Weapon.NET),
	PISTOL(Weapon.PISTOL),
	REPEATER_CROSSBOW(Weapon.REPEATER_CROSSBOW),
	REPEATER_HANDGUN(Weapon.REPEATER_HANDGUN),
	REPEATER_PISTOL(Weapon.REPEATER_PISTOL),
	SHORTBOW(Weapon.SHORTBOW),
	SLING(Weapon.SLING),
	SPEAR_RANGED(Weapon.SPEAR_RANGED),
	STAFF_SLING(Weapon.STAFF_SLING),
	THROWING_AXE(Weapon.THROWING_AXE),
	THROWING_DAGGER(Weapon.THROWING_DAGGER),
	WHIP(Weapon.WHIP);
	
	private Weapon weapon;
	WeaponEnum(Weapon newWeapon) {
		weapon = newWeapon;
	}
	public Weapon weapon() {return weapon;}
	
	public static WeaponEnum toEnum(Weapon theWeapon) {
		WeaponEnum theEnum = NOTHING;
		for(WeaponEnum weaponEnum : WeaponEnum.values())
			if(weaponEnum.weapon() == theWeapon)
				theEnum = weaponEnum;
		return theEnum;
	}
}
