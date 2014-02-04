package weapon.melee.club;

import game.PersonStatus;

import java.util.HashSet;

import status.person.Stunned;

public class Mace extends BaseClub {

	private int damage = 200;
	private int range = 100;
	private boolean ready = false;
	
	public Mace() {
		weaponStatus = getRandomStatus();
		inflictingStatus = new Stunned();
	}

	public String getBaseName() {
		return "Mace";
	}

	public int getDamage() {
		if(ready) {
			ready = false;
			int d = weaponStatus.getDamage(damage);
			return d;
		}
		else {
			ready = true;
			int d = weaponStatus.getDamage(damage);
			return d;
		}
	}

	public int getRange() {
		return weaponStatus.getRange(range);
	}

	public int getFireTime() {
		if(ready) {
			return 1500;
		} else {
			return 2100;
		}
	}

	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('a'); //attack
		
		if(ready)
			a.add('l');
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
		return inflictingStatus;
	}

}
