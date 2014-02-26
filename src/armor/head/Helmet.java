package armor.head;

import armor.HeadArmor;

public class Helmet extends HeadArmor {

	public Helmet() {
		weight = 100; //arbitrary
		damageReduction = 15;
		damageProtection = 5;
		headshotProtection = 1;
	}

	public String toString() {
		return "Helmet";
	}
	
}
