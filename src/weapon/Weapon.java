package weapon;

import game.RandGen;

import java.util.HashSet;

import status.person.BlankPersonStatus;
import status.person.PersonStatus;
import status.weapon.BlankWeaponStatus;
import status.weapon.WeaponStatus;

public abstract class Weapon {
	
	protected RandGen rand = new RandGen();
	protected int pointBlank = 60;

	//TODO: should really be sets/lists
	protected WeaponStatus weaponStatus = new BlankWeaponStatus();
	protected PersonStatus inflictingStatus = new BlankPersonStatus();
	protected final PersonStatus blankInflictingStatus = new BlankPersonStatus();
	
	protected int damage;
	protected int range;
	protected boolean ready = false;
	protected int readyTime;
	protected int fireTime;
	protected int knockback = 0;
	
	protected int selfDamage = 0;
	protected int selfDamageRange = 0;

	protected String damageLocation = "legs";
	
	//shared methods
	public String toString() {
		if(weaponStatus instanceof BlankWeaponStatus)
			return this.getBaseName().trim();
		
		String s = weaponStatus + " ";
		s += (this.getBaseName().substring(0,1).toLowerCase() + this.getBaseName().substring(1)).trim();
		return s;
	}
	
	public int getLocationDamage(int damage) {
		if(damageLocation.equals("head")) {
			System.out.println("Headshot!");
			return damage * 4 / 3;
		}
		
		return damage;
	}
	
	public abstract int getRange();
	
	public int getReadyTime() {
		return weaponStatus.getReadyTime(readyTime);
	}
	
	public int getFireTime() {
		return weaponStatus.getFireTime(fireTime);
	}
	
	public abstract int getReloadTime();
	public abstract int getReloadOneTime();
	
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
	
	public int getSelfDamage() {
		return selfDamage;
	}
	
	public int getSelfDamageRange() {
		return selfDamageRange;
	}
	
	public HashSet<Character> getRestrictedActions() {
		return new HashSet<Character>();
	}
	
	//void methods; individual weapons can implement
	//TODO: these ought to be more general
	public void lastDamageDealt(int damageDealt) {}
	public void lastEnemyKilled(boolean enemyKilled) {}
	public void lastActionTaken(char action) {}
	public void lastEnemyActionTaken(char action) {}
	public void aim(String location) {
		damageLocation = location;
	}
	
	// these apply to every weapon
	public abstract String getBaseName();
	public abstract int getDamage(int distance);
	public abstract HashSet<Character> getWeaponActions();
	public abstract boolean isCrit();
	public abstract String getVerb();
	public abstract String getDamageType();
	public String getDamageLocation() {
		return damageLocation;
	}
	
	// these don't apply to melee types, which have no ammo (usually)
	public abstract boolean hasFullAmmo();
	public abstract boolean hasLoadedAmmo();
	public abstract void reload();
	public abstract void reloadOne();
}
