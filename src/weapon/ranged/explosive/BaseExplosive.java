package weapon.ranged.explosive;

import weapon.WeaponRanged;

public abstract class BaseExplosive extends WeaponRanged {

	public boolean isCrit() {
		return ((rand.getOdds(1,50)) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "shot";
	}
}
