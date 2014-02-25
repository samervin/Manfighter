package weapon.ranged.burst;

import game.ManfighterGenerator;

import java.util.HashSet;

public class Blunderbuss extends BaseBurst {

	private final int pelletsPerShot = 10;
	private int previousPelletsHit = 0;

	public Blunderbuss() {
		weaponStatus = new ManfighterGenerator().getRandomStatus();
		damage = 100; //per pellet
		range = 1500;
		maxClip = 1;
		clip = 1;
		fireTime = 900;
		knockback = 20; //per pellet
		readyTime = 700;
		reloadTime = 10000; //this is ridiculous
	}

	@Override
	public String getBaseName() {
		return "Blunderbuss";
	}

	@Override
	public int getDamage(int distance) {
		clip--;

		int odds; //percentage changes of each pellet hitting, lower odds if not readied

		if(distance == range) {
			odds = 1;
		} else if(distance >= 5 * range / 6) {
			odds = 8;
		} else if(distance >= 4 * range / 6) {
			odds = 20;
		} else if(distance >= 3 * range / 6) {
			odds = 42;
		} else if(distance >= 2 * range / 6) {
			odds = 64;
		} else if(distance >= 1 * range / 6) {
			odds = 85;
		}  else {
			odds = 96;
		}

		int totalDamage = 0;
		for(int j = 0; j < pelletsPerShot; j++) {
			if(ready && rand.getOdds(odds, 100)) {
				totalDamage += damage;
				previousPelletsHit++;
			}	
			else if(!ready && rand.getOdds(odds, 150)) {
				totalDamage += damage;
				previousPelletsHit++;
			}
		}

		return weaponStatus.getDamage(totalDamage);
	}

	@Override
	public int getKnockback() {
		int temp = previousPelletsHit;
		previousPelletsHit = 0;
		return temp * knockback;
	}

	@Override
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
