package weapon.melee.club;

import game.PersonStatus;
import game.RandGen;

import java.util.HashSet;

import status.person.Bruised;

public class Mace extends BaseClub {

	private int damage = 200;
	private int range = 100;
	private boolean ready = false;
	
	public Mace() {
		weaponStatus = getRandomStatus();
		inflictingStatus = new Bruised();
	}

	public String getBaseName() {
		return "Mace";
	}

	public int getDamage() {
		if(ready) {
			ready = false;
			return weaponStatus.getDamage(damage);
		}
		
		return 0;
	}

	public int getRange() {
		return weaponStatus.getRange(range);
	}

	public int getFireTime() {
		return 1500;
	}

	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		
		
		if(ready) {
			a.add('a'); //attack
			a.add('l');
		}	
		else
			a.add('e');
		
		return a;
	}

	public boolean isReadied() {
		return ready;
	}

	public void setReadied(boolean readiness) {
		ready = readiness;
	}

	public PersonStatus getInflictedStatus() {
		int x = RandGen.getRand(1, 10);
		if(x < 8 && ready)
			return inflictingStatus;
		else
			return blankInflictingStatus;
	}

}