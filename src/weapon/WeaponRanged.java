package weapon;

import game.PersonStatus;
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
		return weaponStatus.getAttackSpeed(fireTime);
	}

	public PersonStatus getInflictedStatus() {
		return inflictingStatus;
	}

	public boolean isReadied() {
		return ready;
	}

	public void setReadied(boolean readiness) {
		ready = readiness;
	}
	
	public int getKnockback() {
		return knockback;
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
