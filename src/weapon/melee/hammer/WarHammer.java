package weapon.melee.hammer;

import game.ManfighterGenerator;

import java.util.HashSet;

import status.person.PersonStatus;
import status.person.Stunned;

public class WarHammer extends BaseHammer {

	public WarHammer() {
		weaponStatus = new ManfighterGenerator().getRandomNeutralStatus();
		inflictingStatus = new Stunned();
		damage = 500;
		range = 100;
		fireTime = 2500;
		readyTime = 550;
	}

	public String getBaseName() {
		return "War hammer";
	}

	@Override
	public int getDamage(int distance) {
			ready = false;
			return damage;
	}

	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();

		if(ready) {
			a.add('a'); //attack
			a.add('l');
			a.add('i');
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
