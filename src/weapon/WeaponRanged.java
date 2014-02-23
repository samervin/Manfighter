package weapon;

import game.Weapon;

public abstract class WeaponRanged extends Weapon {

	protected int maxClip;
	protected int clip;
	
	public int getDamage() {
		clip--;
		return weaponStatus.getDamage(damage);
	}
	
	public int getRange() {
		return weaponStatus.getRange(range);
	}

	public int getFireTime() {
		return weaponStatus.getFireTime(fireTime);
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
