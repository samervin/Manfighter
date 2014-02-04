package status.person;

import game.PersonStatus;

public class Bleeding extends PersonStatus {

	public String toString() {
		return "bleeding";
	}
	
	public int getDamagePerHit() {
		return 30;
	}
	
	public int getTimeBetweenHits() {
		return 1000;
	}

}
