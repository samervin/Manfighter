package weapon.ranged.unpowered;

import weapon.WeaponRanged;

public abstract class BaseUnpowered extends WeaponRanged {
	
	public boolean isCrit() {		
		return ((rand.getOdds(1,50)) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "arrowed";
	}
	
	public String getDamageType() {
		return "piercing";
	}
}
