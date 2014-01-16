package person;

import game.*;

public class Player extends Person {

	private String name;
	private int health = 100;
	private Weapon weapon;
	
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

	
}
