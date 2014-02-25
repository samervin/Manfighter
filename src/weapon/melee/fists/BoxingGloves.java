package weapon.melee.fists;

import status.person.Stunned;

public class BoxingGloves extends BaseFists {

	public BoxingGloves() {
		damage = 100;
		range = 1;
		fireTime = 715;
		inflictingStatus = new Stunned(625);
	}
	
	@Override
	public String getBaseName() {
		return "Boxing gloves";
	}

}
