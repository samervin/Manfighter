package weapon.melee.fists;

public class Fists extends BaseFists {
	
	public Fists() {
		damage = 100;
		range = 60;
		fireTime = 700;
	}
	
	public String getBaseName() {
		return "Bare fists";
	}
}
