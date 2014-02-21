package weapon.ranged.unpowered;

import java.util.HashSet;

public class Shortbow extends BaseUnpowered {
	
	public Shortbow() {
		weaponStatus = getRandomStatus();
		damage = 170;
		range = 400;
		fireTime = 975;
		knockback = 15;
		readyTime = 550;
	}

	public String getBaseName() {
		return "Short bow";
	}

	public int getDamage() {
		ready = false;
		if(rand.getOdds(9, 10))
			return weaponStatus.getDamage(damage);
		else
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
