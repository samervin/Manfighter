package status.person;


public class BlankPersonStatus extends PersonStatus {
	
	public boolean isActive() {
		return true; //prevents excessive checking
	}
	
	public String toString() {
		return "nothing";
	}

}
