package weapon.ranged.unpowered;

import weapon.WeaponRanged;

public abstract class BaseUnpowered extends WeaponRanged {
	
	public boolean isCrit() {		
		return ((rand.getRand(1,50) < 2) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "arrowed";
	}
}
