package weapon.ranged.explosive;

import weapon.WeaponRanged;

public abstract class BaseExplosive extends WeaponRanged {

	@Override
	public boolean isCrit() {
		return ((rand.getOdds(1,50)) || weaponStatus.getCritChance());
	}
	
	@Override
	public String getVerb() {
		return "shot";
	}
	
	@Override
	public String getDamageType() {
		return "explosive";
	}
}
