package person;

import game.*;

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
	
	public char[] getActions() {
		char[] weaponReadied = {     'l', 'a', 'd', 'r'};
		char[] weaponLowered = {'e',      'a', 'd', 'r'};
		
		if(weapon.isReadied())
			return weaponReadied;
		return weaponLowered;
	}
	
	
	
	
	
	
	protected String createRandomName() {
		String n;
		int x = RandGen.getRand(1,3);
		switch(x) {
			case 1: n = "Willem"; break;
			case 2: n = "Skeletor"; break;
			case 3: n = "Dr. Neo Cortex"; break;
			default: n = "Programmer Sam";
		}
		
		x = RandGen.getRand(1,6);
		switch(x) {
			case 1: n+= " DaFoe"; break;
			case 2: n+= " the Outrageous"; break;
			case 3: n+= " the Particularly Smelly"; break;
			case 4: n+= " the Terrible"; break;
		}
		
		return n;
	}
}