package weapon.ranged.explosive;

import game.RandGen;
import weapon.WeaponRanged;

public abstract class BaseExplosive extends WeaponRanged {

	public boolean isCrit() {
		RandGen rand = new RandGen();
		
		return ((rand.getRand(1,50) < 2) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "shot";
	}
}
