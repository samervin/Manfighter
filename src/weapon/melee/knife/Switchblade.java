package weapon.melee.knife;

import game.PersonStatus;
import status.person.Bleeding;

public class Switchblade extends BaseKnife {

	private int damage = 130;
	private int range = 70;
	
	public Switchblade() {
		weaponStatus = getRandomStatus();
		inflictingStatus = new Bleeding();
	}
	
	public String getBaseName() {
		return "Switchblade";
	}

	public int getDamage() {
		return weaponStatus.getDamage(damage);
	}

	public int getRange() {
		return weaponStatus.getRange(range);
	}

	public int getFireTime() {
		return 760;
	}
	
	public PersonStatus getInflictedStatus() {
		return inflictingStatus;
	}
}
