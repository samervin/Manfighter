package weapon;

import game.RandGen;
import game.Status;
import game.Weapon;
import weapon.status.*;

public class Dagger extends Weapon {

	private int damage = 15;
	private int range = 45;
	private Status status = new BlankStatus();
	private int swing = 0;
	
	public Dagger() {
		if(RandGen.getRand(1, 4) == 1) {
			status = new DoubleDamage();
		}
	}
	
	public String toString() {
		if(status instanceof BlankStatus)
			return "Dagger";
		return "Dagger, with " + status.toString();
	}

	public int getDamage() {
		if(swing == 0) {
			swing = 1;
			int d = status.getDamage(damage * 2);
			return d;
		}
		else {
			swing = 0;
			System.out.println("You're on the backswing -- less damage!");
			int d = status.getDamage(damage);
			return d;
		}
	}

	public int getRange() {
		int r = status.getRange(range);
		return r;
	}
}
