package weapon.ranged.rapidfire;

import weapon.WeaponRanged;

public abstract class BaseRapidFire extends WeaponRanged {

	@Override
	public boolean isCrit() {
		return ((rand.getOdds(1,45)) || weaponStatus.getCritChance());
	}

	@Override
	public String getVerb() {
		return "shot";
	}
	
	@Override
	public String getDamageType() {
		return "piercing";
	}

}
