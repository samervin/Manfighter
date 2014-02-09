package weapon.melee.hammer;

import game.RandGen;
import weapon.WeaponMelee;

public abstract class BaseHammer extends WeaponMelee {
	
	public boolean isCrit() {
		RandGen rand = new RandGen();
		
		return((rand.getRand(1,50) < 6) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "smashed";
	}

}
