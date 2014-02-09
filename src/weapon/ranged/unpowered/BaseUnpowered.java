package weapon.ranged.unpowered;

import game.RandGen;
import weapon.WeaponRanged;

public abstract class BaseUnpowered extends WeaponRanged {
	
	public boolean isCrit() {
		RandGen rand = new RandGen();
		
		return ((rand.getRand(1,50) < 2) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "arrowed";
	}
}
