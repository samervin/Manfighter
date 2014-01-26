package game;

import java.util.HashSet;
import java.util.Scanner;

import person.Enemy;
import person.EnemyBasic;
import person.Player;
import weapon.melee.fists.Fists;
import weapon.melee.sword.Dagger;
import weapon.ranged.explosive.RocketLauncher;
import weapon.ranged.longrange.SniperRifle;

public class Manfighter {

	Scanner in = new Scanner(System.in);
	int close = 60; //minimum distance apart, in cm

	public static void main(String[] args) {
		new Manfighter();
	}

	public Manfighter() {
		System.out.println("Welcome to Manfighter! What's your name?");
		String stemp = in.nextLine();
		if(stringDivisibleBy(stemp, 2)) {
			while(stringDivisibleBy(stemp, 2)) {
				System.out.println("That name sucks. Try again.");
				stemp = in.nextLine();
			}
			System.out.println("Okay, fine, I suppose.");
		}

		int itemp = RandGen.getRand(1, 4);
		Player p;
		switch(itemp) {
		case 1: p = new Player(stemp, new Fists()); break;
		case 2: p = new Player(stemp, new Dagger()); break;
		case 3: p = new Player(stemp, new RocketLauncher()); break;
		case 4: p = new Player(stemp, new SniperRifle()); break;
		default: p = new Player(stemp, new Fists());
		}

		System.out.println("\nStep into the battleground, " + p.getName() + "!");
		System.out.println("You found a new weapon: " + p.getWeapon() + "!");

		Enemy e = new EnemyBasic();
		System.out.println("Your first opponent is " + e.getName() + "! \nHis weapon is: " + e.getWeapon() + "! \nGood luck!");
		combat(p, e);
	}


	private void combat(Player p, Enemy e) {
		p.setLocation(300);
		e.setLocation(0);

		while(p.getHealth() > 0 && e.getHealth() > 0) {
			combatTurn(p, e);
		}

		if(p.getHealth() < 1)
			System.out.println("You lost, better luck next time!");
		else
			System.out.println("Congratulations, you defeated " + e.getName() + "!");
	}

	private void combatTurn(Player p, Enemy e) {
		HashSet<Character> allactions = p.getActions();
		System.out.print("Will you: ");
		if(allactions.contains('e'))
			System.out.print("ready your weapon[e], ");
		if(allactions.contains('l'))
			System.out.print("lower your weapon[l], ");
		if(allactions.contains('a'))
			System.out.print("attack[a], ");
		if(allactions.contains('d') && canAdvance(p, e))
			System.out.print("advance[d], ");
		if(allactions.contains('r'))
			System.out.print("retreat[r], ");
		System.out.print("move[m], or wait[w]?\n");


		char action = in.nextLine().toLowerCase().charAt(0);
		if(action == 'e' && allactions.contains('e')) {
			p.getWeapon().setReadied(true);
			System.out.println("You readied your " + p.getWeapon() + ". Movement speed lowered.");
		} else if(action == 'l' && allactions.contains('l')) {	
			p.getWeapon().setReadied(false);
			System.out.println("You lowered your " + p.getWeapon() + ". Movement speed increased.");
		} else if(action == 'a' && allactions.contains('a')) {
			if(p.getWeapon().getRange() >= Math.abs(p.getLocation() - e.getLocation())) {
				int dmg = p.getWeapon().getDamage();
				System.out.println("You're dealing " + dmg + " damage!");
				e.setHealth(e.getHealth() - dmg);
				System.out.println(e.getName() + "'s new health is " + e.getHealth() + ".");
			}
			else {
				System.out.println("You tried to attack, but you're not in range!");
			}
		} else if(action == 'd' && allactions.contains('d')) {
			if(p.getWeapon().isReadied()) {
				int dis = move(p, e, 60, 0);
				System.out.println("You stepped forward " + dis + " cm.");
				System.out.println("You're now " + Math.abs(p.getLocation() - e.getLocation()) + " cm apart.");
			} else {
				int dis = move(p, e, 75, 0);
				System.out.println("You stepped forward " + dis + " cm.");
				System.out.println("You're now " + Math.abs(p.getLocation() - e.getLocation()) + " cm apart.");
			}

		} else if(action == 'r' && allactions.contains('r')) {
			if(p.getWeapon().isReadied()) {
				int dis = move(p, e, -45, 0);
				System.out.println("You stepped backward " + dis + " cm.");
				System.out.println("You're now " + Math.abs(p.getLocation() - e.getLocation()) + " cm apart.");
			} else {
				int dis = move(p, e, -60, 0);
				System.out.println("You stepped backward " + dis + " cm.");
				System.out.println("You're now " + Math.abs(p.getLocation() - e.getLocation()) + " cm apart.");
			}

		} else if(action == 'm') {
			System.out.print("Enter the number of cm you wish to move towards your enemy (negative values retreat): ");
			
			//TODO: better parsing
			int distance = Integer.parseInt(in.nextLine());
			if(p.getWeapon().isReadied() && distance >= -45 && distance <=0) {
				int dis = move(p, e, distance, 0);
				System.out.println("You stepped backward " + dis + " cm.");
				System.out.println("You're now " + Math.abs(p.getLocation() - e.getLocation()) + " cm apart.");
			} else if(p.getWeapon().isReadied() && distance <=60 && distance >=0) {
				int dis = move(p, e, distance, 0);
				System.out.println("You stepped forward " + dis + " cm.");
				System.out.println("You're now " + Math.abs(p.getLocation() - e.getLocation()) + " cm apart.");
			} else if(!p.getWeapon().isReadied() && distance >= -60 && distance <=0) {
				int dis = move(p, e, distance, 0);
				System.out.println("You stepped backward " + dis + " cm.");
				System.out.println("You're now " + Math.abs(p.getLocation() - e.getLocation()) + " cm apart.");
			} else if(!p.getWeapon().isReadied() && distance <=75 && distance >=0) {
				int dis = move(p, e, distance, 0);
				System.out.println("You stepped forward " + dis + " cm.");
				System.out.println("You're now " + Math.abs(p.getLocation() - e.getLocation()) + " cm apart.");
			} else {
				//TODO: also dumb
				System.out.println("You can't move that far, you dummy.");
			}
		} else if(action == 'w') {
			System.out.println("You're waiting a turn.");
		} else {
			//TODO: this is dumb
			System.out.println("Not an option, sorry, you lost a turn.");
		}
		
		System.out.println();
		if(p.getHealth() < 1 || e.getHealth() < 1) return;





		
		

		char reaction = e.getAction();
		switch(reaction) {
		case 'e':
			e.getWeapon().setReadied(true);
			System.out.println(e.getName() + " readied his " + e.getWeapon() + ". His movement speed is lowered.");
			break;
		case 'l':
			e.getWeapon().setReadied(false);
			System.out.println(e.getName() + " lowered his " + e.getWeapon() + ". His movement speed is increased.");
			break;
		case 'a':
			if(e.getWeapon().getRange() >= Math.abs(p.getLocation() - e.getLocation())) {
				int dmg = e.getWeapon().getDamage();
				System.out.println(e.getName() + " is dealing " + dmg + " damage!");
				p.setHealth(p.getHealth() - dmg);
				System.out.println(p.getName() + "'s new health is " + p.getHealth() + ".");
			}
			else {
				System.out.println(e.getName() + " tried to attack, but is not in range!");
			}
			break;
		case 'd':			
			if(e.getWeapon().isReadied()) {
				int dis = move(p, e, 60, 0);
				System.out.println(e.getName() + " stepped forward " + dis + " cm.");			
				System.out.println("You're now " + Math.abs(p.getLocation() - e.getLocation()) + " cm apart.");
			} else {
				int dis = move(p, e, 75, 0);
				System.out.println(e.getName() + " stepped forward " + dis + " cm.");			
				System.out.println("You're now " + Math.abs(p.getLocation() - e.getLocation()) + " cm apart.");
			}

			break;
		case 'r':			
			if(e.getWeapon().isReadied()) {
				int dis = move(p, e, -45, 0);
				System.out.println(e.getName() + " stepped backward " + dis + " cm.");
				System.out.println("You're now " + Math.abs(p.getLocation() - e.getLocation()) + " cm apart.");
			} else {
				int dis = move(p, e, -60, 0);
				System.out.println(e.getName() + " stepped backward " + dis + " cm.");
				System.out.println("You're now " + Math.abs(p.getLocation() - e.getLocation()) + " cm apart.");
			}

			break;
		case 'w':
			System.out.println(e.getName() + " is waiting a turn.");
			break;
		default:
			System.out.println("The computer accidentally did this: " + reaction);
		}
	}


	//positive value for newP or newE indicates that they are moving TOWARD their target
	//negative value for newP or newE indicates that they are moving AWAY FROM their target
	//returns centimeters moved
	private int move(Player p, Enemy e, int pmove, int emove) {
		int ploc = p.getLocation();
		int eloc = e.getLocation();
		//close = 60; min distance

		if(ploc < eloc) { //player is left of enemy
			if(pmove > 0) { //player is attempting to advance
				if(eloc - ploc > pmove + close) { //if player has space to move (cannot move through people)
					p.setLocation(ploc + pmove);
					return pmove;
				}
				else { //get as close as possible
					p.setLocation(eloc - close);
					return eloc - ploc - close;
				}
			}
			else if(pmove < 0) { //player is attempting to retreat
				p.setLocation(ploc + pmove);
				return -pmove;
			}
			else if(emove > 0) { //enemy is attempting to advance
				if(eloc - ploc > emove + close) { //if enemy has space to move
					e.setLocation(eloc - emove);
					return emove;
				}
				else { //get as close as possible
					e.setLocation(ploc + close);
					return eloc - ploc - close;
				}
			}
			else if(emove < 0) { //enemy is attempting to retreat
				e.setLocation(eloc - emove);
				return -emove;
			}
		}
		else { //player is right of enemy (signs flip)
			if(pmove > 0) { //player is attempting to advance
				if(ploc - eloc > pmove + close) { //if player has space to move (cannot move through people)
					p.setLocation(ploc - pmove);
					return pmove;
				}
				else { //get as close as possible
					p.setLocation(eloc + close);
					return ploc - eloc - close;
				}
			}
			else if(pmove < 0) { //player is attempting to retreat
				p.setLocation(ploc - pmove);
				return -pmove;
			}
			else if(emove > 0) { //enemy is attempting to advance
				if(ploc - eloc > emove + close) { //if enemy has space to move
					e.setLocation(eloc + emove);
					return emove;
				}
				else { //get as close as possible
					e.setLocation(ploc - close);
					return ploc - eloc - close;
				}
			}
			else if(emove < 0) { //enemy is attempting to retreat
				e.setLocation(eloc + emove);
				return -emove;
			}
		}

		return 0;
	}

	private boolean canAdvance(Player p, Enemy e) {
		if(Math.abs(p.getLocation() - e.getLocation()) == close)
			return false;
		return true;
	}
	
	private boolean stringDivisibleBy(String str, int num) {
		char[] a = str.toCharArray();
		int in = 0;
		for(int k = 0; k < a.length; k++) {
			in += a[k]; //47
		}
		
		return (in % num == 0);
	}
}
