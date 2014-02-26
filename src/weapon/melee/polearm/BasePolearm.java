package weapon.melee.polearm;

import weapon.WeaponMelee;

public abstract class BasePolearm extends WeaponMelee {

	@Override
	public boolean isCrit() {
		return ((rand.getOdds(7,50)) || weaponStatus.getCritChance());
	}

	@Override
	public String getVerb() {
		return "poked";
	}

	@Override
	public String getDamageType() {
		return "piercing";
	}
}
