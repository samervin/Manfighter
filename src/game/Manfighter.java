package game;

import java.util.Scanner;
import person.*;
import weapon.*;

public class Manfighter {

	Scanner in = new Scanner(System.in);

	public static void main(String[] args) {
		new Manfighter();
	}

	public Manfighter() {
		int itemp = RandGen.getRand(1, 3);

		System.out.println("Welcome to Manfighter! What's your name?");
		String stemp = in.nextLine();
		if(itemp == 1) {
			System.out.println("That name sucks. Try again.");
			stemp = in.nextLine();
			System.out.println("Okay, fine, I suppose.");
		}

		itemp = RandGen.getRand(1, 3);
		Player p;
		switch(itemp) {
		case 1: p = new Player(stemp, new Fists()); break;
		case 2: p = new Player(stemp, new Dagger()); break;
		case 3: p = new Player(stemp, new RocketLauncher()); break;
		default: p = new Player(stemp, new Fists());
		}

		System.out.println("\nStep into the battleground, " + p.getName() + "!");
		System.out.println("You found a new weapon: " + p.getWeapon() + "!");

		Enemy e = new EnemyBasic();
		System.out.println("Your first opponent is " + e.getName() + "! \nHis weapon is: " + e.getWeapon() + "! \nGood luck!");

		combat(p, e);
	}


	private void combat(Player p, Enemy e) {
		p.setLocation(0);
		e.setLocation(200);

		while(p.getHealth() > 0 && e.getHealth() > 0) {
			combatTurn(p, e);
		}

		if(p.getHealth() < 1)
			System.out.println("You lost, better luck next time!");
		else
			System.out.println("Congratulations, you defeated " + e.getName() + "!");
	}

	private void combatTurn(Player p, Enemy e) {
		System.out.println("Will you attack(a), advance(d), retreat(r), or wait(w)?");
		char action = in.nextLine().toLowerCase().charAt(0);

		switch(action) {
		case 'a':
			if(p.getWeapon().getRange() >= Math.abs(p.getLocation() - e.getLocation())) {
				int dmg = p.getWeapon().getDamage();
				System.out.println("You're dealing " + dmg + " damage!");
				e.setHealth(e.getHealth() - dmg);
				System.out.println(e.getName() + "'s new health is " + e.getHealth() + ".");
			}
			else {
				System.out.println("You tried to attack, but you're not in range!");
			}
			break;
		case 'd':
			System.out.println("You stepped forward 75 cm.");
			//TODO: advance/retreat are absolute, this doesn't make much sense
			p.setLocation(p.getLocation() + 75);
			System.out.println("You're now " + Math.abs(p.getLocation() - e.getLocation()) + " cm apart.");
			break;
		case 'r':
			System.out.println("You stepped backward 60 cm.");
			p.setLocation(p.getLocation() - 60);
			System.out.println("You're now " + Math.abs(p.getLocation() - e.getLocation()) + " cm apart.");
			break;
		case 'w':
			System.out.println("You're waiting a turn.");
			break;
		default:
			//TODO: this is dumb
			System.out.println("Not an option, sorry, you lost a turn.");
		}
		System.out.println();

		if(p.getHealth() < 1 || e.getHealth() < 1) return;

		char reaction = e.getAction();
		switch(reaction) {
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
			System.out.println(e.getName() + " stepped forward 75 cm.");
			e.setLocation(e.getLocation() - 75);
			System.out.println("You're now " + Math.abs(p.getLocation() - e.getLocation()) + " cm apart.");
			break;
		case 'r':
			System.out.println(e.getName() + " stepped backward 60 cm.");
			e.setLocation(e.getLocation() + 60);
			System.out.println("You're now " + Math.abs(p.getLocation() - e.getLocation()) + " cm apart.");
			break;
		case 'w':
			System.out.println(e.getName() + " is waiting a turn.");
			break;
		default:
			System.out.println("The computer did something it can't do...?");
		}
	}
}
