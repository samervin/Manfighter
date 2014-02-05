package status.person;

import java.util.HashSet;

import game.PersonStatus;

public class Bleeding extends PersonStatus {

	private final int dmg = 30;
	private final int timeBetween = 1000;
	private final int totalTime = 5000;
	private int counter = 0;
	
	public String toString() {
		return "bleeding";
	}
	
	public int getDamage() {
		if(counter % timeBetween == 0) {
			return dmg;
		}
		
		return 0;
	}
	
	public HashSet<Character> getRestrictedActions() {
		return new HashSet<Character>();
	}
	
	public boolean isActive() {
		if(counter >= totalTime) {
			return false;
		}
		
		return true;
		
	}

	public void tick() {
		counter++;
	}
	
	public void reset() {
		counter = 0;
	}

}
