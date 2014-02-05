package status.person;

import java.util.HashSet;

import game.PersonStatus;

public class BlankPersonStatus extends PersonStatus {

	public String toString() {
		return "nothing";
	}

	public int getDamage() {
		return 0;
	}

	public HashSet<Character> getRestrictedActions() {
		return new HashSet<Character>();
	}
	
	public boolean isActive(){
		//TODO: this really isn't accurate
		return true;
	}
	
	public void tick() {
		//nothing
	}
	
	public void reset() {
		//nothing
	}

}
