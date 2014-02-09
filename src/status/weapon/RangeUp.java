package status.weapon;

import game.WeaponStatus;

public class RangeUp extends WeaponStatus {

	public String toString() {
		return "Long-range";
	}
	
	public int getRange(int r) {
		return r*2;
	}
	
}
