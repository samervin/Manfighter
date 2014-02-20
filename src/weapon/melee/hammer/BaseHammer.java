package weapon.melee.hammer;

import weapon.WeaponMelee;

public abstract class BaseHammer extends WeaponMelee {
	
	public boolean isCrit() {
		return((rand.getRand(1,50) < 6) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "smashed";
	}

}
