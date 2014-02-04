package weapon.melee.knife;

import java.util.HashSet;

import game.RandGen;
import game.Weapon;

public abstract class BaseKnife extends Weapon {

	public boolean isReadied() {
		return false; //as fists
	}
	
	public void setReadied(boolean r) {
		//as fists
	}
	
	public boolean hasFullAmmo() {
		return true; //trivially
	}
	
	public boolean hasLoadedAmmo() {
		return true; //trivially
	}
	
	public void reload() {
		//nothing
	}
	
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('a'); //attack
		
		return a;
	}
	
	public boolean isCrit() {
		return((RandGen.getRand(1,50) < 10) || weaponStatus.getCritChance());
	}
	
	public String getVerb() {
		return "stabbed";
	}
}
