package weapon;

import game.RandGen;
import game.Status;
import game.Weapon;
import weapon.status.*;

public class Dagger extends Weapon {

	private int damage = 100;
	private int range = 75;
	private Status status = new BlankStatus();
	private boolean ready = false;

	public Dagger() {
		if(RandGen.getRand(1, 4) == 1) {
			status = new DoubleDamage();
		}
	}

	public String toString() {
		if(status instanceof BlankStatus)
			return this.getBaseName();
		return this.getBaseName() + ", with " + status.toString();
	}

	public String getBaseName() {
		return "Dagger";
	}

	public int getDamage() {
		if(ready) {
			ready = false;
			int d = status.getDamage(damage * 2);
			return d;
		}
		else {
			ready = true;
			System.out.println("Dagger's on the backswing, less damage!");
			int d = status.getDamage(damage);
			return d;
		}
	}

	public int getRange() {
		return range;
	}

	public boolean isReadied() {
		return ready;
	}

	public void setReadied(boolean r) {
		ready = r;
	}
}
