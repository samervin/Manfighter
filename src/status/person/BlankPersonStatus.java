package status.person;

import game.PersonStatus;

public class BlankPersonStatus extends PersonStatus {
	
	public boolean isActive() {
		return true; //prevents excessive checking
	}
	
	public String toString() {
		return "nothing";
	}

}
