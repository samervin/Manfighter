package weapon.melee.polearm;

import game.RandGen;
import weapon.WeaponMelee;

public abstract class BasePolearm extends WeaponMelee {

	public boolean isCrit() {
		RandGen rand = new RandGen();

		return ((rand.getRand(1,50) < 8) || weaponStatus.getCritChance());
	}

	public String getVerb() {
		return "poked";
	}

}
