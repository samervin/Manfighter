package weapon.melee.sword;

import game.RandGen;
import game.Weapon;

public abstract class BaseSword extends Weapon {
	
	public boolean hasFullAmmo() {
		return true; // trivially
	}
	
	public boolean hasLoadedAmmo() {
		return true; //fists don't reload!
	}

	public void reload() {
		//do nothing
	}
	
	public boolean isCrit() {
		return(RandGen.getRand(1,50) < 8);
	}
	
	public String getVerb() {
		return "slashed";
	}
	
}
