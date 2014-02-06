package weapon.melee.hammer;

import game.PersonStatus;
import game.RandGen;

import java.util.HashSet;

import status.person.Stunned;

public class WarHammer extends BaseHammer {

	private final int damage = 500;
	private final int range = 160;
	private boolean ready = false;

	public WarHammer() {
		weaponStatus = getRandomStatus();
		inflictingStatus = new Stunned();
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

	public int getRange() {
		return weaponStatus.getRange(range);
	}

	public int getFireTime() {
		return 2500;
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

	public PersonStatus getInflictedStatus() {
		int x = RandGen.getRand(1, 10);
		if(x < 3 && ready)
			return inflictingStatus;
		else
			return blankInflictingStatus;
	}

	public boolean isReadied() {
		return ready;
	}

	public void setReadied(boolean readiness) {
		ready = readiness;
	}

}
