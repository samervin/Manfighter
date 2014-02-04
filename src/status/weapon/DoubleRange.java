package status.weapon;

import game.WeaponStatus;

public class DoubleRange extends WeaponStatus {

	public String toString() {
		return "doubled range";
	}
	
	public int getRange(int r) {
		return r*2;
	}
	
}
