package person;

import weapon.*;
import game.RandGen;

public class EnemyBasic extends Enemy {
	
	public EnemyBasic() {
		name = createRandomName();
		health = 100;
		
		int i = RandGen.getRand(1, 3);
		switch(i) {
			case 1: weapon = new Fists(); break;
			case 2: weapon = new Dagger(); break;
			case 3: weapon = new RocketLauncher(); break;
			default: weapon = new Fists();
		}
	}

	public char getAction() {
		int x = RandGen.getRand(1, 4);
		switch(x){
		case 1: return 'w';
		case 2: return 'a';
		case 3: return 'd';
		case 4: return 'r';
		default: return '?';
		}
	}
	
}
