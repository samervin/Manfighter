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
	
<<<<<<< HEAD:src/weapon/melee/fists/Fists.java
=======
	public boolean isReadied() {
		return false; //fists are trivially always ready -- no movement penalty
	}
	
	public void setReadied(boolean r) {
		//do nothing, invalid
	}

	public int getFireTime() {
		return 1000; //milliseconds
	}
	
>>>>>>> 912e415c789495c74e241e65b49e7ca2a9be82fa:src/weapon/Fists.java
}
