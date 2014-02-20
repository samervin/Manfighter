package weapon.ranged.explosive;

import weapon.WeaponRanged;

public abstract class BaseExplosive extends WeaponRanged {

	public boolean isCrit() {
		return ((rand.getRand(1,50) < 2) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "shot";
	}
}
