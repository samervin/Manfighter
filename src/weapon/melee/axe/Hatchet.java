package weapon.melee.axe;

import game.RandGen;

import java.util.HashSet;

public class Hatchet extends BaseAxe {

	private boolean stuck = false;
	
	public Hatchet() {
		weaponStatus = getRandomStatus();
		damage = 215;
		range = 75;
		fireTime = 1000;
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
			//TODO: this should reduce fire time a lot to be fair
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
				System.out.println(getBaseName() + " got stuck!");
			} else if(stuck && stuckOdds()) { //already stuck, becoming unstuck
				stuck = false;
				System.out.println(getBaseName() + " got unstuck!");
			}
		}
	}

	//TODO: separate odds chances?
	//TODO: this also doesn't correctly update if killing a stuck enemy
	private boolean stuckOdds() {
		RandGen rand = new RandGen();
		if(rand.getOdds(1, 3)) {
			return true;
		}
		
		return false;
	}
}
