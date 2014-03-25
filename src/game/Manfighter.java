package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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

	private ManfighterGenerator mfg = new ManfighterGenerator();
	private int mindist, forwardStep, forwardReadyStep, backwardStep, backwardReadyStep, timeStep;

	private JFrame frame;
	private JTextField input;
	private JTextArea output, pL, eL;

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
		setGlobalData();

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

	public void setGlobalData() {
		try {
			Scanner in = new Scanner(new File("data/global/global_stats.txt"));
			while(in.hasNextLine()) {
				String[] line = in.nextLine().split(" ");
				if(line[0].equals("minimum-distance-apart"))
					mindist = Integer.parseInt(line[1]);
				else if(line[0].equals("forward-step"))
					forwardStep = Integer.parseInt(line[1]);
				else if(line[0].equals("forward-short-step"))
					forwardReadyStep = Integer.parseInt(line[1]);
				else if(line[0].equals("backward-step"))
					backwardStep = -Integer.parseInt(line[1]);
				else if(line[0].equals("backward-short-step"))
					backwardReadyStep = -Integer.parseInt(line[1]);
				else if(line[0].equals("time-per-cm-stepped"))
					timeStep = Integer.parseInt(line[1]);
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void setup() {
		e = new EnemyBasic();
		p.setLocation(0);
		e.setLocation(500);

		final JDialog pD = new JDialog(frame, "Player");
		pL = new JTextArea(p.getFullInfo());
		JButton closeButton = new JButton("Close");
		closeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pD.setVisible(false);
				pD.dispose();
			}
		});
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.add(pL, BorderLayout.NORTH);
		contentPane.add(closeButton, BorderLayout.SOUTH);
		contentPane.setOpaque(true);
		pD.setContentPane(contentPane);
		pD.setSize(new Dimension(300, 250));
		pD.setLocationRelativeTo(frame);
		pD.setVisible(true);

		final JDialog eD = new JDialog(frame, "Enemy");
		eL = new JTextArea(e.getFullInfo());
		JButton closeButton2 = new JButton("Close");
		closeButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eD.setVisible(false);
				eD.dispose();
			}
		});
		JPanel contentPane2 = new JPanel(new BorderLayout());
		contentPane2.add(eL, BorderLayout.NORTH);
		contentPane2.add(closeButton2, BorderLayout.SOUTH);
		contentPane2.setOpaque(true);
		eD.setContentPane(contentPane2);
		eD.setSize(new Dimension(300, 250));
		eD.setLocationRelativeTo(frame);
		eD.setVisible(true);

		startCombat();
	}

	public void startCombat() {
		checkPlayerStatus();
		checkEnemyStatus();
		printPlayerActions();
		readyforinput = true;
	}

	public void playerCombat(String playerAction) {
		pclock = takeTurn(p, e, playerAction);
		write("\tYou will waste: " + pclock + "ms, current time: " + clock + " ms.");
		if(eclock == 0) {
			eclock = takeTurn(e, p, e.getAction(getDistanceBetween(p, e)));
			write("\tHe will waste: " + eclock + "ms, current time: " + clock + " ms.");
		}
		pclock--;
		eclock--;
		clock++;
		p.tick();
		e.tick();

		while(pclock > 0) {
			otherCombat();
		}

		printPlayerActions();
		readyforinput = true;
		updateLabels();
	}

	public void otherCombat() {
		if(eclock == 0) {
			eclock = takeTurn(e, p, e.getAction(getDistanceBetween(p, e)));
			write("\tHe will waste: " + eclock + "ms, current time: " + clock + " ms.");
		}
		pclock--;
		eclock--;
		clock++;
		p.tick();
		e.tick();

		checkPlayerStatus();
		checkEnemyStatus();
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
				output.append("He is no longer " + e.getStatus() + ".\n");
				e.setStatus(new BlankPersonStatus());
				write("status-done: " +clock);
			}
		}
	}

	private int checkStatus(Person p) {
		String[] names = getNames(p);
		int statdmg = p.getStatus().getDamage();
		if(statdmg != 0) {
			statdmg = p.applyDamage(statdmg, "torso");
			write(String.format("%s lost %d health due to %s!%n", names[0], statdmg, p.getStatus()));
			write(String.format("%s new health is %d.%n", names[1], p.getHealth()));
		}

		return statdmg;
	}

	private void updateLabels() {
		pL.setText(p.getFullInfo());
		eL.setText(e.getFullInfo());
		output.setCaretPosition(output.getDocument().getLength());
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

	private int move(Person p, Person e, int pmove, int emove) {
		int ploc = p.getLocation();
		int eloc = e.getLocation();

		if(ploc < eloc) { //player is left of enemy
			if(pmove > 0) { //player is attempting to advance
				if(eloc - ploc > pmove + mindist) { //if player has space to move (cannot move through people)
					p.setLocation(ploc + pmove);
					return pmove;
				}
				else { //get as close as possible
					p.setLocation(eloc - mindist);
					return eloc - ploc - mindist;
				}
			}
			else if(pmove < 0) { //player is attempting to retreat
				p.setLocation(ploc + pmove);
				return -pmove;
			}
			else if(emove > 0) { //enemy is attempting to advance
				if(eloc - ploc > emove + mindist) { //if enemy has space to move
					e.setLocation(eloc - emove);
					return emove;
				}
				else { //get as close as possible
					e.setLocation(ploc + mindist);
					return eloc - ploc - mindist;
				}
			}
			else if(emove < 0) { //enemy is attempting to retreat
				e.setLocation(eloc - emove);
				return -emove;
			}
		}
		else { //player is right of enemy (signs flip)
			if(pmove > 0) { //player is attempting to advance
				if(ploc - eloc > pmove + mindist) { //if player has space to move (cannot move through people)
					p.setLocation(ploc - pmove);
					return pmove;
				}
				else { //get as close as possible
					p.setLocation(eloc + mindist);
					return ploc - eloc - mindist;
				}
			}
			else if(pmove < 0) { //player is attempting to retreat
				p.setLocation(ploc - pmove);
				return -pmove;
			}
			else if(emove > 0) { //enemy is attempting to advance
				if(ploc - eloc > emove + mindist) { //if enemy has space to move
					e.setLocation(eloc + emove);
					return emove;
				}
				else { //get as close as possible
					e.setLocation(ploc - mindist);
					return ploc - eloc - mindist;
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
		if(Math.abs(a.getLocation() - b.getLocation()) == mindist)
			return false;
		return true;
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
		if(allActions.contains('a') && getDistanceBetween(p, e) <= p.getWeapon().getRange())
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
		if(allActions.contains('d') && canAdvance(p, e))
			output.append("advance[d] ");
		if(allActions.contains('e'))
			output.append("retreat[e] ");
		if(allActions.contains('m'))
			output.append("move[m] ");
		if(allActions.contains('w'))
			output.append("wait[w] ");
		output.append("\n");
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

		String[] attNames = getNames(att);
		String[] defNames = getNames(def);
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
		else if(action == 'a' && validActions.contains('a') && getDistanceBetween(att, def) <= wep.getRange()) {
			actionTime = wep.getFireTime();
			int dmg = att.getDamage(getDistanceBetween(att, def));
			dmg = getCritDamage(att, dmg);
			if(dmg > 0) {
				dmg = def.applyDamage(dmg, wep.getDamageLocation());
				damageDealt = dmg;
				output.append(String.format("%s %s %s, dealing %d %s damage!%n", sentenceStarter, wep.getVerb(), defNames[0], dmg, wep.getDamageType()));
				output.append(String.format("%s new health is %d.%n", (defNames[1].toUpperCase().charAt(0) + defNames[1].substring(1)), def.getHealth()));

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
					int dis = move(att, def, 0, -knockback);
					output.append(String.format("%s knocked %s back %d cm.%n", sentenceStarter, defNames[0], dis));
					output.append(String.format("You're now %d cm apart.%n", getDistanceBetween(att, def)));
				}

				if(wep.getSelfDamage() != 0 && getDistanceBetween(att, def) <= wep.getSelfDamageRange()) {
					int selfdmg = att.applyDamage(wep.getSelfDamage(), "torso");
					output.append(String.format("%s damaged %s for %d damage!%n", sentenceStarter, attNames[2], selfdmg));
					output.append(String.format("%s new health is %d.%n", attNames[1], att.getHealth()));
				}

			} else {
				output.append(String.format("%s missed!%n", sentenceStarter));
			}			
		} 
		else if(action == 'd' && validActions.contains('d') && getDistanceBetween(att, def) > mindist) {
			int dis;
			if(wep.isReadied()) {
				dis = move(att, def, forwardReadyStep, 0);
			} else {
				dis = move(att, def, forwardStep, 0);	
			}

			actionTime = timeStep * dis;
			output.append(String.format("%s stepped forward %d cm.%n", sentenceStarter, dis));
			output.append(String.format("You're now %d cm apart.%n", getDistanceBetween(att, def)));
		} 
		else if(action == 'e' && validActions.contains('e')) {
			int dis;
			if(wep.isReadied()) {
				dis = move(att, def, backwardReadyStep, 0);
			} else {
				dis = move(att, def, backwardStep, 0);
			}

			actionTime = timeStep * dis;
			output.append(String.format("%s stepped backward %d cm.%n", sentenceStarter, dis));
			output.append(String.format("You're now %d cm apart.%n", getDistanceBetween(att, def)));
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
			if(wep.isReadied() && distance >= backwardReadyStep && distance <=0) {
				dis = move(att, def, distance, 0);
				write("You stepped backward " + dis + " cm.");
				write("You're now " + getDistanceBetween(att, def) + " cm apart.");
			} else if(wep.isReadied() && distance <= forwardReadyStep && distance >=0) {
				dis = move(att, def, distance, 0);
				write("You stepped forward " + dis + " cm.");
				write("You're now " + getDistanceBetween(att, def) + " cm apart.");
			} else if(!wep.isReadied() && distance >= backwardStep && distance <=0) {
				dis = move(att, def, distance, 0);
				write("You stepped backward " + dis + " cm.");
				write("You're now " + getDistanceBetween(att, def) + " cm apart.");
			} else if(!wep.isReadied() && distance <= forwardStep && distance >=0) {
				dis = move(att, def, distance, 0);
				write("You stepped forward " + dis + " cm.");
				write("You're now " + getDistanceBetween(att, def) + " cm apart.");
			} else {
				//TODO: also dumb
				write("You can't move that far, you dummy.");
				dis = 0;
			}
			actionTime = timeStep * dis;
		} 
		else if(action == 'w' && validActions.contains('w')) {
			actionTime = Integer.parseInt(JOptionPane.showInputDialog
					("Enter the number of ms you wish to wait:"));
			while(actionTime <= 0)
				actionTime = Integer.parseInt(JOptionPane.showInputDialog
						("Enter the number of ms you wish to wait:"));
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






	public void write(String s) {
		output.append(s + "\n");
	}

	public void createGUI() {
		ManfighterGUI gui = new ManfighterGUI(new ButtonListener());
		frame = gui.getFrame();
		input = gui.getInput();
		output = gui.getOutput();
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
