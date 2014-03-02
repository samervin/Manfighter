package weapon.melee.axe;

import weapon.WeaponMelee;

public abstract class BaseAxe extends WeaponMelee {

	public boolean isCrit() {
		return ((rand.getOdds(7,50)) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "axed";
	}
}
