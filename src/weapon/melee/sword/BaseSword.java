package weapon.melee.sword;

import game.Weapon;

public abstract class BaseSword extends Weapon {

	public boolean hasLoadedAmmo() {
		return true; //swords don't reload!
	}
	
	public void reload() {
		//do nothing
	}
	
}
