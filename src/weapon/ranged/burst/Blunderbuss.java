package weapon.ranged.burst;

import game.ManfighterGenerator;

import java.util.HashSet;

public class Blunderbuss extends BaseBurst {

	private final int pelletsPerShot = 10;
	private int previousPelletsHit = 0;

	public Blunderbuss() {
		weaponStatus = new ManfighterGenerator().getRandomRangedStatus();
		damage = 100; //per pellet
		range = 1500;
		maxClip = 1;
		clip = 1;
		fireTime = 900;
		knockback = 20; //per pellet
		readyTime = 700;
		reloadTime = 10000;
	}

	@Override
	public String getBaseName() {
		return "Blunderbuss";
	}
	
	@Override
	public String getFullInfo() {
		String s = String.format("%s\n\tDamage/pellet: %d\n\tTotal pellets: %d\n\tRange: %d\n\tReady: %b",
				toString(), damage, pelletsPerShot, range, ready);
		return s + String.format("\n\tAmmo: %d/%d", clip, maxClip);
	}

	@Override
	public int getDamage(int distance) {
		clip--;
		int odds; //percentage changes of each pellet hitting, lower odds if not readied

		if(distance == range) {
			odds = 1;
		} else if(distance >= 5 * range / 6) {
			odds = 7;
		} else if(distance >= 4 * range / 6) {
			odds = 19;
		} else if(distance >= 3 * range / 6) {
			odds = 41;
		} else if(distance >= 2 * range / 6) {
			odds = 63;
		} else if(distance >= 1 * range / 6) {
			odds = 85;
		}  else {
			odds = 95;
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

		return totalDamage;
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

		if(ready) {
			a.add('l');
			a.add('i');
		}
		else
			a.add('r');

		return a;
	}

}
