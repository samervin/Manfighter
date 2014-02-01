package status.weapon;

import game.RandGen;
import game.Status;

public class CritIncrease extends Status {

	public String toString() {
		return "more luck";
	}
	
	public boolean getCritChance() {
		return (RandGen.getRand(1, 4) == 1);
	}

}
