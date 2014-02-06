package status.person;

import game.PersonStatus;

public class Bruised extends PersonStatus {

	public String toString() {
		return "bruised";
	}
	
	public int getDamageChange(int dmg) {
		dmg *= 6;
		dmg /= 5; //20% increase
		return dmg;
	}
	
}
