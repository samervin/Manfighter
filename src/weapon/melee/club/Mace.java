package weapon.melee.club;

import game.PersonStatus;
import game.RandGen;

import java.util.HashSet;

import status.person.Bruised;

public class Mace extends BaseClub {
	
	public Mace() {
		weaponStatus = getRandomStatus();
		inflictingStatus = new Bruised();
		damage = 200;
		range = 100;
		swingTime = 1500;
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
			a.add('e');
		
		return a;
	}

	public PersonStatus getInflictedStatus() {
		RandGen rand = new RandGen();
		
		int x = rand.getRand(1, 10);
		if(x < 8)
			return inflictingStatus;
		else
			return blankInflictingStatus;
	}

}
