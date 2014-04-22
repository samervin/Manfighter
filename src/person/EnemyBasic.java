package person;

import game.ManfighterGenerator;
import game.RandGen;

import java.util.ArrayList;
import java.util.HashSet;

public class EnemyBasic extends Enemy {
	
	public EnemyBasic() {
		ManfighterGenerator mfg = new ManfighterGenerator();
		name = mfg.createRandomName();
		health = 1000;
		weapon = mfg.getRandomWeapon();
	}

	@Override
	public String getAction(int distance) {
		ArrayList<String> a = new ArrayList<String>();
		HashSet<Character> valids = weapon.getWeaponActions();
		
		if(valids.contains('r')) {
			for(int i = 0; i < 6; i++) {
				a.add("r");
			}
		}
		
		if(distance <= 2 * weapon.getRange() / 3) {
			for(int i = 0; i < 2; i++)
				a.add("e");
		}
		
		if(distance <= weapon.getRange() && valids.contains('a')) {
			for(int i = 0; i < 10; i++)
				a.add("a");
		} else if (distance >= 2 * weapon.getRange()){
			for(int i = 0; i < 6; i++) {
				a.add("d");
			}
			
			if(valids.contains('l')) {
				a.add("l");
			}
		} else if(distance >= weapon.getRange()){
			for(int i = 0; i < 4; i++) {
				a.add("d");
			}
		}
		
		if(!weapon.hasFullAmmo() && valids.contains('o') && !a.contains('a')) {
			for(int i = 0; i < 1; i++)
				a.add("o");
		}
		
		if(!weapon.hasLoadedAmmo() && valids.contains('o')) {			
			for(int i = 0; i < 10; i++)
				a.add("o");
		}
		
		if(valids.contains('i') && weapon.getDamageLocation().equals("torso")) {
			for(int i = 0; i < 4; i++)
				a.add("i head");
		}
				
		HashSet<Character> restrictions = personstatus.getRestrictedActions();
		for(Character c : restrictions) {
			while(a.contains(c)) {
				a.remove(c);
			}
		}
		HashSet<Character> weaponRestrictions = weapon.getRestrictedActions(); //things your weapon won't let you do!
		for(Character c : weaponRestrictions) {
			if(a.contains(c)) {
				a.remove(c);
			}
		}
		
		if(a.size() == 0)
			a.add("w 100");
		
		RandGen rand = new RandGen();
		int x = rand.getRand(0, a.size() - 1);
		return a.get(x);
		
	}
	
}
