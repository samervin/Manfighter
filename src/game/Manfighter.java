package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import person.Enemy;
import person.EnemyBasic;
import person.Person;
import person.Player;
import status.person.BlankPersonStatus;
import status.person.PersonStatus;
import weapon.Weapon;

public class Manfighter {

	private int TEST = 0; //1 for quicker testing, 0 for general play

	private ManfighterGenerator mfg;
	private ManfighterBoilerplate mfb;
	private ManfighterGUI gui;
	private int mindist, defaultStep, timeStep, readyTimeStep;

	private JFrame frame;
	private JTextField input;
	private JTextArea output, pL, eL, cL;
	final JDialog pD = new JDialog(frame, "Player");
	final JDialog eD = new JDialog(frame, "Enemy");
	final JDialog cD = new JDialog(frame, "Information");

	private boolean readyforinput = false;

	private Player p;
	private Enemy e;

	private int pclock = 0;
	private int eclock = 0;
	private int clock = 0;


	public static void main(String[] args) {
		new Manfighter();
	}

	public Manfighter() {
		createGUI();
		mfg = new ManfighterGenerator();
		setGlobalData();
		mfb = new ManfighterBoilerplate(mindist);
		frame.setTitle(mfg.getRandomGame());

		String name;
		if(TEST == 0) {
			name = JOptionPane.showInputDialog("Welcome to Manfighter! What's your name?");
			if(name == null || !mfg.isValidName(name)) {
				while(!mfg.isValidName(name)) {
					name = JOptionPane.showInputDialog("That name sucks. Try again.");
				}
			}	
		} else {
			name = "Sam";
		}

		p = new Player(name);
		setup();
	}
	
	public void createGUI() {
		gui = new ManfighterGUI(new ButtonListener());
		frame = gui.getFrame();
		input = gui.getInput();
		output = gui.getOutput();
	}
	
	private void setGlobalData() {
		int[] data = mfg.getGlobalData();
		mindist = data[0];
		defaultStep = data[1];
		timeStep = data[2];
		readyTimeStep = data[3];
	}

	public void setup() {
		e = new EnemyBasic();
		p.setLocation(0);
		e.setLocation(500);

		pL = new JTextArea();
		cL = new JTextArea();
		eL = new JTextArea();
		gui.createDialogs(pL, cL, eL, pD, cD, eD);
		pD.setTitle(p.toString());
		eD.setTitle(e.toString());
		updateLabels();
		
		input.requestFocusInWindow();
		startCombat();
	}

	public void startCombat() {
		checkPlayerStatus();
		checkEnemyStatus();
		printPlayerActions();
		clock = 0;
		pclock = 0;
		eclock = 0;
		readyforinput = true;
	}

	public void playerCombat(String playerAction) {
		pclock = takeTurn(p, e, playerAction);
		write("\tYou will waste: " + pclock + "ms, current time: " + clock + " ms.");
		if(eclock == 0) {
			eclock = takeTurn(e, p, e.getAction(mfb.getDistanceBetween(p, e)));
			write("\tHe will waste: " + eclock + "ms, current time: " + clock + " ms.");
		}
		if(pclock > 0 && eclock > 0) {
			pclock--;
			eclock--;
			clock++;
			p.tick();
			e.tick();
		}

		boolean gameon = true;
		while(pclock > 0) {
			otherCombat();
			if(p.getHealth() < 1 || e.getHealth() < 1) {
				pclock = 0;
				eclock = 0;
				gameon = false;
				endCombat();
			}
		}

		if(gameon) {
			printPlayerActions();
			readyforinput = true;
			updateLabels();
		}

	}

	public void otherCombat() {
		if(eclock == 0) {
			eclock = takeTurn(e, p, e.getAction(mfb.getDistanceBetween(p, e)));
			write("\tHe will waste: " + eclock + "ms, current time: " + clock + " ms.");
		}
		if(eclock > 0) {
			pclock--;
			eclock--;
			clock++;
			p.tick();
			e.tick();
		}

		checkPlayerStatus();
		checkEnemyStatus();
	}

	public void endCombat() {
		if(p.getHealth() < 1) {
			write("\nYou lost, better luck next time!");
			int n = JOptionPane.showConfirmDialog(frame, "You lost, would you like to continue?", "Game Over", JOptionPane.YES_NO_OPTION);
			if(n == JOptionPane.YES_OPTION) {
				p = new Player(p.toString());
				setup();
			}
			else {
				write("Thanks for playing!");
				readyforinput = false;
			}
		} else {
			write("\n\nEnemy defeated!\n\n");
			int n = JOptionPane.showConfirmDialog(frame, "Would you like your enemy's " + e.getWeapon() + "?", "Enemy defeated!", JOptionPane.YES_NO_OPTION);
			if(n == JOptionPane.YES_OPTION) {
				p.setWeapon(e.getWeapon());
			}
			setup();
		}
	}

	public void checkPlayerStatus() {
		if(p.getHealth() > 0 && e.getHealth() > 0) {
			int statdmg = checkStatus(p);
			if(statdmg != 0) {
				output.append("\tCurrent time: " + clock + "\n");
			}

			if(!p.getStatus().isActive()) {
				output.append("You are no longer " + p.getStatus() + ".\n");
				p.setStatus(new BlankPersonStatus());
			}
		}
	}

	public void checkEnemyStatus() {
		if(p.getHealth() > 0 && e.getHealth() > 0) {
			int statdmg = checkStatus(e);
			if(statdmg != 0) {
				output.append("\tCurrent time: " + clock + "\n");
			}

			if(!e.getStatus().isActive()) {
				output.append("He is no longer " + e.getStatus() + " as of " + clock + ".\n");
				e.setStatus(new BlankPersonStatus());
			}
		}
	}

	private int checkStatus(Person p) {
		String[] names = mfb.getNames(p);
		int statdmg = p.getStatus().getDamage();
		if(statdmg != 0) {
			statdmg = p.applyDamage(statdmg, "torso");
			write(String.format("%s lost %d health due to %s!", names[0].toUpperCase().charAt(0) + names[0].substring(1), statdmg, p.getStatus()));
			write(String.format("%s new health is %d.", names[1].toUpperCase().charAt(0) + names[1].substring(1), p.getHealth()));
		}

		return statdmg;
	}
	
	public void write(String s) {
		output.append(s + "\n");
	}

	private void updateLabels() {
		pL.setText(p.getFullInfo());
		eL.setText(e.getFullInfo());
		cL.setText("Distance apart: " + mfb.getDistanceBetween(p, e)
				+ "\nCurrent time: " + clock + " ms"
				+ "\nTime until next enemy action: " + eclock + " ms");
		output.setCaretPosition(output.getDocument().getLength());
	}

	private int getCritDamage(Person y, int damage) {
		if(y.getWeapon().isCrit()) {
			write("A critical hit!");
			damage*= 2;
		}

		return damage;
	}

	public void printPlayerActions() {
		HashSet<Character> allActions = p.getValidActions();

		output.append("Choose an action: ");
		if(allActions.contains('a') && mfb.getDistanceBetween(p, e) <= p.getWeapon().getRange())
			output.append("attack[a] ");
		if(allActions.contains('o'))
			output.append("reload your weapon[o] ");
		if(allActions.contains('p'))
			output.append("single-reload your weapon[p] ");
		if(allActions.contains('r'))
			output.append("ready your weapon[r] ");
		if(allActions.contains('l'))
			output.append("lower your weapon[l] ");
		if(allActions.contains('i'))
			output.append("aim your weapon[i] ");
		if(allActions.contains('d') && mfb.canAdvance(p, e))
			output.append("advance[d] ");
		if(allActions.contains('e'))
			output.append("retreat[e] ");
		if(allActions.contains('m'))
			output.append("move[m] ");
		if(allActions.contains('w'))
			output.append("wait[w] ");
		output.append("\n\n");
	}

	private int takeTurn(Person att, Person def, String actionLine) {
		int actionTime;
		Weapon wep = att.getWeapon();
		boolean killedEnemy = false;
		int damageDealt = 0;

		HashSet<Character> validActions = att.getValidActions();
		char action;
		if(actionLine.length() > 0)
			action = actionLine.toLowerCase().charAt(0);
		else
			action = '?';

		String[] attNames = mfb.getNames(att);
		String[] defNames = mfb.getNames(def);
		String sentenceStarter = attNames[0].toUpperCase().charAt(0) + attNames[0].substring(1);

		if(action == 'r' && validActions.contains('r')) {
			actionTime = wep.getReadyTime();
			wep.setReadied(true);
			output.append(String.format("%s readied %s %s. Movement speed lowered.%n", sentenceStarter, attNames[1], wep));
		}
		else if(action == 'l' && validActions.contains('l')) {
			actionTime = wep.getReadyTime();
			wep.setReadied(false);
			output.append(String.format("%s lowered %s %s. Movement speed increased.%n", sentenceStarter, attNames[1], wep));
		} 
		else if(action == 'o' && validActions.contains('o')) {
			actionTime = wep.getReloadTime();
			wep.reload();
			output.append(String.format("%s reloaded %s %s.%n", sentenceStarter, attNames[1], wep));
		}
		else if(action == 'p' && validActions.contains('p')) {
			actionTime = wep.getReloadOneTime();
			wep.reloadOne();
			output.append(String.format("%s single-reloaded %s %s.%n", sentenceStarter, attNames[1], wep));
		}
		else if(action == 'i' && validActions.contains('i')) {
			actionTime = 0;
			String location;

			if(actionLine.split(" ").length > 1) {
				location = actionLine.split(" ")[1];
			} else {
				location = JOptionPane.showInputDialog("Enter the body part [head, torso, arms, legs] you wish to aim for:");
				while(location == null)
					location = JOptionPane.showInputDialog("Enter the body part [head, torso, arms, legs] you wish to aim for:");
			}

			write("Now aiming for: " + location);
			wep.aim(location);
		}
		else if(action == 'a' && validActions.contains('a') && mfb.getDistanceBetween(att, def) <= wep.getRange()) {
			actionTime = wep.getFireTime();
			int dmg = att.getDamage(mfb.getDistanceBetween(att, def));
			dmg = getCritDamage(att, dmg);
			if(dmg > 0) {
				dmg = def.applyDamage(dmg, wep.getDamageLocation());
				damageDealt = dmg;
				write(String.format("%s %s %s in the %s, dealing %d %s damage!",
						sentenceStarter, wep.getVerb(), defNames[0], wep.getDamageLocation(), dmg, wep.getDamageType()));
				write(String.format("%s new health is %d.",
						(defNames[1].toUpperCase().charAt(0) + defNames[1].substring(1)), def.getHealth()));

				PersonStatus wepStat = wep.getInflictedStatus();
				//TODO: this also blows
				if(!(wepStat instanceof BlankPersonStatus) && !def.getStatus().getClass().equals(wepStat.getClass())) {
					output.append(String.format("%s weapon inflicted %s on %s!%n", 
							(attNames[1].toUpperCase().charAt(0) + attNames[1].substring(1)), wepStat, defNames[0]));
					wepStat.initialize(clock);
					def.setStatus(wepStat);
				}

				int knockback = wep.getKnockback();
				if(knockback != 0) {
					int dis = mfb.move(att, def, 0, -knockback);
					output.append(String.format("%s knocked %s back %d cm.%n", sentenceStarter, defNames[0], dis));
					output.append(String.format("You're now %d cm apart.%n", mfb.getDistanceBetween(att, def)));
				}

				if(wep.getSelfDamage() != 0 && mfb.getDistanceBetween(att, def) <= wep.getSelfDamageRange()) {
					int selfdmg = att.applyDamage(wep.getSelfDamage(), "torso");
					output.append(String.format("%s damaged %s for %d damage!%n", sentenceStarter, attNames[2], selfdmg));
					output.append(String.format("%s new health is %d.%n", attNames[1], att.getHealth()));
				}

			} else {
				output.append(String.format("%s missed!%n", sentenceStarter));
			}			
		} 
		else if(action == 'd' && validActions.contains('d') && mfb.getDistanceBetween(att, def) > mindist) {
			int dis = mfb.move(att, def, defaultStep, 0);
			if(wep.isReadied()) {
				actionTime = readyTimeStep * dis;
			} else {
				actionTime = timeStep * dis;	
			}
			output.append(String.format("%s stepped forward %d cm.%n", sentenceStarter, dis));
			output.append(String.format("You're now %d cm apart.%n", mfb.getDistanceBetween(att, def)));
		} 
		else if(action == 'e' && validActions.contains('e')) {
			int dis = mfb.move(att, def, -defaultStep, 0);
			if(wep.isReadied()) {
				actionTime = readyTimeStep * dis;
			} else {
				actionTime = timeStep * dis;	
			}
			output.append(String.format("%s stepped backward %d cm.%n", sentenceStarter, dis));
			output.append(String.format("You're now %d cm apart.%n", mfb.getDistanceBetween(att, def)));
		} 
		else if(action == 'm' && validActions.contains('e')) { //HACKS, also presently this only affects players
			int distance, dis;

			if(actionLine.split(" ").length > 1) {
				distance = Integer.parseInt(actionLine.split(" ")[1]);
			} else {
				distance = Integer.parseInt(JOptionPane.showInputDialog
						("Enter the number of cm you wish to move towards your enemy (negative values retreat):"));
				while(distance == 0)
					distance = Integer.parseInt(JOptionPane.showInputDialog
							("Enter the number of cm you wish to move towards your enemy (negative values retreat):"));

			}

			//TODO: better parsing
			if(wep.isReadied() && distance <0) {
				dis = mfb.move(att, def, distance, 0);
				actionTime = readyTimeStep * dis;
				write("You stepped backward " + dis + " cm.");
				write("You're now " + mfb.getDistanceBetween(att, def) + " cm apart.");
			} else if(wep.isReadied() && distance >=0) {
				dis = mfb.move(att, def, distance, 0);
				actionTime = readyTimeStep * dis;
				write("You stepped forward " + dis + " cm.");
				write("You're now " + mfb.getDistanceBetween(att, def) + " cm apart.");
			} else if(!wep.isReadied() && distance <0) {
				dis = mfb.move(att, def, distance, 0);
				actionTime = timeStep * dis;
				write("You stepped backward " + dis + " cm.");
				write("You're now " + mfb.getDistanceBetween(att, def) + " cm apart.");
			} else if(!wep.isReadied() && distance >=0) {
				dis = mfb.move(att, def, distance, 0);
				actionTime = timeStep * dis;
				write("You stepped forward " + dis + " cm.");
				write("You're now " + mfb.getDistanceBetween(att, def) + " cm apart.");
			} else {
				write("Something went wrong when you tried to move " + distance + " cm.");
				actionTime = 0;
			}
		} 
		else if(action == 'w' && validActions.contains('w')) {
			int time = 0;
			
			if(actionLine.split(" ").length > 1) {
				time = Integer.parseInt(actionLine.split(" ")[1]);
			}
			while(time < 1) {
				time = mfb.getWaitTime(att);
			}
			
			actionTime = time;
			output.append(String.format("%s %s waiting a turn.%n", sentenceStarter, attNames[3]));
		} 
		else {
			actionTime = 0;
			output.append(String.format(action + " is not an option!"));
		}



		if(def.getHealth() < 1) killedEnemy = true;

		wep.lastActionTaken(action);
		wep.lastEnemyKilled(killedEnemy);
		wep.lastDamageDealt(damageDealt);

		def.getWeapon().lastEnemyActionTaken(action);
		return actionTime;
	}




	
	
	

	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String inputLine = input.getText().trim();
			if(inputLine.length() > 0 && readyforinput) {
				readyforinput = false;
				playerCombat(inputLine);
			}
			input.setText("");
			input.requestFocus();
		}
	}
}
