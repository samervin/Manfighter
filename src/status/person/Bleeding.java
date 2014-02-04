package status.person;

import java.util.HashSet;

import game.PersonStatus;

public class Bleeding extends PersonStatus {

	private final int dmg = 30;
	private final int time = 1000;
	private int counter = 1000;
	
	public String toString() {
		return "bleeding";
	}
	
	public int getDamage() {
		if(counter == 0) {
			counter = time;
			return dmg;
		}
		
		return 0;
	}
	
	public HashSet<Character> getRestrictedActions() {
		return new HashSet<Character>();
	}

	public void tick() {
		counter--;
	}
	
	public void reset() {
		counter = time;
	}

}
