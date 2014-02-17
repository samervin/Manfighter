package game;

import java.util.ArrayList;
import java.util.HashSet;

import status.person.BlankPersonStatus;
import status.weapon.AccuracyDown;
import status.weapon.BlankWeaponStatus;
import status.weapon.CritUp;
import status.weapon.DamageUp;
import status.weapon.FireRateUp;
import status.weapon.RangeUp;

public abstract class Weapon {

	//TODO: should really be sets/lists
	protected WeaponStatus weaponStatus = new BlankWeaponStatus();
	protected PersonStatus inflictingStatus = new BlankPersonStatus();
	protected final PersonStatus blankInflictingStatus = new BlankPersonStatus();
	
	protected int damage;
	protected int range;
	protected boolean ready = false;
	protected int fireTime;
	protected int knockback = 0;
	
	//shared methods
	protected WeaponStatus getRandomStatus() {
		ArrayList<WeaponStatus> allStati = new ArrayList<WeaponStatus>();
		allStati.add(new DamageUp());
		allStati.add(new CritUp());
		allStati.add(new RangeUp());
		allStati.add(new AccuracyDown());
		allStati.add(new FireRateUp());
		
		int size = allStati.size();
		for(int i = 0; i < size; i++)
			allStati.add(new BlankWeaponStatus());
		
		RandGen rand = new RandGen();
		int x = rand.getRand(0, allStati.size()-1);
		return allStati.get(x);
	}
	
	public String toString() {
		if(weaponStatus instanceof BlankWeaponStatus)
			return this.getBaseName();
		return weaponStatus + " " + this.getBaseName().substring(0,1).toLowerCase() + this.getBaseName().substring(1);
	}
	
	
	//void methods; individual weapons can implement
	//TODO: this ought to be more general
	public void lastDamageDealt(int damageDealt) {}
	
	
	// these apply to every weapon
	public abstract String getBaseName();
	public abstract int getDamage();
	public abstract int getRange();
	public abstract int getFireTime();
	public abstract HashSet<Character> getWeaponActions();
	public abstract boolean isCrit();
	public abstract String getVerb();
	public abstract PersonStatus getInflictedStatus();
	public abstract int getKnockback();
	
	// these don't apply to fists/knives
	public abstract boolean isReadied();
	public abstract void setReadied(boolean readiness);
	
	// these don't apply to melee types, which have no ammo (usually)
	public abstract boolean hasFullAmmo();
	public abstract boolean hasLoadedAmmo();
	public abstract void reload();
}
