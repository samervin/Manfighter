package weapon.melee.sword;

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
	
}
