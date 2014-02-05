package person;

import game.RandGen;

import java.util.ArrayList;
import java.util.HashSet;

public class EnemyBasic extends Enemy {
	
	public EnemyBasic() {
		name = createRandomName();
		health = 750;
		weapon = getRandomWeapon();
	}

	public char getAction(int distance) {
		ArrayList<Character> a = new ArrayList<Character>();
		HashSet<Character> valids = weapon.getWeaponActions();
		
		if(valids.contains('e')) {
			for(int i = 0; i < 7; i++) {
				a.add('e');
			}
		}
		
		if(distance <= 2 * weapon.getRange() / 3) {
			for(int i = 0; i < 3; i++)
				a.add('r');
		}
		
		if(distance <= weapon.getRange() && valids.contains('a')) {
			for(int i = 0; i < 8; i++)
				a.add('a');
		} else if (distance >= 2 * weapon.getRange()){
			for(int i = 0; i < 6; i++) {
				a.add('d');
			}
			
			if(valids.contains('l')) {
				a.add('l');
			}
		} else if(distance >= weapon.getRange()){
			for(int i = 0; i < 4; i++) {
				a.add('d');
			}
			//a.add('w');
		}
		
		if(!weapon.hasFullAmmo() && valids.contains('o')) {
			for(int i = 0; i < 2; i++)
				a.add('o');
		}
		
		if(!weapon.hasLoadedAmmo() && valids.contains('o')) {			
			for(int i = 0; i < 10; i++)
				a.add('o');
		}
		
		
		
		HashSet<Character> restrictions = personstatus.getRestrictedActions();
		for(Character c : restrictions) {
			while(a.contains(c)) {
				a.remove(c);
			}
		}
		
		if(a.size() == 0)
			a.add('w');
		
		System.out.println(">>Enemy choices: " + a);
		
		int x = RandGen.getRand(0, a.size() - 1);
		return a.get(x);
		
	}
	
}
