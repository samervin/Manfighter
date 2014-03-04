package armor;


public abstract class HeadArmor extends Armor {

	protected int headshotProtection;
	
	public boolean headshotProtected(String location) {
		if(location.equals("head"))
			return rand.getOdds(headshotProtection, 100);
		return false;
	}
	
}
