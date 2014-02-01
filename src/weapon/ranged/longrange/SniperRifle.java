package weapon.ranged.longrange;

import game.RandGen;

import java.util.HashSet;

public class SniperRifle extends BaseLongrange {

	private int damage = 75;
	private int range = 1000; //a lot
	private int ready = 0; //0 = from the hip, 1-6 = tracking, 7+ = headshot
	private int maxClip = 2;
	private int clip = 2;

	public SniperRifle() {
		status = getRandomStatus();
	}
	
	public String getBaseName() {
		return "Sniper rifle";
	}

	public int getDamage() {
		clip--;
		
		if(ready == 0) {
			if(RandGen.getRand(1, 4) == 1)
				return status.getDamage(damage);
			else
				return 0;
		} else if(ready <= 3) {
			if(RandGen.getRand(1, 3) > 1) {
				return (ready * 5) + status.getDamage(damage);
			} else
				return 0;
		} else if(ready <= 6) {
			if(RandGen.getRand(1, 4) > 1) {
				System.out.println("A shot on a vital organ!");
				return (ready * 12) + status.getDamage(damage);
			} else
				return 0;
		}

		System.out.println("A headshot!");
		return ready * status.getDamage(damage);
	}

	public int getRange() {
		return status.getRange(range);
	}

	public boolean isReadied() {
		if(ready != 0)
			return true;
		return false;
	}

	public void setReadied(boolean readiness) {
		if(readiness)
			ready++;
		else
			ready = 0;
	}
	
	public boolean hasFullAmmo() {
		return (clip == maxClip);
	}
	
	public boolean hasLoadedAmmo() {
		if(clip == 0)
			return false;
		else
			return true;
	}

	public void reload() {
		clip = maxClip;
		ready = 0;
	}
	
	public int getFireTime() {
		if(ready > 7)
			return 1200;
		else
			return 2500;
	}

	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('e'); //can always ready
		
		if(this.hasLoadedAmmo())
			a.add('a'); //attack
		if(!this.hasFullAmmo())
			a.add('o'); //reload
			
		if(ready > 0)
			a.add('l');
		
		return a;
	}
	
}
