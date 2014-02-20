package weapon.melee.axe;

import weapon.WeaponMelee;

public abstract class BaseAxe extends WeaponMelee {

	public boolean isCrit() {
		return ((rand.getRand(1,50) < 8) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "axed";
	}

}
