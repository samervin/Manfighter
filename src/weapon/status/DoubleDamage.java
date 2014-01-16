package weapon.status;

import game.Status;

public class DoubleDamage extends Status {

	public String toString() {
		return "Double Damage";
	}
	
	public int getDamage(int d) {
		return d*2;
	}
	
}
