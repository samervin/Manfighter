package weapon.ranged.unpowered;

import java.util.HashSet;

public class Shortbow extends BaseUnpowered {
	
	public Shortbow() {
		weaponStatus = getRandomStatus();
		damage = 170;
		range = 400;
		fireTime = 975;
	}

	public String getBaseName() {
		return "Short bow";
	}

	public int getDamage() {
		ready = false;
		return weaponStatus.getDamage(damage);
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
