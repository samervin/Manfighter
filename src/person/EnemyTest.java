package person;

import weapon.Fists;

public class EnemyTest extends Enemy{

	private int turn = 0;
	
	public EnemyTest() {
		name = "TEST ENEMY";
		health = 10000;
		weapon = new Fists();
	}

	public char getAction() {
		turn++;
		
		switch(turn){		
		case 1: return 'd';
		case 2: return 'd'; 
		case 3: return 'a';
		case 4: return 'a';
		case 5: return 'a';
		default: return 'a';
		}
	}
	
}