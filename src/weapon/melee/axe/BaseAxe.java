package weapon.melee.axe;

import java.util.HashSet;

import weapon.WeaponMelee;

public abstract class BaseAxe extends WeaponMelee {
	
	protected boolean stuck = false;
	protected int defaultFireTime;
	protected int stuckFireTime;
	protected int stuckOdds; //percentage chance of getting stuck
	protected int unStuckOdds; //percentage chance of getting unstuck once stuck
	
	@Override
	public boolean isCrit() {
		return ((rand.getOdds(7,50)) || weaponStatus.getCritChance());
	}
	
	@Override
	public String getVerb() {
		return "axed";
	}
	
	@Override
	public String getDamageType() {
		if(stuck)
			return "penetrating";
		else
			return "slicing";
	}
	
	@Override
	public void reset() {
		super.reset();
		stuck = false;
	}
	
	@Override
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		if(!stuck) {
			if(ready) {
				a.add('l');
				a.add('i'); //aim
			}
			else
				a.add('r');
		}
		a.add('a');
		return a;
	}
	
	@Override
	public HashSet<Character> getRestrictedActions() {
		HashSet<Character> a = new HashSet<Character>();
		if(stuck) {
			a.add('e');
			a.add('m');
		}
		return a;
	}
	
	@Override
	public void lastEnemyActionTaken(char action) {
		if(stuck) {
			switch(action) {
			case 'e': System.out.println(getBaseName() + " got unstuck!"); stuck = false; break;
			case 'm': System.out.println(getBaseName() + " got unstuck!"); stuck = false; break; //enemies currently don't move
			}
		}
	}
	
	@Override
	public void lastDamageDealt(int dam) {
		if(dam > 0) {
			if(!stuck && stuckOdds()) { //not already stuck, becoming stuck
				stuck = true;
				fireTime = stuckFireTime;
				System.out.println(getBaseName() + " got stuck!");
			} else if(stuck && unStuckOdds()) { //already stuck, becoming unstuck
				stuck = false;
				fireTime = defaultFireTime;
				System.out.println(getBaseName() + " got unstuck!");
			}
		}
	}
	
	@Override
	public void lastEnemyKilled(boolean en) {
		if(en) {
			stuck = false;
			fireTime = defaultFireTime;
		}
	}
	
	protected boolean stuckOdds() {
		if(rand.getOdds(1, 3)) {
			return true;
		}
		return false;
	}

	protected boolean unStuckOdds() {
		if(rand.getOdds(1, 5)) {
			return true;
		}
		return false;
	}
	
}
