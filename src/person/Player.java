package person;

import game.ManfighterGenerator;

import java.util.HashSet;

public class Player extends Person {

	private final int maxhealth = 1000;

	public Player(String n) {
		name = n;
		weapon = new ManfighterGenerator().getRandomWeapon(name);
		health = maxhealth;
	}

	@Override
	public HashSet<Character> getValidActions() {

		HashSet<Character> a = weapon.getWeaponActions(); //covers attack, ready, lower, reload, aim
		a.add('d'); //advance
		a.add('e'); //retreat
		
		//TODO: these need to matter
		a.add('m'); //move
		a.add('w'); //wait
				
		HashSet<Character> statusRestrictions = personstatus.getRestrictedActions();
		for(Character c : statusRestrictions) {
			if(a.contains(c)) {
				a.remove(c);
			}
		}
		HashSet<Character> weaponRestrictions = weapon.getRestrictedActions(); //things your weapon won't let you do!
		for(Character c : weaponRestrictions) {
			if(a.contains(c)) {
				a.remove(c);
			}
		}
		
		return a;
	}
}
