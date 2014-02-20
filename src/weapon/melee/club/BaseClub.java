package weapon.melee.club;

import weapon.WeaponMelee;

public abstract class BaseClub extends WeaponMelee {
	
	public boolean isCrit() {
		return((rand.getRand(1,50) < 8) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "smashed";
	}
	
}
