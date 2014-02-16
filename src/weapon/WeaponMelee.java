package weapon;

import game.PersonStatus;
import game.Weapon;

public abstract class WeaponMelee extends Weapon {

	protected int damage;
	protected int range;
	protected boolean ready = false;
	protected int swingTime;
	protected int knockback = 0;

	public int getDamage() {
		return weaponStatus.getDamage(damage);
	}

	public int getRange() {
		return weaponStatus.getRange(range);
	}

	public int getFireTime() {
		return weaponStatus.getAttackSpeed(swingTime);
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

	//these don't matter: they are included here because Weapon demands it!
	public boolean hasFullAmmo() {return true;}
	public boolean hasLoadedAmmo() {return true;}
	public void reload() {}

}
