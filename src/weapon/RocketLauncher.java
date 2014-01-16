package weapon;

import game.*;
import weapon.status.*;
import game.Weapon;

public class RocketLauncher extends Weapon {

	private int damage = 75;
	private int range = 100;
	private Status status = new BlankStatus();
	
	public RocketLauncher() {
		if(RandGen.getRand(1, 10) == 1) {
			status = new DoubleDamage();
		}
	}
	
	public String toString() {		
		if(status instanceof BlankStatus)
			return "Rocket Launcher";
		return "Rocket Launcher, with " + status.toString();
	}

	public int getDamage() {
		if(RandGen.getRand(1, 3) == 1) {
			return status.getDamage(damage);
		}
		System.out.println(this.toString() + " missed!");
		return 0;
	}

	public int getRange() {
		return range;
	}

}
