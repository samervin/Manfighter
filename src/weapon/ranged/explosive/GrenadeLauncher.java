package weapon.ranged.explosive;

import java.util.HashSet;

public class GrenadeLauncher extends BaseExplosive {

	public GrenadeLauncher() {
		weaponStatus = getRandomStatus();
		damage = 350;
		range = 1000;
		maxClip = 6;
		clip = 6;
		fireTime = 2450;
		knockback = 75;
		readyTime = 700;

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

	public int getDamage() {
		clip--;

		if(ready) {
			if(rand.getOdds(2, 3)) {
				return weaponStatus.getDamage(damage);
			}
		} else {
			if(rand.getOdds(2, 7)) {
				return weaponStatus.getDamage(damage);
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
			
		if(ready)
			a.add('l');
		else
			a.add('r');
		
		return a;
	}

}
