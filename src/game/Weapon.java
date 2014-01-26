package game;

public abstract class Weapon {

	// these apply to every weapon
	public abstract String toString();
	public abstract String getBaseName();
	public abstract int getDamage();
	public abstract int getRange();
	
	// these don't apply to fist-types, which are always ready
	public abstract boolean isReadied(); 
	public abstract void setReadied(boolean readiness);
	
	// these don't apply to melee types, which have no ammo (usually)
	public abstract boolean hasLoadedAmmo();
	public abstract void reload();
}
