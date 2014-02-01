package game;

import java.util.HashSet;

import status.weapon.CritIncrease;
import status.weapon.DoubleDamage;
import status.weapon.DoubleRange;
import status.weapon.NoStatus;

public abstract class Weapon {

	protected Status status = new NoStatus();
	
	protected Status getRandomStatus() {
		int k = RandGen.getRand(1, 7);
		Status s = new NoStatus();
		switch(k) {
		case 1: s = new DoubleDamage(); break;
		case 2: s = new CritIncrease(); break;
		case 3: s = new DoubleRange(); break;
		}
		
		return s;
	}
	
	public String toString() {
		if(status instanceof NoStatus)
			return this.getBaseName();
		return this.getBaseName() + ", with " + status.toString();
	}
	
	// these apply to every weapon
	public abstract String getBaseName();
	public abstract int getDamage();
	public abstract int getRange();
	public abstract int getFireTime();
	public abstract HashSet<Character> getWeaponActions();
	public abstract boolean isCrit();
	
	public abstract String getVerb();
	
	// these don't apply to fist-types, which are always ready
	public abstract boolean isReadied(); 
	public abstract void setReadied(boolean readiness);
	
	// these don't apply to melee types, which have no ammo (usually)
	public abstract boolean hasFullAmmo();
	public abstract boolean hasLoadedAmmo();
	public abstract void reload();
}
