package weapon.ranged.longrange;

import game.RandGen;
import weapon.WeaponRanged;

public abstract class BaseLongrange extends WeaponRanged {

	public boolean isCrit() {
		RandGen rand = new RandGen();
		
		return ((rand.getRand(1,50) < 2) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "sniped";
	}
	
}
