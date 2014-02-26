package weapon.melee.club;

import weapon.WeaponMelee;

public abstract class BaseClub extends WeaponMelee {
	
	public boolean isCrit() {
		return((rand.getOdds(7,50)) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "smashed";
	}
	
	public String getDamageType() {
		return "crushing";
	}
	
}
