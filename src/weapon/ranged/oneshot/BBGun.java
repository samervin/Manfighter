package weapon.ranged.oneshot;

import java.util.HashSet;

public class BBGun extends BaseOneshot {

	public BBGun(){
		weaponStatus = getRandomStatus();
		damage = 50;
		range = 1000;
		maxClip = 50;
		clip = 50;
		fireTime = 800;
		readyTime = 400;
	}

	@Override
	public String getBaseName() {
		return " BB gun"; //intentional, prevents lowercase bB gun
	}

	@Override
	public int getDamage() {
		clip--;

		if(ready) {
			if(rand.getOdds(99, 100)) {
				return weaponStatus.getDamage(damage);
			}
		} else {
			if(rand.getOdds(95, 100)) {
				return weaponStatus.getDamage(damage);
			}
		}
		return 0;
	}

	@Override
	public HashSet<Character> getWeaponActions() {
		HashSet<Character> a = new HashSet<Character>();
		if(this.hasLoadedAmmo())
			a.add('a');
		if(!this.hasFullAmmo())
			a.add('o');

		if(ready)
			a.add('l');
		else
			a.add('r');

		return a;
	}

}
