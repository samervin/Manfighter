package weapon.ranged.explosive;

import game.RandGen;
import game.Status;

import java.util.HashSet;

import status.weapon.NoStatus;

public class RocketLauncher extends BaseExplosive {

	private int damage = 350;
	private int range = 500;
	private Status status = new NoStatus();
	private boolean ready = false;
	private int maxClip = 4;
	private int clip = 4;

	public RocketLauncher() {
		status = getRandomStatus();
	}

	public String toString() {
		if(status instanceof NoStatus)
			return this.getBaseName();
		return this.getBaseName() + ", with " + status.toString();
	}

	public String getBaseName() {
		return "Rocket launcher";
	}

	public int getDamage() {
		clip--;
		if(ready) {
			if(RandGen.getRand(1, 5) > 1) {
				return status.getDamage(damage);
			}
		}
		else {
			if(RandGen.getRand(1, 5) > 3) {
				return status.getDamage(damage);
			}
		}
		
		return 0;
	}

	public int getRange() {
		return range;
	}

	public boolean isReadied() {
		return ready;
	}

	public void setReadied(boolean r) {
		ready = r;
	}

	public boolean hasLoadedAmmo() {
		if(clip == 0)
			return false;
		else
			return true;
	}
	
	public boolean hasFullAmmo() {
		return (clip == maxClip);
	}

	public void reload() {
		clip = maxClip;
		ready = false;
	}
	
	public int getFireTime() {
		if(ready)
			return 1900;
		else
			return 2400;
	}
	
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		if(this.hasLoadedAmmo())
			a.add('a'); //attack
		if(!this.hasFullAmmo())
			a.add('o'); //reload
			
		if(ready)
			a.add('l');
		else
			a.add('e');
		
		return a;
	}
}
