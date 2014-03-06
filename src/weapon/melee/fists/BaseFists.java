package weapon.melee.fists;

import java.util.HashSet;

import weapon.WeaponMelee;

public abstract class BaseFists extends WeaponMelee {
	
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('a'); //attack
		a.add('i');
		
		return a;
	}
	
	public boolean isCrit() {
		return((rand.getOdds(2,5)) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "punched";
	}
	
	public String getDamageType() {
		return "crushing";
	}
}
