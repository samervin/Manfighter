package weapon.melee.knife;

import java.util.HashSet;

import weapon.WeaponMelee;

public abstract class BaseKnife extends WeaponMelee {

	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('a'); //attack
		
		return a;
	}
	
	public boolean isCrit() {
		return((rand.getRand(1,50) < 10) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "stabbed";
	}
}
