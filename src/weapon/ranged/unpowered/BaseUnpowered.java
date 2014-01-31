package weapon.ranged.unpowered;

import game.RandGen;
import game.Weapon;

public abstract class BaseUnpowered extends Weapon {

	public boolean hasFullAmmo() {
		return true; //trivially
	}

	public boolean hasLoadedAmmo() {
		return true; //trivially
	}

	public void reload() {
		//do nothing
	}
	
	public boolean isCrit() {
		return(RandGen.getRand(1,50) < 2);
	}
	
	public String getVerb() {
		return "arrowed";
	}
}
