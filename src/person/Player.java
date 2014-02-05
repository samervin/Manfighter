package person;

import game.Person;
import game.Weapon;

import java.util.HashSet;

public class Player extends Person {

	private String name;
	private int health = 750;
	private int location;

	public Player(String n) {
		name = n;
		weapon = getRandomWeapon();
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
		
		//TODO: these need to matter
		a.add('m'); //move
		a.add('w'); //wait
				
		HashSet<Character> restrictions = personstatus.getRestrictedActions();
		for(Character c : restrictions) {
			if(a.contains(c)) {
				a.remove(c);
			}
		}
		
		return a;
	}	

}
