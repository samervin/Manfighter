package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import status.weapon.neutral.BlankWeaponStatus;
import status.weapon.neutral.CritUp;
import status.weapon.neutral.DamageUp;
import status.weapon.neutral.FireRateUp;
import status.weapon.neutral.WeaponStatus;
import status.weapon.ranged.AccuracyDown;
import status.weapon.ranged.RangeUp;
import weapon.Weapon;
import weapon.melee.axe.Hatchet;
import weapon.melee.club.CardboardTube;
import weapon.melee.club.Mace;
import weapon.melee.fists.BoxingGloves;
import weapon.melee.fists.BrassKnuckles;
import weapon.melee.fists.Fists;
import weapon.melee.fists.HandWraps;
import weapon.melee.hammer.WarHammer;
import weapon.melee.knife.Switchblade;
import weapon.melee.other.CrosscutSaw;
import weapon.melee.polearm.Pike;
import weapon.melee.sword.Dagger;
import weapon.ranged.burst.Blunderbuss;
import weapon.ranged.explosive.GrenadeLauncher;
import weapon.ranged.explosive.RocketLauncher;
import weapon.ranged.longrange.SniperRifle;
import weapon.ranged.oneshot.BBGun;
import weapon.ranged.rapidfire.MiniSMG;
import weapon.ranged.unpowered.Shortbow;
import armor.HeadArmor;
import armor.head.BaseballCap;
import armor.head.BlankHeadArmor;
import armor.head.HadesHelm;
import armor.head.VikingHelm;

public class ManfighterGenerator {
	
	HashMap<String, Weapon> eggs = new HashMap<String, Weapon>();
	RandGen rand = new RandGen();
	
	public int[] getGlobalData() {
		int[] data = new int[4];
		try {
			Scanner in = new Scanner(new File("data/global/global_stats.txt"));
			while(in.hasNextLine()) {
				String[] line = in.nextLine().split(" ");
				if(line[0].equals("minimum-distance-apart"))
					data[0] = Integer.parseInt(line[1]);
				else if(line[0].equals("default-step"))
					data[1] = Integer.parseInt(line[1]);
				else if(line[0].equals("time-per-cm-stepped"))
					data[2] = Integer.parseInt(line[1]);
				else if(line[0].equals("slow-time-per-cm-stepped"))
					data[3] = Integer.parseInt(line[1]);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return data;
	}

	public boolean isValidName(String name) {
		return (!stringDivisibleBy(name, 3)) || isEgg(name);
	}
	
	public String getRandomGame() {
		if(rand.getOdds(99, 100))
			return "Manfighter";
		else {
			ArrayList<String> names = new ArrayList<String>();
			names.add("Mansmasher");
			names.add("Manwrestler");
			int x = rand.getRand(0, names.size() - 1);
			return names.get(x);
		}
	}
	
	public Weapon getRandomWeapon(String name) {
		if(isEgg(name)) {
			return eggs.get(name);
		}
		return getRandomWeapon();
	}
	
	public Weapon getRandomWeapon() {
		if(rand.getOdds(1, 100))
			return getRandomRareWeapon();
		
		ArrayList<Weapon> allWeapons = new ArrayList<Weapon>();
		allWeapons.add(new Hatchet());
		allWeapons.add(new Mace());
		allWeapons.add(new BoxingGloves());
			allWeapons.add(new BrassKnuckles());
			allWeapons.add(new Fists());
			allWeapons.add(new HandWraps());
		allWeapons.add(new WarHammer());
		allWeapons.add(new Switchblade());
		allWeapons.add(new CrosscutSaw());
		allWeapons.add(new Pike());
		allWeapons.add(new Dagger());
		allWeapons.add(new Blunderbuss());
		allWeapons.add(new GrenadeLauncher());
			allWeapons.add(new RocketLauncher());
		allWeapons.add(new SniperRifle());
		allWeapons.add(new BBGun());
		allWeapons.add(new MiniSMG());
		allWeapons.add(new Shortbow());
		
		int x = rand.getRand(0, allWeapons.size() - 1);
		return allWeapons.get(x);
	}
	
	public Weapon getRandomRareWeapon() {
		ArrayList<Weapon> rareWeapons = new ArrayList<Weapon>();
		rareWeapons.add(new CardboardTube());
		
		int x = rand.getRand(0, rareWeapons.size() - 1);
		return rareWeapons.get(x);
	}
	
	public HeadArmor getRandomHeadArmor() {
		if(rand.getOdds(1, 3))
			return new BlankHeadArmor();
		if(rand.getOdds(1, 100))
			return getRandomRareHeadArmor();
		
		ArrayList<HeadArmor> allHeads = new ArrayList<HeadArmor>();
		allHeads.add(new BaseballCap());
		allHeads.add(new VikingHelm());
		
		int x = rand.getRand(0, allHeads.size() -1);
		return allHeads.get(x);
	}
	
	public HeadArmor getRandomRareHeadArmor() {
		ArrayList<HeadArmor> rareHeads = new ArrayList<HeadArmor>();
		rareHeads.add(new HadesHelm());
		
		int x = rand.getRand(0, rareHeads.size() -1);
		return rareHeads.get(x);
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
		String folder = "data/names/";
		String n = "";
		
		int x = rand.getRand(1, 50);
		if(x == 50) {
			Scanner special;
			try {
				special = new Scanner(new File(folder + "name_special.txt"));
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
					prefix = new Scanner(new File(folder + "name_prefix.txt"));
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
				first = new Scanner(new File(folder + "name_first.txt"));
				ArrayList<String> firstNames = new ArrayList<String>();
				while(first.hasNextLine())
					firstNames.add(first.nextLine());
				
				n+= firstNames.get(rand.getRand(0, firstNames.size()-1));
				first.close();
				
			} catch (FileNotFoundException e) {
				n = "name_first.txt not found";
			}
			
			if(x < 44) {
				Scanner last;
				try {
					last = new Scanner(new File(folder + "name_last.txt"));
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
	
	public WeaponStatus getRandomRangedStatus() {
		if(rand.getOdds(1, 2))
			return new BlankWeaponStatus();
		
		ArrayList<WeaponStatus> allStati = new ArrayList<WeaponStatus>();
		allStati.add(new DamageUp());
		allStati.add(new CritUp());
		allStati.add(new RangeUp());
		allStati.add(new AccuracyDown());
		allStati.add(new FireRateUp());
		
		int x = rand.getRand(0, allStati.size()-1);
		return allStati.get(x);
	}
	
	public WeaponStatus getRandomNeutralStatus() {
		if(rand.getOdds(1, 2))
			return new BlankWeaponStatus();
		
		ArrayList<WeaponStatus> allStati = new ArrayList<WeaponStatus>();
		allStati.add(new DamageUp());
		allStati.add(new CritUp());
		allStati.add(new FireRateUp());
		
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
			eggs.put("Pribs", new CardboardTube());
			eggs.put("Blunderbuss", new Blunderbuss());
			eggs.put("again.", new Fists());
		}
	}
	
	private boolean isEgg(String name) {
		seedEggs();
		return eggs.keySet().contains(name);
	}
	
}
