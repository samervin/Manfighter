package status.weapon;

import game.RandGen;
import game.WeaponStatus;

public class CritUp extends WeaponStatus {

	public String toString() {
		return "Lucky";
	}
	
	public boolean getCritChance() {
		RandGen rand = new RandGen();
		
		return (rand.getOdds(1, 4));
	}

}
