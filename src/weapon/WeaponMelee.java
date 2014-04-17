package weapon;


public abstract class WeaponMelee extends Weapon {
	
	public int getDamage(int distance) {
		return damage;
	}
	
	public int getRange() {
		return weaponStatus.getRange(range) + pointBlank;
	}
	
	@Override
	public void reset() {
		ready = false;
		damageLocation = "torso";
	}

	//these don't matter: they are included here because Weapon demands it!
	public boolean hasFullAmmo() {return true;}
	public boolean hasLoadedAmmo() {return true;}
	public void reload() {}
	public void reloadOne() {}
	public int getReloadTime() {return 1;}
	public int getReloadOneTime() {return 1;}

}
