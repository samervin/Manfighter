package status.weapon;

import game.WeaponStatus;

public class FireRateUp extends WeaponStatus {

	public String toString() {
		return "Quick";
	}

	public int getFireTime(int s) {
		s *=2;
		s /=3;
		
		return s;
	}
	
}
