package weapon.melee.axe;

import game.ManfighterGenerator;

public class Hatchet extends BaseAxe {

	public Hatchet() {
		weaponStatus = new ManfighterGenerator().getRandomNeutralStatus();
		damage = 215;
		range = 15;
		fireTime = defaultFireTime;
		readyTime = 550;
		
		stuck = false;
		defaultFireTime = 1000;
		stuckFireTime = 1400;
		stuckOdds = 35;
		unStuckOdds = 20;
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
}
