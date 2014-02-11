package status.person;

import game.PersonStatus;

public class Bruised extends PersonStatus {
	
	public Bruised() {
		totalTime = 10000;
	}
	
	public String toString() {
		return "bruised";
	}
	
	public int getDamageChange(int dmg) {
		dmg *= 4;
		dmg /= 3; //33% increase
		return dmg;
	}	
	
}
