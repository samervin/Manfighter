package status.person;

import game.PersonStatus;

public class BlankPersonStatus extends PersonStatus {

	public String toString() {
		return "nothing";
	}

	public int getDamagePerHit() {
		return 0;
	}
	
	public int getTimeBetweenHits() {
		return 0;
	}

}
