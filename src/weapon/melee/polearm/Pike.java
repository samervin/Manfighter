package weapon.melee.polearm;

import game.ManfighterGenerator;

import java.util.HashSet;

public class Pike extends BasePolearm {

	public Pike() {
		weaponStatus = new ManfighterGenerator().getRandomStatus();
		damage = 130;
		range = 300;
		fireTime = 800;
		knockback = 50;
		readyTime = 550;
	}
	
	public String getBaseName() {
		return "Pike";
	}
	
	public int getDamage(int distance) {
		if(ready) {
			return weaponStatus.getDamage(damage);
		}
		return 0;
	}

	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();

		if(ready) {
			a.add('a'); //attack
			a.add('l');
		}	
		else
			a.add('r');

		return a;
	}

}
