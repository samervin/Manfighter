package weapon;

import weapon.status.*;
import game.*;

public class SniperRifle extends Weapon {

	private int damage = 75;
	private int range = 1000; //a lot
	private Status status = new BlankStatus();
	private int ready = 0; //0 = from the hip, 1-6 = tracking, 7+ = headshot

	public String toString() {		
		if(status instanceof BlankStatus)
			return "Sniper Rifle";
		return "Sniper Rifle, with " + status.toString();
	}

	public int getDamage() {
		if(ready == 0) {
			if(RandGen.getRand(1, 8) == 1)
				return status.getDamage(damage);
			else
				return 0;
		} else if(ready <= 3) {
			if(RandGen.getRand(1, 5) == 1) {
				return (ready * 5) + status.getDamage(damage);
			} else
				return 0;
		} else if(ready <= 6) {
			if(RandGen.getRand(1, 3) == 1) {
				System.out.println("A shot on a vital organ!");
				return (ready * 12) + status.getDamage(damage);
			} else
				return 0;
		}

		System.out.println("A headshot!");
		return ready * status.getDamage(damage);
	}

	public int getRange() {
		return range;
	}

	public boolean isReadied() {
		if(ready != 0)
			return true;
		return false;
	}

	public void setReadied(boolean readiness) {
		if(readiness)
			ready++;
		else
			ready = 0;
	}

}
