package status.person;

import java.util.HashSet;

import game.PersonStatus;

public class Rooted extends PersonStatus {
	
	public Rooted() {
		totalTime = 4000;
	}
	
	public Rooted(int time) {
		totalTime = time;
	}
	
	public String toString() {
		return "rooted";
	}
	
	public HashSet<Character> getRestrictedActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('d');
		a.add('e');
		a.add('m');
		return a;
	}

}
