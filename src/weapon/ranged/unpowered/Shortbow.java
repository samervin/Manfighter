package weapon.ranged.unpowered;

import game.Status;

import java.util.HashSet;

import status.weapon.NoStatus;

public class Shortbow extends BaseUnpowered {

	private int damage = 175;
	private int range = 400;
	private Status status = new NoStatus();
	private boolean ready = false;
	
	public Shortbow() {
		status = getRandomStatus();
	}
	
	public String toString() {
		if(status instanceof NoStatus)
			return this.getBaseName();
		return this.getBaseName() + ", with " + status.toString();
	}

	public String getBaseName() {
		return "Short bow";
	}

	public int getDamage() {
		ready = false;
		return status.getDamage(damage);
	}

	public int getRange() {
		return range;
	}

	public int getFireTime() {
		return 975;
	}

	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		if(ready) {
			a.add('a');
			a.add('l');
		}
		else
			a.add('e');
		
		return a;
	}

	public boolean isReadied() {
		return ready;
	}

	public void setReadied(boolean readiness) {
		ready = readiness;
		
	}
	
}
