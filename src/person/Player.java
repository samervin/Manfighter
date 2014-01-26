package person;

import game.Person;
import game.Weapon;

import java.util.HashSet;

import weapon.melee.fists.Fists;
import weapon.ranged.longrange.SniperRifle;

public class Player extends Person {

	private String name;
	private int health = 500;
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

		HashSet<Character> a = new HashSet<Character>(); //regular arrays are hard
		//a.add('e'); //ready weapon
		//a.add('l'); //lower weapon
		if(weapon.hasLoadedAmmo())
			a.add('a'); //attack
		a.add('d'); //advance
		a.add('r'); //retreat

		if(weapon.isReadied())
			a.add('l');
		else
			a.add('e');
		
		if(!weapon.hasFullAmmo())
			a.add('o'); //reload
		
		if(weapon instanceof SniperRifle)
			a.add('e'); //can always ready a sniper rifle
		else if(weapon instanceof Fists) {
			a.remove('e'); //fists are trivially always ready
		}
			
		
		
		
		return a;
	}

}
