package game;

import java.util.ArrayList;
import java.util.HashSet;

import status.person.BlankPersonStatus;
import weapon.melee.club.Mace;
import weapon.melee.fists.Fists;
import weapon.melee.hammer.WarHammer;
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
		ArrayList<Weapon> allWeapons = new ArrayList<Weapon>();
		allWeapons.add(new Mace());
		allWeapons.add(new Fists());
		allWeapons.add(new WarHammer());
		allWeapons.add(new Switchblade());
		allWeapons.add(new Dagger());
		allWeapons.add(new RocketLauncher());
		allWeapons.add(new SniperRifle());
		allWeapons.add(new Shortbow());
		
		RandGen rand = new RandGen();
		int x = rand.getRand(0, allWeapons.size() - 1);
		return allWeapons.get(x);
	}
}
