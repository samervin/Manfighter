package weapon.melee.fists;

import game.RandGen;

public class HandWraps extends BaseFists {

	public HandWraps() {
		damage = 75;
		range = 0;
		fireTime = 650;
	}
	
	@Override
	public String getBaseName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean isCrit() {
		if(new RandGen().getOdds(1, 2)) {
			return super.isCrit();
		}
		return false;
	}
}
