package game;

import java.util.Random;


//this class is safe to initialize once per session:
//it generates a new random seed each time you call a function
//for MAXIMUM RANDOMITUDE
public class RandGen
{	
		//fancy wrapper for built-in Random function, allows for easy ranges
	public int getRand(int min, int max)
	{
		Random rand = new Random();
		return rand.nextInt(max - min + 1) + min;
	}
	
	//wrapper for getRand: given that something happens x times out of y rolls,
	//roll a dice and return whether that something happened
	public boolean getOdds(int x, int y) {		
		int q = getRand(1, y);
		if(q <= x)
			return true;
		return false;
	}

	//math magic: values on the ends are a bit more likely
	public int getRandEnds(int min, int max)
	{
		Random rand = new Random();
		double rnd = rand.nextDouble();
		rnd = 2 * rnd - 1;
		rnd = Math.signum(rnd) * rnd * rnd;
		rnd = Math.signum(rnd) - rnd;

		return (int)Math.round(min + (max - min) * (rnd + 1) / 2);
	}

	//math easy beans: values in the middle are MUCH more likely
	public int getRandMid(int min, int max)
	{
		return (int)((getRand(min, max) + getRand(min, max)) / 2.0 + .5);
	}
}