package weapon.melee.club;

import game.PersonStatus;

import java.util.HashSet;

import status.person.Bruised;

public class Mace extends BaseClub {
	
	public Mace() {
		weaponStatus = getRandomStatus();
		inflictingStatus = new Bruised();
		damage = 200;
		range = 100;
		fireTime = 1500;
		knockback = 50;
		readyTime = 550;
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

	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		
		if(ready) {
			a.add('a');
			a.add('l');
		}	
		else
			a.add('r');
		
		return a;
	}

	public PersonStatus getInflictedStatus() {
		if(rand.getOdds(7, 10))
			return inflictingStatus;
		else
			return blankInflictingStatus;
	}

}
