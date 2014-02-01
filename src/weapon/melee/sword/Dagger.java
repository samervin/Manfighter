package weapon.melee.sword;

import java.util.HashSet;

public class Dagger extends BaseSword {

	private int damage = 100;
	private int range = 75;
	private boolean ready = false;

	public Dagger() {
		status = getRandomStatus();
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
		return status.getRange(range);
	}

	public boolean isReadied() {
		return ready;
	}

	public void setReadied(boolean r) {
		ready = r;
	}
	
	public int getFireTime() {
		if(ready)
			return 1000;
		else
			return 1500;
	}
	
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('a'); //attack
		
		if(ready)
			a.add('l');
		else
			a.add('e');
		
		return a;
	}
}
