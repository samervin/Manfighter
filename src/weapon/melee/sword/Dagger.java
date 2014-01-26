package weapon.melee.sword;

import game.RandGen;
import game.Status;
import status.weapon.BaseStatus;
import status.weapon.DoubleDamage;

public class Dagger extends BaseSword {

	private int damage = 100;
	private int range = 75;
	private Status status = new BaseStatus();
	private boolean ready = false;

	public Dagger() {
		if(RandGen.getRand(1, 4) == 1) {
			status = new DoubleDamage();
		}
	}

	public String toString() {
		if(status instanceof BaseStatus)
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
