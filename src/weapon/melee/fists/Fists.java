package weapon.melee.fists;

public class Fists extends BaseFists {

	private int damage = 100;
	private int range = 60;
	
	public String toString() {
		return this.getBaseName();
	}
	
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
		return 1000; //milliseconds
	}
}
