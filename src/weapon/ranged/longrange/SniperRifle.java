package weapon.ranged.longrange;

import game.ManfighterGenerator;

import java.util.HashSet;

public class SniperRifle extends BaseLongrange {

	private int readyState = 0; //0 = from the hip, 1-4 = tracking, 5+ = headshot

	public SniperRifle() {
		weaponStatus = new ManfighterGenerator().getRandomStatus();
		damage = 80;
		range = 1000;
		maxClip = 2;
		clip = 2;
		fireTime = 1250;
		knockback = 60;
		readyTime = 550;
		reloadTime = 2000;
	}
	
	public String getBaseName() {
		return "Sniper rifle";
	}

	public int getDamage(int distance) {
		clip--;
		
		if(readyState == 0) {
			if(rand.getOdds(1, 4))
				return weaponStatus.getDamage(damage);
			else
				return 0;
		} else if(readyState <= 4) {
			if(rand.getOdds(2, 3)) {
				return (readyState * 60) + weaponStatus.getDamage(damage);
			} else
				return 0;
		}

		System.out.println("A headshot!");
		return (readyState*100) + weaponStatus.getDamage(damage);
	}

	public boolean isReadied() {
		if(readyState != 0)
			return true;
		return false;
	}

	public void setReadied(boolean readiness) {
		if(readiness)
			readyState++;
		else
			readyState = 0;
	}

	public void reload() {
		clip = maxClip;
		readyState = 0;
	}
	
	public int getFireTime() {
		if(readyState > 7)
			return weaponStatus.getFireTime(fireTime);
		else
			return weaponStatus.getFireTime(fireTime * 2);
	}

	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('r'); //can always ready
		
		if(this.hasLoadedAmmo())
			a.add('a'); //attack
		if(!this.hasFullAmmo())
			a.add('o'); //reload
			
		if(readyState > 0)
			a.add('l');
		
		return a;
	}
	
}
