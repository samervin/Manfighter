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
	protected Weapon weapon;
	protected int health;
	protected int location;
	protected String name;
	
	public String toString() {
		return name;
	}
	
	public int getHealth() {
		return health;
	}
	
	public Weapon getWeapon() {
		return weapon;
	}
	
	public void setWeapon(Weapon w) {
		weapon = w;
	}
	
	public int getDamage() {
		return weapon.getDamage();
	}
	
	public int applyDamage(int dmg)  {//return value is ACTUAL damage done
		dmg = personstatus.getDamageChange(dmg);
		health -= dmg;
		return dmg;
	}
	
	public void setLocation(int newLocation) {
		location = newLocation;
	}
	
	public int getLocation() {
		return location;
	}
	
	public abstract HashSet<Character> getActions();
	
	public void reset() {
		personstatus.reset();
		weapon.inflictingStatus.reset();
	}
	
	public void tick() {
		personstatus.tick();
	}
	
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
