package weapon.melee.fists;

import game.PersonStatus;

public class Fists extends BaseFists {

	private int damage = 100;
	private int range = 60;
	
	public String getBaseName() {
		return "Bare fists";
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getRange() {
		return range;
	}

	public int getFireTime() {
		return 750; //milliseconds
	}
	
	public PersonStatus getInflictedStatus() {
		return inflictingStatus;
	}
}
