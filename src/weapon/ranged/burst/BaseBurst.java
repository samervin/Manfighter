package weapon.ranged.burst;

import weapon.WeaponRanged;

public abstract class BaseBurst extends WeaponRanged {

	protected int pelletsPerShot;
	
	@Override
	public boolean isCrit() {
		return ((rand.getOdds(1,50)) || weaponStatus.getCritChance());
	}

	@Override
	public String getVerb() {
		return "blasted";
	}

	@Override
	public String getDamageType() {
		return "shrapnel";
	}
	
	@Override
	public String getFullInfo() {
		String s = super.getFullInfo();
		s = s.replaceFirst("Damage", "Damage/pellet");
		s = s.replaceFirst("Range", "Pellets/shot: " + pelletsPerShot + "\n\tRange");
		return s;
	}
	
}
