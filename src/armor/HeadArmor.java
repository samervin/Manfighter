package armor;


public abstract class HeadArmor extends Armor {

	protected int headshotProtection;
	
	public boolean headshotProtected() {
		return rand.getOdds(headshotProtection, 100);
	}
	
}
