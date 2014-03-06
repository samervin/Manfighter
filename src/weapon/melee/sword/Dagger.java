package weapon.melee.sword;

import game.ManfighterGenerator;

import java.util.HashSet;

public class Dagger extends BaseSword {

	public Dagger() {
		weaponStatus = new ManfighterGenerator().getRandomStatus();
		damage = 200;
		range = 15;
		fireTime = 1000;
		readyTime = 550;
	}

	public String getBaseName() {
		return "Dagger";
	}

	public int getDamage(int distance) {
		if(ready) {
			ready = false;
			return damage;
		}
		else {
			ready = true;
			System.out.println(getBaseName() + " is on the backswing, less damage.");
			return (damage / 2);
		}
	}
	
	public int getFireTime() {
		if(ready)
			return weaponStatus.getFireTime(fireTime);
		else
			return weaponStatus.getFireTime(3 * fireTime / 2);
	}
	
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('a'); //attack
		
		if(ready) {
			a.add('l');
			a.add('i');
		}
		else
			a.add('r');
		
		return a;
	}
}
