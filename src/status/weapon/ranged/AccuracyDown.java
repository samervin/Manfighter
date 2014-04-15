package status.weapon.ranged;

import status.weapon.neutral.WeaponStatus;
import game.RandGen;

public class AccuracyDown extends WeaponStatus {

	public String toString() {
		return "Inaccurate";
	}

	public int getDamage(int d) {
		RandGen rand = new RandGen();
		
		if(rand.getOdds(1, 6))
			return 0;
		return d;
	}
}
