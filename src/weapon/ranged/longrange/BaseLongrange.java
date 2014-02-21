package weapon.ranged.longrange;

import weapon.WeaponRanged;

public abstract class BaseLongrange extends WeaponRanged {

	public boolean isCrit() {
		return ((rand.getOdds(1,50)) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "sniped";
	}
	
}
