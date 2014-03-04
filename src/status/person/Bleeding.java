package status.person;


public class Bleeding extends PersonStatus {
	
	private int timeBetween = 1000;
	
	public Bleeding() {
		dmg = 35;
		totalTime = 5000;
	}
	
	public Bleeding(int time) {
		dmg = 30;
		totalTime = time;
	}
	
	public String toString() {
		return "bleeding";
	}

	public int getDamage() {
		if((current-start)%timeBetween == 0 && current != start) {
			check();
			return dmg;
		}
		return 0;
	}
	
}
