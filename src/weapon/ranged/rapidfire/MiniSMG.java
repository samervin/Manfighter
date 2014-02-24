package weapon.ranged.rapidfire;

import java.util.HashSet;

public class MiniSMG extends BaseRapidFire {

	public final int bulletsPerTriggerPull = 5;
	public int bulletsLeft = 5; //per trigger pull
	public boolean rambo = true; //if you miss in a trigger pull, you automatically miss the rest
	
	public MiniSMG() {
		weaponStatus = getRandomStatus();
		damage = 15;
		range = 1000;
		maxClip = 25;
		clip = 25;
		fireTime = 200;
		readyTime = 750;
		knockback = 2;
	}
	
	@Override
	public String getBaseName() {
		return "Mini SMG";
	}
	
	@Override
	public int getFireTime() {
		if(bulletsLeft > 0) {
			return fireTime / 4;
		}
		return fireTime;
	}
	
	@Override
	public int getDamage() {		
		if(bulletsLeft > 0) {
			bulletsLeft--;
			clip--;
			if(ready && rambo) {
				if(rand.getOdds(9,10)) {
					return weaponStatus.getDamage(damage);
				} else
					rambo = false;
			} else if(!ready && rambo) {
				if(rand.getOdds(8,10)) {
					return weaponStatus.getDamage(damage);
				} else
					rambo = false;
			}
		} else {
			bulletsLeft = bulletsPerTriggerPull;
			rambo = true;
			return getDamage();
		}
		
		return 0;
	}

	@Override
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		if(this.hasLoadedAmmo())
			a.add('a');
		if(bulletsLeft % 5 == 0) {
			if(!this.hasFullAmmo())
				a.add('o');
			if(ready)
				a.add('l');
			else
				a.add('r');
		}
		
		return a;
	}
	
	@Override
	public HashSet<Character> getRestrictedActions() {
		HashSet<Character> a = new HashSet<Character>();
		if(bulletsLeft % 5 != 0) {
			a.add('m');
			a.add('w');
			a.add('d');
			a.add('e');
		}
		
		return a;
	}

}
