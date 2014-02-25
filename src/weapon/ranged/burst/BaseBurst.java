package weapon.ranged.burst;

import weapon.WeaponRanged;

public abstract class BaseBurst extends WeaponRanged {

	@Override
	public boolean isCrit() {
		return ((rand.getOdds(1,50)) || weaponStatus.getCritChance());
	}

	@Override
	public String getVerb() {
		return "blasted";
	}

}
