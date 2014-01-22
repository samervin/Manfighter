package weapon;
import game.*;
import weapon.status.*;

public class Fists extends Weapon {

	private int damage = 100;
	private int range = 60;
	private Status status = new BlankStatus();
	
	public String toString() {		
		if(status instanceof BlankStatus)
			return "Bare fists";
		return "Bare fists, with " + status.toString();
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
}
