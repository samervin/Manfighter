package weapon.melee.sword;

import weapon.WeaponMelee;

public abstract class BaseSword extends WeaponMelee {
	
	public boolean isCrit() {
		return ((rand.getOdds(7,50)) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "slashed";
	}
	
}
