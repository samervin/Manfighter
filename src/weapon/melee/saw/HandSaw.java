package weapon.melee.saw;

import java.util.HashSet;

public class HandSaw extends BaseSaw {

	private int consecutives = 1;
	
	public HandSaw() {
		weaponStatus = getRandomStatus();
		damage = 120;
		range = 90;
		swingTime = 1100;
	}
	
	public String getBaseName() {
		return "Hand saw";
	}
	
	public int getDamage() {
		if(ready) {
			int d = weaponStatus.getDamage(damage);
			d *= consecutives;
			return d;
		} else {
			int d = weaponStatus.getDamage(damage);
			return d;
		}
	}

	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('a');
		
		if(ready)
			a.add('l');
		else
			a.add('r');
		
		return a;
	}
	
	public void lastDamageDealt(int dam) {
		if(dam > 0)
			consecutives++;
		else
			consecutives = 1;
	}

}
