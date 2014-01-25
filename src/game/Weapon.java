package game;

public abstract class Weapon {

	public abstract String toString();
	public abstract String getBaseName();
	public abstract int getDamage();
	public abstract int getRange();
	public abstract boolean isReadied();
	public abstract void setReadied(boolean readiness);
	public abstract int getFireTime();
}
