package weapon.melee.fists;
import status.weapon.*;
import game.*;

public class Fists extends BaseFists {

	private int damage = 100;
	private int range = 60;
	private Status status = new BaseStatus();
	
	public String toString() {
		if(status instanceof BaseStatus)
			return this.getBaseName();
		return this.getBaseName() + ", with " + status.toString();
	}
	
	public String getBaseName() {
		return "Bare fists";
	}
	
	public int getDamage() {
		int d = status.getDamage(damage);
		return d;
	}
	
	public int getRange() {
		return range;
	}
	
}
