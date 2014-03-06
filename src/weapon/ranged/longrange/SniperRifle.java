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
		int d = 0;
		
		if(readyState == 0) {
			if(rand.getOdds(1, 4)) {
				d = damage;
			}
		} else if(readyState <= 4) {
			if(rand.getOdds(2, 3)) {
				d = (readyState * 60) + damage;
			}
		} else {
			d = readyState*100 + damage;
		}

		return d;
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

	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('r'); //can always ready
		
		if(this.hasLoadedAmmo())
			a.add('a'); //attack
		if(!this.hasFullAmmo())
			a.add('o'); //reload
			
		if(readyState > 0) {
			a.add('i');
			a.add('l');
		}
		
		return a;
	}
	
}
