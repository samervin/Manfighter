package weapon.melee.sword;

import weapon.WeaponMelee;

public abstract class BaseSword extends WeaponMelee {
	
	public boolean isCrit() {
		return ((rand.getRand(1,50) < 8) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "slashed";
	}
	
}
