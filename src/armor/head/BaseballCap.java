package armor.head;

import armor.HeadArmor;

public class BaseballCap extends HeadArmor {

	public BaseballCap() {
		weight = 100; //arbitrary
		damageReduction = 5;
		damageResistance = 0;
		headshotProtection = 0;
	}

	public String toString() {
		return "Baseball cap";
	}
	
}
