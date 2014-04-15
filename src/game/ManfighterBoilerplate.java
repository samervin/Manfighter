package game;

import javax.swing.JOptionPane;

import person.Person;
import person.Player;

//intended to make the Manfighter class only about the combat,
//with this class reserved for generic methods that act only on individual players
public class ManfighterBoilerplate {

	int mindist;
	
	public ManfighterBoilerplate(int m) {
		mindist = m;
	}
	
	public String[] getNames(Person p) {
		if(p instanceof Player) {
			String[] names = {"you", "your", "yourself", "are"};
			return names;
		} else {
			String[] names = {p.toString(), "his", "himself", "is"};
			return names;
		}
	}
	
	public int getWaitTime(Person per) {
		if(per instanceof Player) {
			String wait = JOptionPane.showInputDialog("Enter the number of ms you wish to wait:");
			while(wait.equals("") || Integer.parseInt(wait) == 0)
				wait = JOptionPane.showInputDialog("Enter the number of ms you wish to wait:");
			return Integer.parseInt(wait);
		} else {
			return 100; //TODO: this should probably be deterministic!
		}
	}
	
	public int getDistanceBetween(Person p, Person e) {
		return(Math.abs(p.getLocation() - e.getLocation()));
	}

	public boolean canAdvance(Person a, Person b) {
		if(Math.abs(a.getLocation() - b.getLocation()) == mindist)
			return false;
		return true;
	}
	
	public int move(Person p, Person e, int pmove, int emove) {
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
	
}
