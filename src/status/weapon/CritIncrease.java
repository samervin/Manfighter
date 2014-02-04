package status.weapon;

import game.RandGen;
import game.WeaponStatus;

public class CritIncrease extends WeaponStatus {

	public String toString() {
		return "more luck";
	}
	
	public boolean getCritChance() {
		return (RandGen.getRand(1, 4) == 1);
	}

}
