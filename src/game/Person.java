package game;

import java.util.HashSet;

import status.person.BlankPersonStatus;

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
	
	public int getDamage(int distance) {
		return weapon.getDamage(distance);
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
	
	public abstract HashSet<Character> getValidActions();
	public String getAction(int distance) {	return "#";	}
	
	public void tick() {
		personstatus.tick();
	}
	
	public PersonStatus getStatus() {
		return personstatus;
	}
	public void setStatus(PersonStatus ps) {
		personstatus = ps;
	}
}
