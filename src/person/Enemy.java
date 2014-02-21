package person;

import game.Person;
import game.RandGen;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public abstract class Enemy extends Person {

	public abstract char getAction(int distance);

	public HashSet<Character> getActions() {

		HashSet<Character> a = weapon.getWeaponActions();
		a.add('d'); //advance
		a.add('e'); //retreat

		return a;
	}

	protected String createRandomName() {
		RandGen rand = new RandGen();
		String n = "";
		
		int x = rand.getRand(1, 50);
		if(x == 50) {
			Scanner special;
			try {
				special = new Scanner(new File("src/data/name_special.txt"));
				ArrayList<String> specialNames = new ArrayList<String>();
				while(special.hasNextLine())
					specialNames.add(special.nextLine());
				
				n+= specialNames.get(rand.getRand(0, specialNames.size()-1));
				special.close();
				
			} catch (FileNotFoundException e) {
				n = "name_special.txt not found";
			}
			
		} else {
			if(x % 4 == 0) {
				Scanner prefix;
				try {
					prefix = new Scanner(new File("src/data/name_prefix.txt"));
					ArrayList<String> prefixNames = new ArrayList<String>();
					while(prefix.hasNextLine())
						prefixNames.add(prefix.nextLine());
					
					n+= prefixNames.get(rand.getRand(0, prefixNames.size()-1)) + " ";
					prefix.close();
					
				} catch (FileNotFoundException e) {
					n = "name_prefix.txt not found";
				}
			}
			
			Scanner first;
			try {
				first = new Scanner(new File("src/data/name_first.txt"));
				ArrayList<String> firstNames = new ArrayList<String>();
				while(first.hasNextLine())
					firstNames.add(first.nextLine());
				
				n+= firstNames.get(rand.getRand(0, firstNames.size()-1));
				first.close();
				
			} catch (FileNotFoundException e) {
				n = "name_first.txt not found";
			}
			
			if(x < 45) {
				Scanner last;
				try {
					last = new Scanner(new File("src/data/name_last.txt"));
					ArrayList<String> lastNames = new ArrayList<String>();
					while(last.hasNextLine())
						lastNames.add(last.nextLine());
					
					n+= " " + lastNames.get(rand.getRand(0, lastNames.size()-1));
					last.close();
					
				} catch (FileNotFoundException e) {
					n = "name_last.txt not found";
				}
			}
		}

		return n;
	}
}