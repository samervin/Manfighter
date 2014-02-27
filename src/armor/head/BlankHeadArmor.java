package armor.head;

import armor.HeadArmor;

public class BlankHeadArmor extends HeadArmor {

	public BlankHeadArmor() {
		weight = 0;
		damageReduction = 0;
		damageResistance = 0;
		headshotProtection = 0;
	}
	
	
	public String toString() {
		return "no hat";
	}
}
