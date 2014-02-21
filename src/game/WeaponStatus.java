package game;

public abstract class WeaponStatus {

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
	
	public int getFireTime(int s) {
		return s;
	}
	
	public int getReadyTime(int r) {
		return r;
	}
	
}
