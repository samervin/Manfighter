package weapon;

import game.Weapon;

public abstract class WeaponRanged extends Weapon {

	protected int maxClip;
	protected int clip;
	protected int reloadTime;
	
	public int getDamage(int distance) {
		clip--;
		return weaponStatus.getDamage(damage);
	}
	
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
