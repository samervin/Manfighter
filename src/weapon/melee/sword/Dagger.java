package weapon.melee.sword;

import java.util.HashSet;

public class Dagger extends BaseSword {

	public Dagger() {
		weaponStatus = getRandomStatus();
		damage = 200;
		range = 75;
		swingTime = 1000;
	}

	public String getBaseName() {
		return "Dagger";
	}

	public int getDamage() {
		if(ready) {
			ready = false;
			int d = weaponStatus.getDamage(damage);
			return d;
		}
		else {
			ready = true;
			System.out.println("Dagger's on the backswing, less damage!");
			int d = weaponStatus.getDamage(damage / 2);
			return d;
		}
	}
	
	public int getFireTime() {
		if(ready)
			return weaponStatus.getAttackSpeed(swingTime);
		else
			return weaponStatus.getAttackSpeed(3 * swingTime / 2);
	}
	
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('a'); //attack
		
		if(ready)
			a.add('l');
		else
			a.add('e');
		
		return a;
	}
}
