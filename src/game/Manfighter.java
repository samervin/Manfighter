package game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.DefaultCaret;

import person.Enemy;
import person.EnemyBasic;
import person.Person;
import person.Player;
import status.person.BlankPersonStatus;
import status.person.PersonStatus;
import weapon.Weapon;

public class Manfighter {

	private int TEST = 1; //1 for quicker testing, 0 for general play

	private Scanner in = new Scanner(System.in);
	private ManfighterGenerator mfg = new ManfighterGenerator();
	private final int close = 60; //minimum distance apart, in cm
	private final int forwardStep = 85; //how far you step forward
	private final int forwardReadyStep = 70;
	private final int backwardStep = -70; //how far you step backward
	private final int backwardReadyStep = -55;
	private final int timeStep = 8; //ms per cm moved
	private final int timeOther = 500; //place holder for "other" actions

	private JFrame frame;
	private JTextField input;
	private JTextArea output;
	private JTextArea pL;
	private JTextArea eL;

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
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				createGUI();
			}
		});

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

		checkPlayerStatus();
		checkEnemyStatus();
		if(pclock <= 0) {
			printPlayerActions();
			readyforinput = true;
			updateLabels();
			return;
		} else {
			otherCombat();
		}
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
		if(pclock <= 0) {
			printPlayerActions();
			readyforinput = true;
			updateLabels();
			return;
		} else {
			otherCombat();
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
			actionTime = timeOther;
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
				System.out.print("Enter the body part [head, torso, arms, legs] you wish to aim for: ");
				location = in.nextLine();
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
		else if(action == 'd' && validActions.contains('d') && getDistanceBetween(att, def) > close) {
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
			actionTime = timeStep;
			int distance;

			if(actionLine.split(" ").length > 1) {
				distance = Integer.parseInt(actionLine.split(" ")[1]);
			} else {
				System.out.print("Enter the number of cm you wish to move towards your enemy (negative values retreat): ");
				distance = Integer.parseInt(in.nextLine());
			}

			//TODO: better parsing
			if(wep.isReadied() && distance >= backwardReadyStep && distance <=0) {
				int dis = move(att, def, distance, 0);
				System.out.println("You stepped backward " + dis + " cm.");
				System.out.println("You're now " + getDistanceBetween(att, def) + " cm apart.");
			} else if(wep.isReadied() && distance <= forwardReadyStep && distance >=0) {
				int dis = move(att, def, distance, 0);
				System.out.println("You stepped forward " + dis + " cm.");
				System.out.println("You're now " + getDistanceBetween(att, def) + " cm apart.");
			} else if(!wep.isReadied() && distance >= backwardStep && distance <=0) {
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
				actionTime = 0;
			}
		} 
		else if(action == 'w' && validActions.contains('w')) {
			actionTime = timeOther;
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
		frame = new JFrame("Manfighter");
		frame.setDefaultCloseOperation(3);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setOpaque(true);
		ButtonListener butlis = new ButtonListener();
		output = new JTextArea(15, 80);
		output.setWrapStyleWord(true);
		output.setEditable(false);
		JScrollPane scrollMain = new JScrollPane(output);
		scrollMain.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMain.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		DefaultCaret caretMain = (DefaultCaret) output.getCaret();
		caretMain.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		panel.add(scrollMain, BorderLayout.CENTER);

		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new FlowLayout());
		input = new JTextField(10);
		JButton enter = new JButton("Enter");
		enter.setActionCommand("Enter");
		enter.addActionListener(butlis);
		input.setActionCommand("Enter");
		input.addActionListener(butlis);
		inputpanel.add(input);
		inputpanel.add(enter);
		panel.add(inputpanel, BorderLayout.SOUTH);
		frame.getContentPane().add(BorderLayout.CENTER, panel);
		frame.pack();
		frame.setLocationByPlatform(true);
		//center of screen
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
		input.requestFocus();
	}

	public class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String inputLine = input.getText().trim();
			if(inputLine.length() > 0 && readyforinput) {
				readyforinput = false;
				write(inputLine);
				playerCombat(inputLine);
			}
			input.setText("");
			input.requestFocus();
		}

	}
}
