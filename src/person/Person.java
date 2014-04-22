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
	
	public String getFullInfo() {
		String s = String.format("Health: %d\nStatus: %s\nHead armor: %s\nLocation: %d", 
				health, personstatus.toString(), headArmor.toString(), location);
		s = s + "\n\n" + weapon.getFullInfo();
		return s.replaceAll("\t", "    ");
	}
	
	public int getDamage(int distance) {
		return weapon.getWeaponStatus().getDamage(weapon.getDamage(distance));
	}
	
	public int applyDamage(int dmg, String location)  {//return value is ACTUAL damage done		
		if(location.equals("head")) {
			if(!headArmor.headshotProtected()) {
				System.out.println("Headshot!");
				dmg = dmg * 4;
				dmg = dmg / 3;
			} else {
				System.out.println("Headshot deflected!");
				return 0;
			}
		}
		
		dmg -= headArmor.getDamageReduction();
		dmg = headArmor.getDamageResistance(dmg);
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
