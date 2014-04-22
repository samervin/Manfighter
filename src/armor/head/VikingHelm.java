package armor.head;

import armor.HeadArmor;

public class VikingHelm extends HeadArmor {

	public VikingHelm() {
		weight = 100; //arbitrary
		damageReduction = 10;
		damageResistance = 1;
		headshotProtection = 1;
	}
	
	@Override
	public String toString() {
		return "Viking helm";
	}
}
