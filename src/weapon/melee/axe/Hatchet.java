package weapon.melee.axe;

import java.util.HashSet;

public class Hatchet extends BaseAxe {

	private boolean stuck = false;
	private int defaultFireTime = 1000;
	private int stuckFireTime = 1400;

	public Hatchet() {
		weaponStatus = getRandomStatus();
		damage = 215;
		range = 15;
		fireTime = defaultFireTime;
		readyTime = 550;
	}

	public String getBaseName() {
		return "Hatchet";
	}

	public int getDamage() {
		if(!stuck) {
			if(ready) {
				ready = false;
				int d = weaponStatus.getDamage(damage);
				return d;
			} else {
				ready = true;
				System.out.println(getBaseName() + " is on the backswing, less damage.");
				int d = weaponStatus.getDamage(damage / 2);
				return d;
			}
		} else {
			System.out.println(getBaseName() + " is stuck!");
			int d = weaponStatus.getDamage(damage * 2);
			return d;
		}
	}

	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		if(!stuck) {
			a.add('a'); //attack

			if(ready)
				a.add('l');
			else
				a.add('r');
		} else {
			a.add('a');
		}
		return a;
	}

	public void lastDamageDealt(int dam) {
		if(dam > 0) {
			if(!stuck && stuckOdds()) { //not already stuck, becoming stuck
				stuck = true;
				fireTime = stuckFireTime;
				System.out.println(getBaseName() + " got stuck!");
			} else if(stuck && unStuckOdds()) { //already stuck, becoming unstuck
				stuck = false;
				fireTime = defaultFireTime;
				System.out.println(getBaseName() + " got unstuck!");
			}
		}
	}

	public void lastEnemyKilled(boolean en) {
		if(en) {
			stuck = false;
			fireTime = defaultFireTime;
		}
	}

	public void lastActionTaken(char action) {
		if(stuck) {
			switch(action) {
			case 'd': System.out.println(getBaseName() + " got unstuck!"); stuck = false; break;
			case 'e': System.out.println(getBaseName() + " got unstuck!"); stuck = false; break;
			case 'm': System.out.println(getBaseName() + " got unstuck!"); stuck = false; break;
			}
		}
	}
	
	public void lastEnemyActionTaken(char action) {
		lastActionTaken(action);
	}

	private boolean stuckOdds() {
		if(rand.getOdds(1, 3)) {
			return true;
		}
		return false;
	}

	private boolean unStuckOdds() {
		if(rand.getOdds(1, 5)) {
			return true;
		}
		return false;
	}
}
