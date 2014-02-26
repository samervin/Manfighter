package weapon.melee.club;

import game.ManfighterGenerator;

import java.util.HashSet;

import status.person.Bruised;

public class CardboardTube extends BaseClub {

	public CardboardTube() {
		weaponStatus = new ManfighterGenerator().getRandomStatus();
		inflictingStatus = new Bruised();
		damage = 10;
		range = 100;
		fireTime = 600;
		readyTime = 300;
	}
	
	@Override
	public String getBaseName() {
		return "Cardboard tube";
	}
	
	@Override
	public int getDamage(int distance) {
		if(ready) {
			ready = false;
			return weaponStatus.getDamage(damage);
		}
		
		return 0;
	}

	@Override
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		
		if(ready) {
			a.add('a');
			a.add('l');
		}	
		else
			a.add('r');
		
		return a;
	}
}