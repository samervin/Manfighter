package weapon.ranged.longrange;

import weapon.WeaponRanged;

public abstract class BaseLongrange extends WeaponRanged {

	public boolean isCrit() {
		return ((rand.getRand(1,50) < 2) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "sniped";
	}
	
}
