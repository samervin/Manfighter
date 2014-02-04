package weapon.melee.club;

import game.RandGen;
import game.Weapon;

public abstract class BaseClub extends Weapon {

	public boolean hasFullAmmo() {
		return true; // trivially
	}
	
	public boolean hasLoadedAmmo() {
		return true;
	}

	public void reload() {
		//do nothing
	}
	
	public boolean isCrit() {
		return((RandGen.getRand(1,50) < 8) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "smashed";
	}
	
}
