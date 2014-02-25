package weapon.ranged.unpowered;

import game.ManfighterGenerator;

import java.util.HashSet;

public class Shortbow extends BaseUnpowered {
	
	public Shortbow() {
		weaponStatus = new ManfighterGenerator().getRandomStatus();
		damage = 170;
		range = 400;
		maxClip = 1;
		clip = 1;
		fireTime = 700;
		knockback = 15;
		readyTime = 800;
		reloadTime = 1500;
	}

	public String getBaseName() {
		return "Short bow";
	}

	public int getDamage(int distance) {
		clip--;
		ready = false;
		if(rand.getOdds(9, 10))
			return weaponStatus.getDamage(damage);
		
		return 0;
	}

	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		if(ready) {
			a.add('a');
			a.add('l');
		}
		else
			a.add('r');
		
		return a;
	}
	
}
