package weapon.ranged.explosive;

import game.RandGen;
import game.Status;
import status.weapon.BaseStatus;
import status.weapon.DoubleDamage;

public class RocketLauncher extends BaseExplosive {

	private int damage = 350;
	private int range = 200;
	private Status status = new BaseStatus();
	private boolean ready = false;
	private int maxClip = 4;
	private int clip = 4;

	public RocketLauncher() {
		if(RandGen.getRand(1, 10) == 1) {
			status = new DoubleDamage();
		}
	}

	public String toString() {
		if(status instanceof BaseStatus)
			return this.getBaseName();
		return this.getBaseName() + ", with " + status.toString();
	}

	public String getBaseName() {
		return "Rocket launcher";
	}

	public int getDamage() {
		clip--;
		if(ready) {
			if(RandGen.getRand(1, 3) == 1) {
				return status.getDamage(damage);
			}
		}
		else {
			if(RandGen.getRand(1, 4) == 1) {
				return status.getDamage(damage);
			}
		}

		System.out.println(this.toString() + " missed!");
		return 0;
	}

	public int getRange() {
		return range;
	}

	public boolean isReadied() {
		return ready;
	}

	public void setReadied(boolean r) {
		ready = r;
	}
<<<<<<< HEAD:src/weapon/ranged/explosive/RocketLauncher.java

	public boolean hasLoadedAmmo() {
		if(clip == 0)
			return false;
		else
			return true;
	}

	public void reload() {
		clip = maxClip;
=======
	
	public int getFireTime() {
		if(ready)
			return 2000;
		else
			return 3000;
>>>>>>> 912e415c789495c74e241e65b49e7ca2a9be82fa:src/weapon/RocketLauncher.java
	}
}
