package person;

import game.ManfighterGenerator;

import java.util.HashSet;

import status.person.BlankPersonStatus;
import status.person.PersonStatus;
import weapon.Weapon;
import armor.HeadArmor;

public abstract class Person {
	
	protected PersonStatus personstatus = new BlankPersonStatus();
	protected Weapon weapon;
	protected HeadArmor headArmor = new ManfighterGenerator().getRandomHeadArmor();
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
	
	public HeadArmor getHeadArmor() {
		return headArmor;
	}
	
	public void setHeadArmor(HeadArmor ha) {
		headArmor = ha;
	}
	
	public int getDamage(int distance) {
		return weapon.getDamage(distance);
	}
	
	public int applyDamage(int dmg, String location)  {//return value is ACTUAL damage done
		if(headArmor.headshotProtected(location)) {
			System.out.println("Headshot blocked!");
			dmg = 0;
		} else {
			System.out.println("\t\tinitial damage is " + dmg);
			dmg -= headArmor.getDamageReduction();
			System.out.println("\t\tdamage reduction is: " + dmg);
			dmg = headArmor.getDamageResistance(dmg);
			System.out.println("\t\tdamage protection is: " + dmg);
			dmg = personstatus.getDamageChange(dmg);
			System.out.println("\t\tstatus change is: " + dmg);
		}
		
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
