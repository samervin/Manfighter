package weapon.melee.polearm;

import weapon.WeaponMelee;

public abstract class BasePolearm extends WeaponMelee {

	public boolean isCrit() {
		return ((rand.getOdds(7,50)) || weaponStatus.getCritChance());
	}

	public String getVerb() {
		return "poked";
	}

}
