package weapon.melee.club;

import game.ManfighterGenerator;

import java.util.HashSet;

import status.person.Bruised;
import status.person.PersonStatus;

public class Mace extends BaseClub {
	
	public Mace() {
		weaponStatus = new ManfighterGenerator().getRandomStatus();
		inflictingStatus = new Bruised();
		damage = 200;
		range = 40;
		fireTime = 1500;
		knockback = 50;
		readyTime = 550;
	}

	@Override
	public String getBaseName() {
		return "Mace";
	}

	@Override
	public int getDamage(int distance) {
		int d;
		if(ready) {
			ready = false;
			d = weaponStatus.getDamage(damage);
			return d;
		}
		
		return 0;
	}

	@Override
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		
		if(ready) {
			a.add('a');
			a.add('l');
			a.add('i');
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
