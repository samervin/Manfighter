package weapon.ranged.unpowered;

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
	
}
