package weapon.melee.other;

import game.ManfighterGenerator;

import java.util.HashSet;

import weapon.WeaponMelee;

public class CrosscutSaw extends WeaponMelee {

	private int consecutives = 1;
	
	public CrosscutSaw() {
		weaponStatus = new ManfighterGenerator().getRandomNeutralStatus();
		damage = 120;
		range = 30;
		fireTime = 1100;
		readyTime = 550;
	}
	
	public String getBaseName() {
		return "Crosscut saw";
	}
	
	public boolean isCrit() {
		return ((rand.getOdds(7,50)) || weaponStatus.getCritChance());
	}

	public String getVerb() {
		return "sawed";
	}
	
	public String getDamageType() {
		return "slicing";
	}
	
	@Override
	public void reset() {
		super.reset();
		consecutives = 1;
	}
	
	public int getDamage(int distance) {
		int log = 1;
		if(damageLocation.equalsIgnoreCase("log")) {
			System.out.println("I'm a crosscut saw, baby drag me across your log!");
			log = 5;
		}
		if(ready) {
			return (damage * consecutives * log);
		} else {
			int d = (damage * consecutives * log);
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
