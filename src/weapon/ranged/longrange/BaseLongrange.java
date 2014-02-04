package weapon.ranged.longrange;

import game.RandGen;
import game.Weapon;

public abstract class BaseLongrange extends Weapon {

	public boolean isCrit() {
		return ((RandGen.getRand(1,50) < 2) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "sniped";
	}
	
}
