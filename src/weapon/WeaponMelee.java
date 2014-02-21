package weapon;

import game.Weapon;

public abstract class WeaponMelee extends Weapon {

	public int getDamage() {
		return weaponStatus.getDamage(damage);
	}

	//these don't matter: they are included here because Weapon demands it!
	public boolean hasFullAmmo() {return true;}
	public boolean hasLoadedAmmo() {return true;}
	public void reload() {}

}
