package weapon.melee.knife;

import java.util.HashSet;

import weapon.WeaponMelee;

public abstract class BaseKnife extends WeaponMelee {

	//a note: no knives may be readied, their permanent unready status keeps the player moving at maximum speed
	
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('a'); //attack
		a.add('i');
		
		return a;
	}
	
	public boolean isCrit() {
		return((rand.getOdds(9,50)) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "stabbed";
	}
	
	public String getDamageType() {
		return "slicing";
	}
	
	@Override
	public int getDamage(int distance) {
		return damage;
	}
}
