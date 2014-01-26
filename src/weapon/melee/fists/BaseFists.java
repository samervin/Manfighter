package weapon.melee.fists;

import game.Weapon;

public abstract class BaseFists extends Weapon {
	
	public boolean isReadied() {
		return false; //fists are trivially always ready, false prevents movement penalties
	}
	
	public void setReadied(boolean r) {
		//do nothing
	}
	
	public boolean hasLoadedAmmo() {
		return true; //fists don't reload!
	}
	
	public void reload() {
		//do nothing
	}
}
