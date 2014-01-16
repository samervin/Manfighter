package weapon;
import game.*;
import weapon.status.*;

public class Fists extends Weapon {

	private int damage = 10;
	private int range = 30;
	private Status status = new BlankStatus();
	
	public Fists() {
		if(RandGen.getRand(0,1) == 1) {
			status = new DoubleDamage();
		}
	}
	
	public String toString() {
		return "Bare fists, with " + status.toString();
	}
	
	public int getDamage() {
		int d = status.getDamage(damage);
		return d;
	}
	
	public int getRange() {
		int r = status.getRange(range);
		return r;
	}
}
