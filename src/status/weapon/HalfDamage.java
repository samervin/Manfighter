package status.weapon;

import game.Status;

public class HalfDamage extends Status {

	public String toString() {
		return "halved damage";
	}
	
	public int getDamage(int d) {
		return d/2;
	}
	
}
