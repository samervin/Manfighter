package weapon.melee.axe;

import game.ManfighterGenerator;

import java.util.HashSet;

public class Hatchet extends BaseAxe {

	private boolean stuck = false;
	private int defaultFireTime = 1000;
	private int stuckFireTime = 1400;

	public Hatchet() {
		weaponStatus = new ManfighterGenerator().getRandomNeutralStatus();
		damage = 215;
		range = 15;
		fireTime = defaultFireTime;
		readyTime = 550;
	}

	public String getBaseName() {
		return "Hatchet";
	}

	public int getDamage(int distance) {
		if(!stuck) {
			if(ready) {
				ready = false;
				return damage;
			} else {
				ready = true;
				System.out.println(getBaseName() + " is on the backswing, less damage.");
				return (damage / 2);
			}
		} else {
			System.out.println(getBaseName() + " is stuck!");
			return (damage * 2);
		}
	}

	@Override
	public String getDamageType() {
		if(stuck)
			return "penetrating";
		else
			return "slicing";
	}

	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		if(!stuck) {
			if(ready) {
				a.add('l');
				a.add('i'); //aim
			}
			else
				a.add('r');
		}
		a.add('a');
		return a;
	}

	public HashSet<Character> getRestrictedActions() {
		HashSet<Character> a = new HashSet<Character>();
		if(stuck) {
			a.add('e');
			a.add('m');
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

	public void lastEnemyActionTaken(char action) {
		if(stuck) {
			switch(action) {
			case 'e': System.out.println(getBaseName() + " got unstuck!"); stuck = false; break;
			case 'm': System.out.println(getBaseName() + " got unstuck!"); stuck = false; break; //enemies currently don't move
			}
		}
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
