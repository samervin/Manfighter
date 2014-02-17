package weapon.melee.hammer;

import game.PersonStatus;
import game.RandGen;

import java.util.HashSet;

import status.person.Stunned;

public class WarHammer extends BaseHammer {

	public WarHammer() {
		weaponStatus = getRandomStatus();
		inflictingStatus = new Stunned();
		damage = 500;
		range = 160;
		fireTime = 2500;
	}

	public String getBaseName() {
		return "War hammer";
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
			a.add('a'); //attack
			a.add('l');
		}	
		else
			a.add('r');

		return a;
	}

	public PersonStatus getInflictedStatus() {
		RandGen rand = new RandGen();
		
		int x = rand.getRand(1, 10);
		if(x < 4)
			return inflictingStatus;
		else
			return blankInflictingStatus;
	}
}
