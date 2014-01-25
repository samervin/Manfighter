package weapon;
import game.*;
import weapon.status.*;

public class Fists extends Weapon {

	private int damage = 100;
	private int range = 60;
	private Status status = new BlankStatus();
	
	public String toString() {
		if(status instanceof BlankStatus)
			return this.getBaseName();
		return this.getBaseName() + ", with " + status.toString();
	}
	
	public String getBaseName() {
		return "Bare fists";
	}
	
	public int getDamage() {
		int d = status.getDamage(damage);
		return d;
	}
	
	public int getRange() {
		return range;
	}
	
	public boolean isReadied() {
		return false; //fists are trivially always ready -- no movement penalty
	}
	
	public void setReadied(boolean r) {
		//do nothing, invalid
	}

	public int getFireTime() {
		return 1000; //milliseconds
	}
	
}
