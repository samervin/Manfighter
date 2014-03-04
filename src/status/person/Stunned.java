package status.person;


import java.util.HashSet;

public class Stunned extends PersonStatus {
	
	public Stunned() {
		totalTime = 3000;
	}
	
	public Stunned(int time) {
		totalTime = time;
	}
	
	public String toString() {
		return "stunned";
	}

	public HashSet<Character> getRestrictedActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('a');
		a.add('r');
		a.add('l');
		a.add('d');
		a.add('e');
		a.add('o');
		a.add('m');
		return a;
	}
}
