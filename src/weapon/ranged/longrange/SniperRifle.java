package weapon.ranged.longrange;

import game.RandGen;

import java.util.HashSet;

public class SniperRifle extends BaseLongrange {

	private int readyState = 0; //0 = from the hip, 1-6 = tracking, 7+ = headshot

	public SniperRifle() {
		weaponStatus = getRandomStatus();
		damage = 80;
		range = 1000;
		maxClip = 2;
		clip = 2;
		fireTime = 1250;
	}
	
	public String getBaseName() {
		return "Sniper rifle";
	}

	public int getDamage() {
		clip--;
		RandGen rand = new RandGen();
		
		if(readyState == 0) {
			if(rand.getRand(1, 4) == 1)
				return weaponStatus.getDamage(damage);
			else
				return 0;
		} else if(readyState <= 3) {
			if(rand.getRand(1, 3) > 1) {
				return (readyState * 6) + weaponStatus.getDamage(damage);
			} else
				return 0;
		} else if(readyState <= 6) {
			if(rand.getRand(1, 4) > 1) {
				System.out.println("A shot on a vital organ!");
				return (readyState * 15) + weaponStatus.getDamage(damage);
			} else
				return 0;
		}

		System.out.println("A headshot!");
		return readyState * weaponStatus.getDamage(damage);
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
			return weaponStatus.getAttackSpeed(fireTime);
		else
			return weaponStatus.getAttackSpeed(fireTime * 2);
	}

	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('e'); //can always ready
		
		if(this.hasLoadedAmmo())
			a.add('a'); //attack
		if(!this.hasFullAmmo())
			a.add('o'); //reload
			
		if(readyState > 0)
			a.add('l');
		
		return a;
	}
	
}
