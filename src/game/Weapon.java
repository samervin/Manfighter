package game;

import java.util.HashSet;

import status.weapon.DoubleDamage;
import status.weapon.HalfDamage;
import status.weapon.NoStatus;

public abstract class Weapon {

	protected Status getRandomStatus() {
		int k = RandGen.getRand(1, 5);
		Status s = new NoStatus();
		switch(k) {
		case 1: s = new DoubleDamage();
		case 2: s = new HalfDamage();
		}
		
		return s;
	}
	
	// these apply to every weapon
	public abstract String toString();
	public abstract String getBaseName();
	public abstract int getDamage();
	public abstract int getRange();
	public abstract int getFireTime();
	public abstract HashSet<Character> getWeaponActions();
	
	// these don't apply to fist-types, which are always ready
	public abstract boolean isReadied(); 
	public abstract void setReadied(boolean readiness);
	
	// these don't apply to melee types, which have no ammo (usually)
	public abstract boolean hasFullAmmo();
	public abstract boolean hasLoadedAmmo();
	public abstract void reload();
	
}
