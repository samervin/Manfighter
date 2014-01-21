package person;

import game.*;

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
	
	public char[] getActions() {
		char[] weaponReadied = {     'l', 'a', 'd', 'r'};
		char[] weaponLowered = {'e',      'a', 'd', 'r'};
		
		if(weapon.isReadied())
			return weaponReadied;
		return weaponLowered;
	}
	
}
