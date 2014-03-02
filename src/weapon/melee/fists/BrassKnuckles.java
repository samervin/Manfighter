package weapon.melee.fists;

public class BrassKnuckles extends BaseFists {

	public BrassKnuckles(){
		damage = 100;
		range = 2;
		fireTime = 715;
	}
	
	@Override
	public String getBaseName() {
		return "Brass knuckles";
	}
	
	@Override
	public String getDamageType() {
		return "piercing";
	}

}
