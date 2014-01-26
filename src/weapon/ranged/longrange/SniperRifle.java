package weapon.ranged.longrange;

import java.util.HashSet;

import status.weapon.*;
import game.*;

public class SniperRifle extends BaseLongrange {

	private int damage = 75;
	private int range = 1000; //a lot
	private Status status = new BaseStatus();
	private int ready = 0; //0 = from the hip, 1-6 = tracking, 7+ = headshot
	private int maxClip = 2;
	private int clip = 2;

	public String toString() {
		if(status instanceof BaseStatus)
			return this.getBaseName();
		return this.getBaseName() + ", with " + status.toString();
	}
	
	public String getBaseName() {
		return "Sniper rifle";
	}

	public int getDamage() {
		clip--;
		
		if(ready == 0) {
			if(RandGen.getRand(1, 8) == 1)
				return status.getDamage(damage);
			else
				return 0;
		} else if(ready <= 3) {
			if(RandGen.getRand(1, 5) == 1) {
				return (ready * 5) + status.getDamage(damage);
			} else
				return 0;
		} else if(ready <= 6) {
			if(RandGen.getRand(1, 3) == 1) {
				System.out.println("A shot on a vital organ!");
				return (ready * 12) + status.getDamage(damage);
			} else
				return 0;
		}

		System.out.println("A headshot!");
		return ready * status.getDamage(damage);
	}

	public int getRange() {
		return range;
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
	}
	public int getFireTime() {
		if(ready > 7)
			return 1500;
		else
			return 3000;
	}

	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('e'); //can always ready
		
		if(this.hasLoadedAmmo())
			a.add('a'); //attack
		if(!this.hasLoadedAmmo())
			a.add('o'); //reload
			
		if(ready > 0)
			a.add('l');
		
		return a;
	}
	
}
