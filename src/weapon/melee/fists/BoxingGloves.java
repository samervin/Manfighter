package weapon.melee.fists;

import status.person.PersonStatus;
import status.person.Stunned;

public class BoxingGloves extends BaseFists {

	public BoxingGloves() {
		damage = 75;
		range = 1;
		fireTime = 715;
		inflictingStatus = new Stunned(600);
	}
	
	@Override
	public String getBaseName() {
		return "Boxing gloves";
	}
	
	@Override
	public PersonStatus getInflictedStatus() {
		if(rand.getOdds(7, 10))
			return inflictingStatus;
		return blankInflictingStatus;
	}

}
