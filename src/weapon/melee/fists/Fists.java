package weapon.melee.fists;

public class Fists extends BaseFists {
	
	public Fists() {
		damage = 100;
		range = 60;
		swingTime = 700;
	}
	
	public String getBaseName() {
		return "Bare fists";
	}
}
