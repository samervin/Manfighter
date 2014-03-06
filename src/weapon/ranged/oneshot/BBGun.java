package weapon.ranged.oneshot;

import game.ManfighterGenerator;

import java.util.HashSet;

public class BBGun extends BaseOneshot {

	public BBGun(){
		weaponStatus = new ManfighterGenerator().getRandomStatus();
		damage = 50;
		range = 1000;
		maxClip = 50;
		clip = 50;
		fireTime = 600;
		readyTime = 400;
		reloadTime = 2500;
		reloadOneTime = 2200;
	}

	@Override
	public String getBaseName() {
		return " BB gun"; //intentional, prevents lowercase bB gun
	}

	@Override
	public int getDamage(int distance) {
		clip--;
		
		if(ready) {
			if(rand.getOdds(99, 100)) {
				return damage;
			}
		} else {
			if(rand.getOdds(95, 100)) {
				return damage;
			}
		}
		return 0;
	}
	
	@Override
	public String getDamageType() {
		return "crushing";
	}

	@Override
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		if(this.hasLoadedAmmo())
			a.add('a');
		if(!this.hasFullAmmo()) {
			a.add('o');
			a.add('p');
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
