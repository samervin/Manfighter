package status.weapon;

import game.Status;

public class DoubleDamage extends Status {

	public String toString() {
		return "doubled damage";
	}
	
	public int getDamage(int d) {
		return d*2;
	}
	
}
