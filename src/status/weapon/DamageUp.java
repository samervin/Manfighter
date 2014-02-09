package status.weapon;

import game.WeaponStatus;

public class DamageUp extends WeaponStatus {

	public String toString() {
		return "Damaging";
	}
	
	public int getDamage(int d) {
		d *= 3;
		d /= 2; //1.5x increase
		return d;
	}
	
}
