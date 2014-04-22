package armor.head;

import armor.HeadArmor;

public class HadesHelm extends HeadArmor {

	public HadesHelm() {
		weight = 100; //arbitrary
		damageReduction = -100;
		damageResistance = 20;
		headshotProtection = 75;
	}
	
	@Override
	public String toString() {
		return "Hades' Helm";
	}
}
