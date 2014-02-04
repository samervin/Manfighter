package game;

import java.util.HashSet;

import status.person.BlankPersonStatus;
import weapon.melee.club.Mace;
import weapon.melee.fists.Fists;
import weapon.melee.knife.Switchblade;
import weapon.melee.sword.Dagger;
import weapon.ranged.explosive.RocketLauncher;
import weapon.ranged.longrange.SniperRifle;
import weapon.ranged.unpowered.Shortbow;

public abstract class Person {
	
	protected PersonStatus personstatus = new BlankPersonStatus();
	
	public abstract String getName();
	public abstract int getHealth();
	protected abstract Weapon getWeapon();
	public abstract int getDamage();
	public abstract void setHealth(int newHealth);
	public abstract void setLocation(int newLocation);
	public abstract int getLocation();
	public abstract HashSet<Character> getActions();
	
	public PersonStatus getStatus() {
		return personstatus;
	}
	public void setStatus(PersonStatus ps) {
		personstatus = ps;
	}
	
	public Weapon getRandomWeapon() {
		int itemp = RandGen.getRand(1, 7);
		Weapon w;
		switch(itemp) {
		case 1: w = new Fists(); break;
		case 2: w = new Dagger(); break;
		case 3: w = new RocketLauncher(); break;
		case 4: w = new SniperRifle(); break;
		case 5: w = new Shortbow(); break;
		case 6: w = new Mace(); break;
		case 7: w = new Switchblade(); break;
		default: w = new Fists();
		}
		
		return w;
	}
}
