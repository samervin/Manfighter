package weapon.melee.saw;

import game.ManfighterGenerator;

import java.util.HashSet;

public class HandSaw extends BaseSaw {

	private int consecutives = 1;
	
	public HandSaw() {
		weaponStatus = new ManfighterGenerator().getRandomNeutralStatus();
		damage = 120;
		range = 30;
		fireTime = 1100;
		readyTime = 550;
	}
	
	public String getBaseName() {
		return "Hand saw";
	}
	
	@Override
	public void reset() {
		super.reset();
		consecutives = 1;
	}
	
	public int getDamage(int distance) {
		if(ready) {
			return (damage * consecutives);
		} else {
			int d = damage * consecutives;
			return (d / 2);
		}
	}
	
	public void setReadied(boolean readiness) {
		ready = readiness;
		consecutives = 1;
	}

	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('a');
		
		if(ready) {
			a.add('l');
			a.add('i');
		}
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
