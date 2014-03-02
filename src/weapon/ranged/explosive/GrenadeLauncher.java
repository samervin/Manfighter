package weapon.ranged.explosive;

import game.ManfighterGenerator;

import java.util.HashSet;

public class GrenadeLauncher extends BaseExplosive {

	public GrenadeLauncher() {
		weaponStatus = new ManfighterGenerator().getRandomStatus();
		damage = 350;
		range = 1000;
		maxClip = 6;
		clip = 6;
		fireTime = 1000;
		knockback = 75;
		readyTime = 700;
		reloadTime = 4700;

		selfDamage = 300;
		selfDamageRange = 270;
	}

	@Override
	public String getBaseName() {
		return "Grenade launcher";
	}

	public String getVerb() {
		return "bombed";
	}

	public int getDamage(int distance) {
		clip--;
		int d;
		
		if(ready) {
			if(rand.getOdds(2, 3)) {
				d = weaponStatus.getDamage(damage);
				return getLocationDamage(d);
			}
		} else {
			if(rand.getOdds(2, 7)) {
				d = weaponStatus.getDamage(damage);
				return getLocationDamage(d);
			}
		}
		return 0;
	}

	@Override
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		if(this.hasLoadedAmmo())
			a.add('a'); //attack
		if(!this.hasFullAmmo())
			a.add('o'); //reload
			
		if(ready) {
			a.add('i');
			a.add('l');
		}
		else
			a.add('r');
		
		return a;
	}

}
