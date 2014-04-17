package weapon.ranged.rapidfire;

import game.ManfighterGenerator;

import java.util.HashSet;

public class MiniSMG extends BaseRapidFire {

	public final int bulletsPerTriggerPull = 5; //you fire 5 rounds at a time
	public int bulletsLeft = 0; //per trigger pull
	public boolean rambo = true; //if you miss in a trigger pull, you automatically miss the rest
	
	public MiniSMG() {
		weaponStatus = new ManfighterGenerator().getRandomRangedStatus();
		damage = 20;
		range = 1000;
		maxClip = 25;
		clip = 25;
		fireTime = 200;
		readyTime = 750;
		reloadTime = 1900;
	}
	
	@Override
	public String getBaseName() {
		return "Mini SMG";
	}
	
	@Override
	public void reset() {
		super.reset();
		bulletsLeft = 0;
		rambo = true;
	}
	
	@Override
	public int getFireTime() {
		if(bulletsLeft > 0) {
			return fireTime / 4;
		}
		return fireTime;
	}
	
	@Override
	public void reload() {
		clip = maxClip;
		ready = false;
		bulletsLeft = 0;
		rambo = true;
	}
	
	@Override
	public int getDamage(int distance) {
		
		if(bulletsLeft > 0) {
			bulletsLeft--;
			clip--;
			if(ready && rambo) {
				if(rand.getOdds(9,10)) {
					return damage;
				} else
					rambo = false;
			} else if(!ready && rambo) {
				if(rand.getOdds(8,10)) {
					return damage;
				} else
					rambo = false;
			}
		} else {
			bulletsLeft = bulletsPerTriggerPull;
			rambo = true;
			return this.getDamage(distance);
		}

		return 0; //miss
	}
	
	@Override
	public void lastDamageDealt(int damage) {
		if(damage == 0)
			rambo = false;
	}

	@Override
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		if(this.hasLoadedAmmo())
			a.add('a');
		if(bulletsLeft % 5 == 0) {
			if(!this.hasFullAmmo())
				a.add('o');
			if(ready) {
				a.add('i');
				a.add('l');
			}
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
