package game;

import java.util.HashSet;

public abstract class PersonStatus {

	public abstract String toString();
	public void tick() {}
	public void reset() {}
	
	public int getDamage() {
		return 0;
	}
	
	public int getDamageChange(int damage) {
		return damage;
	}
	
	public HashSet<Character> getRestrictedActions() {
		return new HashSet<Character>();
	}
	
	public boolean isActive() {
		return true;
	}

}
