package weapon.ranged.oneshot;

import weapon.WeaponRanged;

public abstract class BaseOneshot extends WeaponRanged {

	@Override
	public boolean isCrit() {
		return ((rand.getOdds(1,50)) || weaponStatus.getCritChance());
	}

	@Override
	public String getVerb() {
		return "shot";
	}

}
