package status.person;


public class Bruised extends PersonStatus {
	
	public Bruised() {
		totalTime = 10000;
	}
	
	public Bruised(int time) {
		totalTime = time;
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
