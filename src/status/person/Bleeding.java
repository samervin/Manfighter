package status.person;

import game.PersonStatus;

public class Bleeding extends PersonStatus {
	
	private int timeBetween = 1000;
	
	public Bleeding() {
		dmg = 30;
		totalTime = 5000;
	}
	
	public String toString() {
		return "bleeding";
	}

	public int getDamage() {
		if((current-start)%timeBetween == 0 && current != start) {
			check();
			return dmg;
		}
		return 0;
	}
	
}
