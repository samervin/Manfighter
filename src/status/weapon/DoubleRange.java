package status.weapon;

import game.Status;

public class DoubleRange extends Status {

	public String toString() {
		return "doubled range";
	}
	
	public int getRange(int r) {
		return r*2;
	}
	
}
