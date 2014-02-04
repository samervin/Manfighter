package game;

import java.util.HashSet;

import status.person.BlankPersonStatus;
import status.weapon.BlankWeaponStatus;
import status.weapon.CritIncrease;
import status.weapon.DoubleDamage;
import status.weapon.DoubleRange;

public abstract class Weapon {

	protected WeaponStatus weaponStatus = new BlankWeaponStatus();
	protected PersonStatus inflictingStatus = new BlankPersonStatus();
	
	protected WeaponStatus getRandomStatus() {
		int k = RandGen.getRand(1, 7);
		WeaponStatus s = new BlankWeaponStatus();
		switch(k) {
		case 1: s = new DoubleDamage(); break;
		case 2: s = new CritIncrease(); break;
		case 3: s = new DoubleRange(); break;
		}
		
		return s;
	}
	
	public String toString() {
		if(weaponStatus instanceof BlankWeaponStatus)
			return this.getBaseName();
		return this.getBaseName() + ", with " + weaponStatus.toString();
	}
	
	// these apply to every weapon
	public abstract String getBaseName();
	public abstract int getDamage();
	public abstract int getRange();
	public abstract int getFireTime();
	public abstract HashSet<Character> getWeaponActions();
	public abstract boolean isCrit();
	public abstract String getVerb();
	public abstract PersonStatus getInflictedStatus();
	
	// these don't apply to fist-types, which are always ready
	public abstract boolean isReadied(); 
	public abstract void setReadied(boolean readiness);
	
	// these don't apply to melee types, which have no ammo (usually)
	public abstract boolean hasFullAmmo();
	public abstract boolean hasLoadedAmmo();
	public abstract void reload();
}
