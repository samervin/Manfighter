package weapon.ranged.unpowered;

import game.ManfighterGenerator;

import java.util.HashSet;

public class Shortbow extends BaseUnpowered {
	
	public Shortbow() {
		weaponStatus = new ManfighterGenerator().getRandomStatus();
		damage = 170;
		range = 400;
		maxClip = 1;
		clip = 1;
		fireTime = 700;
		knockback = 15;
		readyTime = 800;
		reloadTime = 1500;
	}

	public String getBaseName() {
		return "Short bow";
	}

	public int getDamage(int distance) {
		clip--;
		ready = false;
		int d = 0;
		
		if(rand.getOdds(9, 10)) {
			d = weaponStatus.getDamage(damage);
		}
			
		
		return d;
	}
	
	@Override
	public String getDamageType() {
		return "piercing";
	}

	public HashSet<Character> getWeaponActions() {		
		HashSet<Character> a = new HashSet<Character>();
		if(this.hasLoadedAmmo() && ready)
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
