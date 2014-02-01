package weapon.melee.fists;

import game.RandGen;
import game.Weapon;

import java.util.HashSet;

public abstract class BaseFists extends Weapon {
	
	public boolean isReadied() {
		return false; //fists are trivially always ready, false prevents movement penalties
	}
	
	public void setReadied(boolean r) {
		//do nothing
	}
	
	public boolean hasFullAmmo() {
		return true; // trivially
	}
	
	public boolean hasLoadedAmmo() {
		return true; //fists don't reload!
	}
	
	public void reload() {
		//do nothing
	}
	
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		a.add('a'); //attack
		
		return a;
	}
	
	public boolean isCrit() {
		return((RandGen.getRand(1,50) < 10) || status.getCritChance());
	}
	
	public String getVerb() {
		return "punched";
	}
}
