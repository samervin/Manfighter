package weapon.ranged.explosive;

import game.RandGen;

import java.util.HashSet;

public class RocketLauncher extends BaseExplosive {

	public RocketLauncher() {
		weaponStatus = getRandomStatus();
		damage = 300;
		range = 500;
		maxClip = 4;
		clip = 4;
		fireTime = 2000;
	}

	public String getBaseName() {
		return "Rocket launcher";
	}

	public int getDamage() {
		clip--;
		RandGen rand = new RandGen();
		
		if(ready) {
			if(rand.getRand(1, 5) > 1) {
				return weaponStatus.getDamage(damage);
			}
		}
		else {
			if(rand.getRand(1, 5) > 3) {
				return weaponStatus.getDamage(damage);
			}
		}
		
		return 0;
	}
	
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		if(this.hasLoadedAmmo())
			a.add('a'); //attack
		if(!this.hasFullAmmo())
			a.add('o'); //reload
			
		if(ready)
			a.add('l');
		else
			a.add('r');
		
		return a;
	}
}
