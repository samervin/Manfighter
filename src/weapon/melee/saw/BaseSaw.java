package weapon.melee.saw;

import weapon.WeaponMelee;

public abstract class BaseSaw extends WeaponMelee {

	public boolean isCrit() {
		return ((rand.getRand(1,50) < 8) || weaponStatus.getCritChance());
	}

	public String getVerb() {
		return "sawed";
	}

}
