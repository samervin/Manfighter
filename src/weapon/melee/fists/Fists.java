package weapon.melee.fists;

public class Fists extends BaseFists {
	
	public Fists() {
		damage = 75;
		range = 0;
		fireTime = 700;
	}
	
	@Override
	public String getBaseName() {
		return "Bare fists";
	}
}
