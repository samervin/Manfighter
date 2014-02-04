package weapon.ranged.unpowered;

import game.PersonStatus;

import java.util.HashSet;

public class Shortbow extends BaseUnpowered {

	private int damage = 175;
	private int range = 400;
	private boolean ready = false;
	
	public Shortbow() {
		weaponStatus = getRandomStatus();
	}

	public String getBaseName() {
		return "Short bow";
	}

	public int getDamage() {
		ready = false;
		return weaponStatus.getDamage(damage);
	}

	public int getRange() {
		return weaponStatus.getRange(range);
	}

	public int getFireTime() {
		return 975;
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
