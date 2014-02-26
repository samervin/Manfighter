package weapon;

import game.Weapon;

public abstract class WeaponRanged extends Weapon {

	protected int maxClip;
	protected int clip;
	protected int reloadTime;
	
	public abstract int getDamage(int distance);
	
	public int getRange() {
		return weaponStatus.getRange(range);
	}

	public int getFireTime() {
		return weaponStatus.getFireTime(fireTime);
	}
	
	public int getReloadTime() {
		return reloadTime;
	}

	public boolean hasFullAmmo() {
		return (clip == maxClip);
	}

	public boolean hasLoadedAmmo() {
		return(clip != 0);
	}

	public void reload() {
		clip = maxClip;
		ready = false;
	}

}
