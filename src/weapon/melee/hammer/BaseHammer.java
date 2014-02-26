package weapon.melee.hammer;

import weapon.WeaponMelee;

public abstract class BaseHammer extends WeaponMelee {
	
	public boolean isCrit() {
		return((rand.getOdds(1,10)) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "smashed";
	}

	public String getDamageType() {
		return "crushing";
	}
}
