package person;

import game.Person;
import game.RandGen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public abstract class Enemy extends Person {

	public abstract char getAction(int distance);

	public HashSet<Character> getActions() {

		HashSet<Character> a = weapon.getWeaponActions();
		a.add('d'); //advance
		a.add('r'); //retreat

		return a;
	}

	protected String createRandomName() {
		String n = "";
		int x = RandGen.getRand(1, 50);
		if(x == 50) {
			Scanner special;
			try {
				special = new Scanner(new File("src/data/name_special.txt"));
				int max = Integer.parseInt(special.nextLine()) -1; //off-by-one errors
				int maxRand = RandGen.getRand(0, max);
				for(int i = 0; i < maxRand; i++) {
					special.nextLine();
				}
				n += special.nextLine();
				special.close();
				
			} catch (FileNotFoundException e) {
				n = "name_special.txt not found";
			}
			
		} else {
			if(x % 4 == 0) {
				Scanner prefix;
				try {
					prefix = new Scanner(new File("src/data/name_prefix.txt"));
					int max = Integer.parseInt(prefix.nextLine()) -1; //off-by-one errors
					int maxRand = RandGen.getRand(0, max);
					for(int i = 0; i < maxRand; i++) {
						prefix.nextLine();
					}
					n += prefix.nextLine() + " ";
					prefix.close();
					
				} catch (FileNotFoundException e) {
					n = "name_prefix.txt not found";
				}
			}
			
			Scanner first;
			try {
				first = new Scanner(new File("src/data/name_first.txt"));
				int max = Integer.parseInt(first.nextLine()) -1; //off-by-one errors
				int maxRand = RandGen.getRand(0, max);
				for(int i = 0; i < maxRand; i++) {
					first.nextLine();
				}
				n += first.nextLine();
				first.close();
				
			} catch (FileNotFoundException e) {
				n = "name_first.txt not found";
			}
			
			if(x < 45) {
				Scanner last;
				try {
					last = new Scanner(new File("src/data/name_last.txt"));
					int max = Integer.parseInt(last.nextLine()) -1; //off-by-one errors
					int maxRand = RandGen.getRand(0, max);
					for(int i = 0; i < maxRand; i++) {
						last.nextLine();
					}
					n += " " + last.nextLine();
					last.close();
					
				} catch (FileNotFoundException e) {
					n = "name_last.txt not found";
				}
			}
		}

		return n;
	}
}