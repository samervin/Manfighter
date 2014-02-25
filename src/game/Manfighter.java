package game;

import java.util.HashSet;
import java.util.Scanner;

import person.Enemy;
import person.EnemyBasic;
import person.Player;
import status.person.BlankPersonStatus;

public class Manfighter {

	private int TEST = 0; //1 for quicker testing, 0 for general play

	private Scanner in = new Scanner(System.in);
	private ManfighterGenerator mfg = new ManfighterGenerator();
	private final int close = 60; //minimum distance apart, in cm
	private final int forwardStep = 85; //how far you step forward
	private final int backwardStep = 70; //how far you step backward
	private final int timeStep = 650; //seconds per step
	private final int timeOther = 500; //place holder for "other" actions

	public static void main(String[] args) {
		new Manfighter();
	}

	public Manfighter() {
		String stemp;
		if(TEST == 0) {
			System.out.println("Welcome to Manfighter! What's your name?");
			stemp = in.nextLine().trim();
			if(!mfg.isValidName(stemp)) {
				while(!mfg.isValidName(stemp)) {
					System.out.println("That name sucks. Try again.");
					stemp = in.nextLine().trim();
				}
				System.out.println("Okay, fine, I suppose.");
			}	
		} else {
			stemp = "Sam";
		}

		Player p = new Player(stemp);

		System.out.println("\nStep into the battleground, " + p + "!");
		System.out.println("You found a new weapon: " + p.getWeapon() + "!");

		int kills = -1;
		while(p.getHealth() > 0) {
			kills++;
			Enemy e = new EnemyBasic();
			System.out.println("\n\n\nYour next opponent is " + e + "! \nHis weapon is: " + e.getWeapon() + "! \nGood luck!");
			combat(p, e);

			if(p.getHealth() > 0) {
				System.out.println("\nWould you like your dead enemy's weapon: " + e.getWeapon() + "? [y]es or [n]o?");
				if(in.nextLine().charAt(0) == 'y') {
					p.setWeapon(e.getWeapon());
					System.out.println("Congratulations on your new " + p.getWeapon() + "!");
				} else {
					System.out.println("If you say so!");
				}
			}

		}

		System.out.println("You finished with " + kills + " kills!");
	}


	private void combat(Player p, Enemy e) {
		p.setLocation(0);
		e.setLocation(500);
		System.out.println("You begin " + e.getLocation() + " cm apart.");

		int playerClock = 0;
		int enemyClock = 0;
		int totalClock = 0;

		int state = 1;
		while(p.getHealth() > 0 && e.getHealth() > 0) {
			if(state == 1) {
				state = 2;
				int statdmg = checkStatus(p);
				if(statdmg != 0) {
					System.out.println("\t\t\t\t\t\t\tCurrent time: " + totalClock);
				}
			} else if(state == 2) {
				state = 3;
				int statdmg = checkStatus(e);
				if(statdmg != 0) {
					System.out.println("\t\t\t\t\t\t\tCurrent time: " + totalClock);
				}
			} else if(state == 3) {
				state = 4;
				if(!p.getStatus().isActive()) {
					System.out.println("You are no longer " + p.getStatus() + ".");
					p.setStatus(new BlankPersonStatus());

				}
			} else if(state == 4) {
				state = 5;
				if(!e.getStatus().isActive()) {
					System.out.println(e + " is no longer " + e.getStatus() + ".");
					e.setStatus(new BlankPersonStatus());
				}
			} else if(state == 5) {
				state = 6;
				if(playerClock == 0) {
					playerClock = takeTurn(p,e, totalClock);
					System.out.println("\t\t\t\t\t\t\tYou will waste: " + playerClock + "ms, current time: " + totalClock + " ms.");
				}				
			} else if(state == 6) {
				state = 7;
				if(enemyClock == 0){
					enemyClock = takeTurn(e,p, totalClock);
					System.out.println("\t\t\t\t\t\t\tHe will waste: " + enemyClock + "ms, current time: " + totalClock + " ms.");
				}
			} else if(state == 7) {
				state = 1;
				playerClock --;
				enemyClock --;
				totalClock ++;
				p.tick();
				e.tick();
			}

		}

		if(p.getHealth() < 1) {
			System.out.println("You lost, better luck next time!");
		}
		else {
			System.out.println("Congratulations, you defeated " + e + "!");
			p.setStatus(new BlankPersonStatus());
		}

	}

	private int checkStatus(Person p) {
		String[] names = getNames(p);
		int statdmg = p.getStatus().getDamage();
		if(statdmg != 0) {
			statdmg = p.applyDamage(statdmg);
			System.out.printf("%s lost %d health due to %s!%n", names[0], statdmg, p.getStatus());
			System.out.printf("%s new health is %d.%n", names[1], p.getHealth());
		}

		return statdmg;
	}

	private int takeTurn(Person att, Person def, int currentTime) {
		int actionTime;
		Weapon wep = att.getWeapon();
		boolean killedEnemy = false;
		int damageDealt = 0;

		HashSet<Character> validActions = att.getValidActions();
		String actionLine = getPersonAction(att, def);
		char action;
		if(actionLine.length() > 0)
			action = actionLine.toLowerCase().charAt(0);
		else
			action = '?';

		String[] attNames = getNames(att);
		String[] defNames = getNames(def);
		String sentenceStarter = attNames[0].toUpperCase().charAt(0) + attNames[0].substring(1);

		if(action == 'r' && validActions.contains('r')) {
			actionTime = wep.getReadyTime();
			wep.setReadied(true);
			System.out.printf("%s readied %s %s. Movement speed lowered.%n", sentenceStarter, attNames[1], wep);
		}
		else if(action == 'l' && validActions.contains('l')) {
			actionTime = timeOther;
			wep.setReadied(false);
			System.out.printf("%s lowered %s %s. Movement speed increased.%n", sentenceStarter, attNames[1], wep);
		} 
		else if(action == 'o' && validActions.contains('o')) {
			actionTime = wep.getReloadTime();
			wep.reload();
			System.out.printf("%s reloaded %s %s.%n", sentenceStarter, attNames[1], wep);
		} 
		else if(action == 'a' && validActions.contains('a') && getDistanceBetween(att, def) <= wep.getRange()) {
			actionTime = wep.getFireTime();
			int dmg = wep.getDamage(getDistanceBetween(att, def));
			dmg = getCritDamage(att, dmg);
			if(dmg > 0) {
				dmg = def.applyDamage(dmg);
				damageDealt = dmg;
				System.out.printf("%s %s %s, dealing %d damage!%n", sentenceStarter, wep.getVerb(), defNames[0], dmg);
				System.out.printf("%s new health is %d.%n", (defNames[1].toUpperCase().charAt(0) + defNames[1].substring(1)), def.getHealth());

				PersonStatus wepStat = wep.getInflictedStatus();
				//TODO: this also blows
				if(!(wepStat instanceof BlankPersonStatus) && !def.getStatus().getClass().equals(wepStat.getClass())) {
					System.out.printf("%s's weapon inflicted %s on %s!%n", sentenceStarter, wepStat, defNames[0]);
					wepStat.initialize(currentTime);
					def.setStatus(wepStat);
				}

				int knockback = wep.getKnockback();
				if(knockback != 0) {
					int dis = move(att, def, 0, -knockback);
					System.out.printf("%s knocked %s back %d cm.%n", sentenceStarter, defNames[0], dis);
					System.out.printf("You're now %d cm apart.%n", getDistanceBetween(att, def));
				}

				if(wep.getSelfDamage() != 0 && getDistanceBetween(att, def) <= wep.getSelfDamageRange()) {
					int selfdmg = att.applyDamage(wep.getSelfDamage());
					System.out.printf("%s damaged %s for %d damage!%n", sentenceStarter, attNames[2], selfdmg);
					System.out.printf("%s new health is %d.%n", attNames[1], att.getHealth());
				}

			} else {
				System.out.printf("%s missed!%n", sentenceStarter);
			}			
		} 
		else if(action == 'd' && validActions.contains('d') && getDistanceBetween(att, def) > close) {
			actionTime = timeStep;
			int dis;

			if(wep.isReadied()) {
				dis = move(att, def, forwardStep-10, 0);
			} else {
				dis = move(att, def, forwardStep, 0);	
			}

			System.out.printf("%s stepped forward %d cm.%n", sentenceStarter, dis);
			System.out.printf("You're now %d cm apart.%n", getDistanceBetween(att, def));
		} 
		else if(action == 'e' && validActions.contains('e')) {
			actionTime = timeStep;
			int dis;

			if(wep.isReadied()) {
				dis = move(att, def, -backwardStep+10, 0);
			} else {
				dis = move(att, def, -backwardStep, 0);
			}

			System.out.printf("%s stepped backward %d cm.%n", sentenceStarter, dis);
			System.out.printf("You're now %d cm apart.%n", getDistanceBetween(att, def));
		} 
		else if(action == 'm' && validActions.contains('e')) { //HACKS, also presently this only affects players
			actionTime = timeStep;
			int distance;

			if(actionLine.split(" ").length > 1) {
				distance = Integer.parseInt(actionLine.split(" ")[1]);
			} else {
				System.out.print("Enter the number of cm you wish to move towards your enemy (negative values retreat): ");
				distance = Integer.parseInt(in.nextLine());
			}

			//TODO: better parsing
			if(wep.isReadied() && distance >= -backwardStep+10 && distance <=0) {
				int dis = move(att, def, distance, 0);
				System.out.println("You stepped backward " + dis + " cm.");
				System.out.println("You're now " + getDistanceBetween(att, def) + " cm apart.");
			} else if(wep.isReadied() && distance <= forwardStep-10 && distance >=0) {
				int dis = move(att, def, distance, 0);
				System.out.println("You stepped forward " + dis + " cm.");
				System.out.println("You're now " + getDistanceBetween(att, def) + " cm apart.");
			} else if(!wep.isReadied() && distance >= -backwardStep && distance <=0) {
				int dis = move(att, def, distance, 0);
				System.out.println("You stepped backward " + dis + " cm.");
				System.out.println("You're now " + getDistanceBetween(att, def) + " cm apart.");
			} else if(!wep.isReadied() && distance <= forwardStep && distance >=0) {
				int dis = move(att, def, distance, 0);
				System.out.println("You stepped forward " + dis + " cm.");
				System.out.println("You're now " + getDistanceBetween(att, def) + " cm apart.");
			} else {
				//TODO: also dumb
				System.out.println("You can't move that far, you dummy.");
				actionTime = 1;
			}
		} 
		else if(action == 'w' && validActions.contains('w')) {
			actionTime = timeOther;
			System.out.printf("%s %s waiting a turn.%n", sentenceStarter, attNames[3]);
		} 
		else {
			actionTime = 1;
			System.out.println(action + " is not an option!");
		}



		if(def.getHealth() < 1) killedEnemy = true;

		wep.lastActionTaken(action);
		wep.lastEnemyKilled(killedEnemy);
		wep.lastDamageDealt(damageDealt);

		def.getWeapon().lastEnemyActionTaken(action);
		return actionTime;
	}

	private String getPersonAction(Person att, Person def) {
		if(att instanceof Player && def instanceof Enemy) {
			HashSet<Character> allActions = att.getValidActions();
			if(allActions.size() == 1) {
				for(char c : allActions)
					return "" + c; ///cannot get the only element because reasons
			}
			
			System.out.print("Choose an action: ");
			if(allActions.contains('a') && getDistanceBetween(att, def) <= att.getWeapon().getRange())
				System.out.print("attack[a] ");
			if(allActions.contains('r'))
				System.out.print("ready your weapon[r] ");
			if(allActions.contains('l'))
				System.out.print("lower your weapon[l] ");
			if(allActions.contains('o'))
				System.out.print("reload your weapon[o] ");
			if(allActions.contains('i'))
				System.out.print("aim your weapon[i] ");
			if(allActions.contains('d') && canAdvance(att, def))
				System.out.print("advance[d] ");
			if(allActions.contains('e'))
				System.out.print("retreat[e] ");
			if(allActions.contains('m'))
				System.out.print("move[m] ");
			if(allActions.contains('w'))
				System.out.print("wait[w] ");

			System.out.println();
			String actionLine = in.nextLine().toLowerCase();
			return actionLine;
		} else if(att instanceof Enemy && def instanceof Player) {
			String actionLine = att.getAction(getDistanceBetween(att, def));
			return actionLine;
		}

		return "ERROR";
	}

	private String[] getNames(Person p) {
		if(p instanceof Player) {
			String[] names = {"you", "your", "yourself", "are"};
			return names;
		} else {
			String[] names = {p.toString(), "his", "himself", "is"};
			return names;
		}
	}

	//positive value for newP or newE indicates that they are moving TOWARD their target
	//negative value for newP or newE indicates that they are moving AWAY FROM their target
	//returns centimeters moved
	private int move(Person p, Person e, int pmove, int emove) {
		int ploc = p.getLocation();
		int eloc = e.getLocation();

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

	private int getDistanceBetween(Person p, Person e) {
		return(Math.abs(p.getLocation() - e.getLocation()));
	}

	private boolean canAdvance(Person a, Person b) {
		if(Math.abs(a.getLocation() - b.getLocation()) == close)
			return false;
		return true;
	}

	private int getCritDamage(Person y, int damage) {
		if(y.getWeapon().isCrit()) {
			System.out.println("A critical hit!");
			damage*= 2;
		}

		return damage;
	}
}
