package weapon.melee.sword;

import weapon.WeaponMelee;

public abstract class BaseSword extends WeaponMelee {
	
	@Override
	public boolean isCrit() {
		return ((rand.getOdds(7,50)) || weaponStatus.getCritChance());
	}
	
	@Override
	public String getVerb() {
		return "slashed";
	}
	
	@Override
	public String getDamageType() {
		return "slicing";
	}
	
}
