package weapon.melee.hammer;

import game.PersonStatus;

import java.util.HashSet;

import status.person.Stunned;

public class WarHammer extends BaseHammer {

	public WarHammer() {
		weaponStatus = getRandomStatus();
		inflictingStatus = new Stunned();
		damage = 500;
		range = 100;
		fireTime = 2500;
		readyTime = 550;
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
		if(rand.getOdds(3, 10))
			return inflictingStatus;
		else
			return blankInflictingStatus;
	}
}
