package weapon.melee.sword;

import game.ManfighterGenerator;

import java.util.HashSet;

public class Dagger extends BaseSword {

	private final int readiedDamage = 200;
	private final int unreadiedDamage = 120;
	
	public Dagger() {
		weaponStatus = new ManfighterGenerator().getRandomNeutralStatus();
		damage = unreadiedDamage;
		range = 15;
		fireTime = 1000;
		readyTime = 550;
	}

	public String getBaseName() {
		return "Dagger";
	}
	
	@Override
	public void setReadied(boolean readiness) {
		ready = readiness;
		if(readiness)
			damage = readiedDamage;
		else
			damage = unreadiedDamage;
	}

	public int getDamage(int distance) {		
		if(ready) {
			setReadied(false);
			return damage;
		}
		else {
			setReadied(true);
			System.out.println(getBaseName() + " is on the backswing, less damage.");
			return damage;
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
