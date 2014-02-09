package status.weapon;

import game.RandGen;
import game.WeaponStatus;

public class AccuracyDown extends WeaponStatus {

	public String toString() {
		return "Inaccurate";
	}

	public int getDamage(int d) {
		RandGen rand = new RandGen();
		
		if(rand.getRand(1, 6) == 1)
			return 0;
		return d;
	}
}
