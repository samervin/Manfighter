package status.weapon;

import game.WeaponStatus;

public class DoubleDamage extends WeaponStatus {

	public String toString() {
		return "doubled damage";
	}
	
	public int getDamage(int d) {
		return d*2;
	}
	
}
