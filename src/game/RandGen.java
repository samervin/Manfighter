package game;

import java.util.Random;

public class RandGen
{
	public static int getRand(int min, int max)
	{
		Random rand = new Random();
		return rand.nextInt(max - min + 1) + min;
	}

	public int getRandEnds(int min, int max)
	{
		Random rand = new Random();
		double rnd = rand.nextDouble();
		rnd = 2 * rnd - 1;
		rnd = Math.signum(rnd) * rnd * rnd;
		rnd = Math.signum(rnd) - rnd;

		return (int)Math.round(min + (max - min) * (rnd + 1) / 2);
	}

	public int getRandMid(int min, int max)
	{
		return (int)((getRand(min, max) + getRand(min, max)) / 2.0 + .5);
	}
}