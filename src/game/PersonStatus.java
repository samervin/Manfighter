package game;

import java.util.HashSet;

public abstract class PersonStatus {

	public abstract String toString();
	public abstract int getDamage();
	public abstract HashSet<Character> getRestrictedActions();
	public abstract void tick();
	public abstract void reset();
}
