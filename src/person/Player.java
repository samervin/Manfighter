package person;

import game.Person;
import java.util.HashSet;

public class Player extends Person {

	private final int maxhealth = 750;

	public Player(String n) {
		name = n;
		weapon = getRandomWeapon();
		health = maxhealth;
	}

	public HashSet<Character> getActions() {

		HashSet<Character> a = weapon.getWeaponActions(); //covers attack, ready, lower, reload
		a.add('d'); //advance
		a.add('r'); //retreat
		
		//TODO: these need to matter
		a.add('m'); //move
		a.add('w'); //wait
				
		HashSet<Character> restrictions = personstatus.getRestrictedActions();
		for(Character c : restrictions) {
			if(a.contains(c)) {
				a.remove(c);
			}
		}
		
		return a;
	}	

}
