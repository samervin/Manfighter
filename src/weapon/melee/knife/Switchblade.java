package weapon.melee.knife;

import game.ManfighterGenerator;
import status.person.Bleeding;

public class Switchblade extends BaseKnife {
	
	public Switchblade() {
		weaponStatus = new ManfighterGenerator().getRandomNeutralStatus();
		inflictingStatus = new Bleeding();
		damage = 130;
		range = 10;
		fireTime = 760;
		readyTime = 550;
	}
	
	public String getBaseName() {
		return "Switchblade";
	}
}
