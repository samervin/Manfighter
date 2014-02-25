package person;

import game.Person;

import java.util.HashSet;

public abstract class Enemy extends Person {

	public abstract String getAction(int distance);

	public HashSet<Character> getValidActions() {

		HashSet<Character> a = weapon.getWeaponActions();
		a.add('d'); //advance
		a.add('e'); //retreat
		a.add('w');

		return a;
	}
}