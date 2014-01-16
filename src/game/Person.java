package game;

public abstract class Person {
	
	public abstract String getName();
	public abstract int getHealth();
	public abstract Weapon getWeapon();
	public abstract int getDamage();
	public abstract void setHealth(int newHealth);
}
