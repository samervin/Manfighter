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
			if(stringDivisibleBy(stemp, 3)) {
				while(stringDivisibleBy(stemp, 3)) {
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
					System.out.println("If you say so...");
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

		while(p.getHealth() > 0 && e.getHealth() > 0) {
			
			if(p.getHealth() > 0) {
				int statdmg = p.getStatus().getDamage();
				if(statdmg != 0) {
					statdmg = p.applyDamage(statdmg);
					System.out.println("You lost " + statdmg + " health due to " + p.getStatus() + "!");
					System.out.println("Your new health is " + p.getHealth() + ".");
					System.out.println("\t\t\t\t\t\t\tCurrent time: " + totalClock);
				}
			}
			if(e.getHealth() > 0) {
				int statdmg = e.getStatus().getDamage();
				if(statdmg != 0) {
					statdmg = e.applyDamage(statdmg);
					System.out.println(e + " lost " + statdmg + " health due to " + e.getStatus() + "!");
					System.out.println(e + "'s new health is " + e.getHealth() + ".");
					System.out.println("\t\t\t\t\t\t\tCurrent time: " + totalClock);
				}
			}
			if(p.getHealth() > 0) {
				if(!p.getStatus().isActive()) {
					System.out.println("You are no longer " + p.getStatus() + ".");
					p.setStatus(new BlankPersonStatus());
					
				}
			}
			if(e.getHealth() > 0) {
				if(!e.getStatus().isActive()) {
					System.out.println(e + " is no longer " + e.getStatus() + ".");
					e.setStatus(new BlankPersonStatus());
					
				}
			}
			if(p.getHealth() > 0 && playerClock == 0) {
				playerClock = playerTurn(p,e, totalClock);
				System.out.println("\t\t\t\t\t\t\tYou will waste: " + playerClock + "ms, current time: " + totalClock + " ms.");
			}
			if(e.getHealth() > 0 && enemyClock == 0) {
				enemyClock = enemyTurn(p,e, totalClock);
				System.out.println("\t\t\t\t\t\t\tHe will waste: " + enemyClock + "ms, current time: " + totalClock + " ms.");
			}
			playerClock --;
			enemyClock --;
			totalClock ++;
			p.tick();
			e.tick();
		}

		if(p.getHealth() < 1) {
			System.out.println("You lost, better luck next time!");
		}
		else {
			System.out.println("Congratulations, you defeated " + e + "!");
			p.setStatus(new BlankPersonStatus());
		}
			
	}

	private int playerTurn(Player p, Enemy e, int currentTime) {
		int actionTime;
		Weapon wep = p.getWeapon();
		boolean killedEnemy = false;
		int damageDealt = 0;
		
		HashSet<Character> allactions = p.getActions();
		System.out.print("Will you: ");
		if(allactions.contains('a') && getDistanceBetween(p, e) <= wep.getRange())
			System.out.print("attack[a], ");
		if(allactions.contains('r'))
			System.out.print("ready your weapon[r], ");
		if(allactions.contains('l'))
			System.out.print("lower your weapon[l], ");
		if(allactions.contains('o'))
			System.out.print("reload your weapon[o], ");
		if(allactions.contains('i'))
			System.out.print("aim your weapon[i], ");
		if(allactions.contains('d') && canAdvance(p, e))
			System.out.print("advance[d], ");
		if(allactions.contains('e'))
			System.out.print("retreat[e], ");
		if(allactions.contains('e')) //temporary hack
			System.out.print("move[m], ");
		System.out.print("or wait[w]?\n");


		String actionLine = in.nextLine().toLowerCase();
		char action = '?';
		if(actionLine.length() > 0)
			action = actionLine.charAt(0);
		
		if(action == 'r' && allactions.contains('r')) {
			actionTime = wep.getReadyTime();
			wep.setReadied(true);
			System.out.println("You readied your " + wep + ". Movement speed lowered.");
		} 
		else if(action == 'l' && allactions.contains('l')) {
			actionTime = timeOther;
			wep.setReadied(false);
			System.out.println("You lowered your " + wep + ". Movement speed increased.");
		} 
		else if(action == 'o' && allactions.contains('o')) {
			actionTime = timeOther;
			wep.reload();
			System.out.println("You reloaded your " + wep + ".");
		} 
		else if(action == 'a' && allactions.contains('a')) {
			if(wep.getRange() >= Math.abs(p.getLocation() - e.getLocation())) {
				actionTime = wep.getFireTime();
				int dmg = wep.getDamage();
				dmg = getCritDamage(p, dmg);
				if(dmg > 0) {
					dmg = e.applyDamage(dmg);
					damageDealt = dmg;
					System.out.println("You " + wep.getVerb() + " " + e + ", dealing " + dmg + " damage!");
					System.out.println(e + "'s new health is " + e.getHealth() + ".");
					
					PersonStatus wepStat = wep.getInflictedStatus();
					//TODO: this also blows
					if(!(wepStat instanceof BlankPersonStatus) && !e.getStatus().getClass().equals(wepStat.getClass())) {
						System.out.println("Your weapon inflicted " + wepStat + " on your enemy!");
						wepStat.initialize(currentTime);
						e.setStatus(wepStat);
					}
					
					if(wep.getKnockback() != 0) {
						int dis = move(p, e, 0, -wep.getKnockback());
						System.out.println("You knocked " + e + " back " + dis + " cm.");
						System.out.println("You're now " + getDistanceBetween(p, e) + " cm apart.");
					}
					
					if(wep.getSelfDamage() != 0 && getDistanceBetween(p, e) <= wep.getSelfDamageRange()) {
						int selfdmg = p.applyDamage(wep.getSelfDamage());
						System.out.println("You damaged yourself for " + selfdmg + " damage!");
						System.out.println("Your new health is " + p.getHealth() + ".");
					}
					
				} else {
					System.out.println("You missed!");
				}
			}
			else {
				actionTime = timeOther;
				System.out.println("You tried to attack, but you're not in range!");
			}
		} 
		else if(action == 'd' && allactions.contains('d') && getDistanceBetween(p, e) > close) {
			actionTime = timeStep;
			if(wep.isReadied()) {
				int dis = move(p, e, forwardStep-10, 0);
				System.out.println("You stepped forward " + dis + " cm.");
				System.out.println("You're now " + getDistanceBetween(p, e) + " cm apart.");
			} else {
				int dis = move(p, e, forwardStep, 0);
				System.out.println("You stepped forward " + dis + " cm.");
				System.out.println("You're now " + getDistanceBetween(p, e) + " cm apart.");
			}

		} 
		else if(action == 'e' && allactions.contains('e')) {
			actionTime = timeStep;
			if(wep.isReadied()) {
				int dis = move(p, e, -backwardStep+10, 0);
				System.out.println("You stepped backward " + dis + " cm.");
				System.out.println("You're now " + getDistanceBetween(p, e) + " cm apart.");
			} else {
				int dis = move(p, e, -backwardStep, 0);
				System.out.println("You stepped backward " + dis + " cm.");
				System.out.println("You're now " + getDistanceBetween(p, e) + " cm apart.");
			}

		} 
		else if(action == 'm' && allactions.contains('e')) { //HACKS
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
				int dis = move(p, e, distance, 0);
				System.out.println("You stepped backward " + dis + " cm.");
				System.out.println("You're now " + getDistanceBetween(p, e) + " cm apart.");
			} else if(wep.isReadied() && distance <= forwardStep-10 && distance >=0) {
				int dis = move(p, e, distance, 0);
				System.out.println("You stepped forward " + dis + " cm.");
				System.out.println("You're now " + getDistanceBetween(p, e) + " cm apart.");
			} else if(!wep.isReadied() && distance >= -backwardStep && distance <=0) {
				int dis = move(p, e, distance, 0);
				System.out.println("You stepped backward " + dis + " cm.");
				System.out.println("You're now " + getDistanceBetween(p, e) + " cm apart.");
			} else if(!wep.isReadied() && distance <= forwardStep && distance >=0) {
				int dis = move(p, e, distance, 0);
				System.out.println("You stepped forward " + dis + " cm.");
				System.out.println("You're now " + getDistanceBetween(p, e) + " cm apart.");
			} else {
				//TODO: also dumb
				System.out.println("You can't move that far, you dummy.");
				actionTime = 1;
			}
		} 
		else if(action == 'w') {
			actionTime = timeOther;
			System.out.println("You're waiting a turn.");
		} 
		else {
			actionTime = 1;
			System.out.println(action + " is not an option, sorry.");
		}

		if(e.getHealth() < 1) killedEnemy = true;
		
		wep.lastActionTaken(action);
		wep.lastEnemyKilled(killedEnemy);
		wep.lastDamageDealt(damageDealt);
		
		e.getWeapon().lastEnemyActionTaken(action);
		System.out.println();
		return actionTime;
	}

	
	
	
	private int enemyTurn(Player p, Enemy e, int currentTime) {
		int reactionTime;
		char reaction = e.getAction(getDistanceBetween(p, e));
		Weapon wep = e.getWeapon();
		int damageDealt = 0;

		switch(reaction) {
		case 'r':
			reactionTime = wep.getReadyTime();
			wep.setReadied(true);
			System.out.println(e + " readied his " + wep + ". His movement speed is lowered.");
			break;
		case 'l':
			reactionTime = timeOther;
			wep.setReadied(false);
			System.out.println(e + " lowered his " + wep + ". His movement speed is increased.");
			break;
		case 'o':
			reactionTime = timeOther;
			wep.reload();
			System.out.println(e + " reloaded his " + wep + ".");
			break;
		case 'a':
			if(wep.getRange() >= Math.abs(p.getLocation() - e.getLocation())) {
				reactionTime = wep.getFireTime();
				int dmg = wep.getDamage();
				dmg = getCritDamage(e, dmg);
				if(dmg > 0) {
					dmg = p.applyDamage(dmg);
					System.out.println(e + " " + wep.getVerb() + " you, dealing " + dmg + " damage!");
					System.out.println(p + "'s new health is " + p.getHealth() + ".");
					
					damageDealt = dmg;
					
					PersonStatus wepStat = wep.getInflictedStatus();
					if(!(wepStat instanceof BlankPersonStatus) && !p.getStatus().getClass().equals(wepStat.getClass())) {
						System.out.println(e + "'s weapon inflicted " + wepStat + " on you!");
						wepStat.initialize(currentTime);
						p.setStatus(wepStat);
					}
					
					if(wep.getKnockback() != 0) {
						int dis = move(p, e, -wep.getKnockback(), 0);
						System.out.println(e + " knocked you back " + dis + " cm.");
						System.out.println("You're now " + getDistanceBetween(p, e) + " cm apart.");
					}
					
					if(wep.getSelfDamage() != 0 && getDistanceBetween(p, e) <= wep.getSelfDamageRange()) {
						int selfdmg = e.applyDamage(wep.getSelfDamage());
						System.out.println(e + " damaged himself for " + selfdmg + " damage!");
						System.out.println(e + "'s new health is " + e.getHealth() + ".");
					}
					
				} else {
					System.out.println(e + " missed!");
				}
			}
			else {
				reactionTime = timeOther;
				System.out.println(e + " tried to attack, but is not in range!");
			}
			
			break;
		case 'd':
			reactionTime = timeStep;
			if(wep.isReadied()) {
				int dis = move(p, e, forwardStep-10, 0);
				System.out.println(e + " stepped forward " + dis + " cm.");			
				System.out.println("You're now " + getDistanceBetween(p, e) + " cm apart.");
			} else {
				int dis = move(p, e, forwardStep, 0);
				System.out.println(e + " stepped forward " + dis + " cm.");			
				System.out.println("You're now " + getDistanceBetween(p, e) + " cm apart.");
			}

			break;
		case 'e':
			reactionTime = timeStep;
			if(wep.isReadied()) {
				int dis = move(p, e, -backwardStep+10, 0);
				System.out.println(e + " stepped backward " + dis + " cm.");
				System.out.println("You're now " + getDistanceBetween(p, e) + " cm apart.");
			} else {
				int dis = move(p, e, -backwardStep, 0);
				System.out.println(e + " stepped backward " + dis + " cm.");
				System.out.println("You're now " + getDistanceBetween(p, e) + " cm apart.");
			}

			break;
		case 'w':
			reactionTime = timeOther;
			System.out.println(e + " is waiting a turn.");
			break;
		default:
			reactionTime = 1;
			System.out.println("The computer accidentally did this: " + reaction);
		}

		wep.lastActionTaken(reaction);
		wep.lastDamageDealt(damageDealt);
		p.getWeapon().lastEnemyActionTaken(reaction);
		return reactionTime;
	}

	//positive value for newP or newE indicates that they are moving TOWARD their target
	//negative value for newP or newE indicates that they are moving AWAY FROM their target
	//returns centimeters moved
	private int move(Player p, Enemy e, int pmove, int emove) {
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

	private boolean canAdvance(Player p, Enemy e) {
		if(Math.abs(p.getLocation() - e.getLocation()) == close)
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

	private boolean stringDivisibleBy(String str, int num) {
		char[] a = str.toCharArray();
		int in = 0;
		for(int k = 0; k < a.length; k++) {
			in += a[k]; //47
		}

		return (in % num == 0);
	}
}
