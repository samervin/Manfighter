package person;

import game.Person;
import game.RandGen;
import game.Weapon;

import java.util.HashSet;

public abstract class Enemy extends Person {

	protected String name;
	protected int health;
	protected Weapon weapon;
	protected int location;

	public abstract char getAction();

	public int getDamage() {
		return weapon.getDamage();
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int h) {
		health = h;
	}

	public String getName() {
		return name;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setLocation(int l){
		location = l;
	}

	public int getLocation() {
		return location;
	}

	public HashSet<Character> getActions() {

		HashSet<Character> a = weapon.getWeaponActions();
		a.add('d'); //advance
		a.add('r'); //retreat

		return a;
	}






	protected String createRandomName() {
		String n = "";
		int x = RandGen.getRand(1, 3);
		switch(x) {
		case 1: n += "Dr. "; break;
		}

		x = RandGen.getRand(1,3);
		switch(x) {
		case 1: n += "Willem"; break;
		case 2: n += "Skeletor"; break;
		case 3: n += "Neo"; break;
		default: n += "ERROR";
		}

		x = RandGen.getRand(1,8);
		switch(x) {
		case 1: n+= " DaFoe"; break;
		case 2: n+= " the Outrageous"; break;
		case 3: n+= " the Particularly Smelly"; break;
		case 4: n+= " the Terrible"; break;
		}

		return n;
	}
}