package status.person;

import game.PersonStatus;

import java.util.HashSet;

public class Stunned extends PersonStatus {

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

	public void tick() {
		//nothing yet
	}
	
	public void reset() {
		//nothing yet
	}

}
