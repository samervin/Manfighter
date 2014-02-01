package weapon.ranged.explosive;

import game.RandGen;
import game.Weapon;

public abstract class BaseExplosive extends Weapon {

	public boolean isCrit() {
		return ((RandGen.getRand(1,50) < 2) || status.getCritChance());
	}
	
	public String getVerb() {
		return "shot";
	}
}
