package game;

public abstract class Armor {

	//armorstatus?
	protected RandGen rand = new RandGen();
	
	protected int weight;
	protected int damageReduction; //flat -x damage from damage dealt
	protected int damageResistance; //%protection from remaining damage
	
	public abstract String toString();
	
	public int getDamageReduction() {
		return damageReduction;
	}
	
	public int getDamageResistance(int dmg) {
		return (100 - damageResistance) * dmg / 100;
	}
	
}
