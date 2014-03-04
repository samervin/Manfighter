package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import status.weapon.AccuracyDown;
import status.weapon.BlankWeaponStatus;
import status.weapon.CritUp;
import status.weapon.DamageUp;
import status.weapon.FireRateUp;
import status.weapon.RangeUp;
import status.weapon.WeaponStatus;
import weapon.Weapon;
import weapon.melee.axe.Hatchet;
import weapon.melee.club.CardboardTube;
import weapon.melee.club.Mace;
import weapon.melee.fists.BoxingGloves;
import weapon.melee.fists.BrassKnuckles;
import weapon.melee.fists.Fists;
import weapon.melee.hammer.WarHammer;
import weapon.melee.knife.Switchblade;
import weapon.melee.saw.HandSaw;
import weapon.melee.sword.Dagger;
import weapon.ranged.burst.Blunderbuss;
import weapon.ranged.explosive.GrenadeLauncher;
import weapon.ranged.explosive.RocketLauncher;
import weapon.ranged.longrange.SniperRifle;
import weapon.ranged.oneshot.BBGun;
import weapon.ranged.rapidfire.MiniSMG;
import weapon.ranged.unpowered.Shortbow;
import armor.HeadArmor;
import armor.head.BlankHeadArmor;
import armor.head.Helmet;

public class ManfighterGenerator {
	
	HashMap<String, Weapon> eggs = new HashMap<String, Weapon>();
	RandGen rand = new RandGen();

	public boolean isValidName(String name) {
		return (!stringDivisibleBy(name, 3)) || isEgg(name);
	}
	
	public Weapon getRandomWeapon(String name) {
		if(isEgg(name)) {
			return eggs.get(name);
		}
		return getRandomWeapon();
	}
	
	public Weapon getRandomWeapon() {
		ArrayList<Weapon> allWeapons = new ArrayList<Weapon>();
		allWeapons.add(new Hatchet());
		allWeapons.add(new Mace());
		allWeapons.add(new BoxingGloves()); allWeapons.add(new BrassKnuckles()); allWeapons.add(new Fists());
		allWeapons.add(new WarHammer());
		allWeapons.add(new Switchblade());
		allWeapons.add(new HandSaw());
		allWeapons.add(new Dagger());
		allWeapons.add(new Blunderbuss());
		allWeapons.add(new GrenadeLauncher()); allWeapons.add(new RocketLauncher());
		allWeapons.add(new SniperRifle());
		allWeapons.add(new BBGun());
		allWeapons.add(new MiniSMG());
		allWeapons.add(new Shortbow());
		
		int x = rand.getRand(0, allWeapons.size() - 1);
		return allWeapons.get(x);
	}
	
	public HeadArmor getRandomHeadArmor() {
		ArrayList<HeadArmor> allHeads = new ArrayList<HeadArmor>();
		allHeads.add(new BlankHeadArmor());
		allHeads.add(new Helmet());
		
		int x = rand.getRand(0, allHeads.size() -1);
		return allHeads.get(x);
	}
	
	public String getRandomLocation() {
		ArrayList<String> bodyParts = new ArrayList<String>();
		bodyParts.add("head");
		bodyParts.add("torso");
		bodyParts.add("arms");
		bodyParts.add("legs");
		
		int x = rand.getRand(0, bodyParts.size() - 1);
		return bodyParts.get(x);
	}
	
	public String createRandomName() {
		RandGen rand = new RandGen();
		String n = "";
		
		int x = rand.getRand(1, 50);
		if(x == 50) {
			Scanner special;
			try {
				special = new Scanner(new File("data/name_special.txt"));
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
					prefix = new Scanner(new File("data/name_prefix.txt"));
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
				first = new Scanner(new File("data/name_first.txt"));
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
					last = new Scanner(new File("data/name_last.txt"));
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
	
	public WeaponStatus getRandomStatus() {
		RandGen rand = new RandGen();
		ArrayList<WeaponStatus> allStati = new ArrayList<WeaponStatus>();
		allStati.add(new DamageUp());
		allStati.add(new CritUp());
		allStati.add(new RangeUp());
		allStati.add(new AccuracyDown());
		allStati.add(new FireRateUp());
		
		int size = allStati.size();
		for(int i = 0; i < size; i++)
			allStati.add(new BlankWeaponStatus());
		
		int x = rand.getRand(0, allStati.size()-1);
		return allStati.get(x);
	}
	
	
	
	private boolean stringDivisibleBy(String str, int num) {
		char[] a = str.toCharArray();
		int in = 0;
		for(int k = 0; k < a.length; k++) {
			in += a[k]; //47
		}

		return (in % num == 0);
	}
	
	private void seedEggs() {
		if(eggs.size() == 0) {
			eggs.put("Cassandra", new Shortbow());
			eggs.put("Pribs", new CardboardTube());
		}
	}
	
	private boolean isEgg(String name) {
		seedEggs();
		
		for(String n : eggs.keySet()) {
			if(name.equals(n))
				return true;
		}
		return false;
	}
	
}
