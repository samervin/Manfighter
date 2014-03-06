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
		reloadOneTime = 900; //speculation

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
		
		if(ready) {
			if(rand.getOdds(2, 3)) {
				return damage;
			}
		} else {
			if(rand.getOdds(2, 7)) {
				return damage;
			}
		}
		return 0;
	}

	@Override
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		if(this.hasLoadedAmmo())
			a.add('a'); //attack
		if(!this.hasFullAmmo()) {
			a.add('o'); //reload
			a.add('p'); //reload one
		}
			
			
		if(ready) {
			a.add('i');
			a.add('l');
		}
		else
			a.add('r');
		
		return a;
	}

}
