package weapon.melee.polearm;

import weapon.WeaponMelee;

public abstract class BasePolearm extends WeaponMelee {

	public boolean isCrit() {
		return ((rand.getRand(1,50) < 8) || weaponStatus.getCritChance());
	}

	public String getVerb() {
		return "poked";
	}

}
