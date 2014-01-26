package person;

import weapon.melee.fists.*;
import weapon.melee.sword.Dagger;
import weapon.ranged.explosive.RocketLauncher;
import weapon.ranged.longrange.SniperRifle;
import game.RandGen;

public class EnemyBasic extends Enemy {
	
	public EnemyBasic() {
		name = createRandomName();
		health = 750;
		
		int i = RandGen.getRand(1, 4);
		switch(i) {
			case 1: weapon = new Fists(); break;
			case 2: weapon = new Dagger(); break;
			case 3: weapon = new RocketLauncher(); break;
			case 4: weapon = new SniperRifle(); break;
			default: weapon = new Fists();
		}
	}

	public char getAction() {
		int x = RandGen.getRand(1, 7);
		switch(x){		
		case 1: return 'e';
		case 2: return 'a';
		case 3: return 'a';
		case 4: return 'd';
		case 5: return 'd';
		case 6: return 'r';
		case 7: return 'a';
		default: return '?';
		}
	}
	
}
