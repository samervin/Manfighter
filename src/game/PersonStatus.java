package game;

import java.util.HashSet;

public abstract class PersonStatus {

	protected int dmg;
	protected int totalTime;
	protected int start = 0;
	protected int current = 0;
	protected boolean isActive = false;
	
	public abstract String toString();
	
	public void tick() {
		current++;
		check();
	}
	
	public void initialize(int startTime) {
		start = startTime;
		current = startTime;
		isActive = true;
	}
	
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
		return isActive;
	}
	
	protected void check() {
		if((current-start) > totalTime) {
			isActive = false;
			start = 0;
			current = 0;
		}
	}

}
