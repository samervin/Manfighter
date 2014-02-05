package status.person;

import game.PersonStatus;

import java.util.HashSet;

public class Stunned extends PersonStatus {
	
	private final int totalTime = 3000;
	private int counter = 0;

	public String toString() {
		return "stunned";
	}

	public int getDamage() {
		return 0;
	}

	public HashSet<Character> getRestrictedActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('a');
		a.add('e');
		a.add('l');
		a.add('d');
		a.add('r');
		a.add('o');
		a.add('m');
		return a;
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
