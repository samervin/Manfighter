package weapon;

import game.*;
import weapon.status.*;
import game.Weapon;

public class RocketLauncher extends Weapon {

	private int damage = 350;
	private int range = 200;
	private Status status = new BlankStatus();
	private boolean ready = false;

	public RocketLauncher() {
		if(RandGen.getRand(1, 10) == 1) {
			status = new DoubleDamage();
		}
	}

	public String toString() {
		if(status instanceof BlankStatus)
			return this.getBaseName();
		return this.getBaseName() + ", with " + status.toString();
	}

	public String getBaseName() {
		return "Rocket launcher";
	}

	public int getDamage() {
		if(ready) {
			if(RandGen.getRand(1, 3) == 1) {
				return status.getDamage(damage);
			}
		}
		else {
			if(RandGen.getRand(1, 4) == 1) {
				return status.getDamage(damage);
			}
		}

		System.out.println(this.toString() + " missed!");
		return 0;
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
