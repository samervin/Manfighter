package person;

import game.Person;
import game.Weapon;

import java.util.HashSet;

public class Player extends Person {

	private String name;
	private int health = 750;
	private Weapon weapon;
	private int location;

	public Player(String n, Weapon w) {
		name = n;
		weapon = w;
	}

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

		HashSet<Character> a = weapon.getWeaponActions(); //covers attack, ready, lower, reload
		a.add('d'); //advance
		a.add('r'); //retreat
				
		return a;
	}

}
