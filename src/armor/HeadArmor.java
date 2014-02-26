package armor;

import game.Armor;

public abstract class HeadArmor extends Armor {

	protected int headshotProtection;
	
	public boolean headshotProtected() {
		return rand.getOdds(headshotProtection, 100);
	}
	
}
