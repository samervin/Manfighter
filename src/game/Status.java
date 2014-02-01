package game;

public abstract class Status {

	public abstract String toString();
	
	public int getDamage(int d) {
		return d;
	}
	
	public int getRange(int r) {
		return r;
	}
	
	public boolean getCritChance() {
		return false;
	}
}
