package weapon.melee.knife;

import status.person.Bleeding;

public class Switchblade extends BaseKnife {
	
	public Switchblade() {
		weaponStatus = getRandomStatus();
		inflictingStatus = new Bleeding();
		damage = 130;
		range = 70;
		fireTime = 760;
	}
	
	public String getBaseName() {
		return "Switchblade";
	}
}
