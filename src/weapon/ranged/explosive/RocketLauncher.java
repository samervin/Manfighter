package weapon.ranged.explosive;

import game.ManfighterGenerator;

import java.util.HashSet;

public class RocketLauncher extends BaseExplosive {

	public RocketLauncher() {
		weaponStatus = new ManfighterGenerator().getRandomStatus();
		damage = 300;
		range = 1000;
		maxClip = 4;
		clip = 4;
		fireTime = 1000;
		knockback = 50;
		readyTime = 550;
		reloadTime = 4000;
		reloadOneTime = 1100; //speculation
		
		selfDamage = 200;
		selfDamageRange = 200;
	}

	public String getBaseName() {
		return "Rocket launcher";
	}
	
	public String getVerb() {
		return "rocketed";
	}

	public int getDamage(int distance) {
		clip--;
		
		if(ready) {
			if(rand.getOdds(4, 5)) {
				return damage;
			}
		} else {
			if(rand.getOdds(2, 5)) {
				return damage;
			}
		}
		return 0;
	}
	
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		if(this.hasLoadedAmmo())
			a.add('a'); //attack
		if(!this.hasFullAmmo()) {
			a.add('o'); //reload
			a.add('p'); //reload one
		}
		
		if(ready) {
			a.add('i');
			a.add('l');
		}
		else
			a.add('r');
		
		return a;
	}
}
