package weapon.melee.saw;

import weapon.WeaponMelee;

public abstract class BaseSaw extends WeaponMelee {

	public boolean isCrit() {
		return ((rand.getOdds(7,50)) || weaponStatus.getCritChance());
	}

	public String getVerb() {
		return "sawed";
	}
	
	public String getDamageType() {
		return "slicing";
	}

}
