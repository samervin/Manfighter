package game;

import java.util.Random;

public class RandGen
{
	static Random rand = new Random();

	public static int getRand(int min, int max)
	{
		return rand.nextInt(max - min + 1) + min;
	}

	public static int getRandEnds(int min, int max)
	{
		double rnd = rand.nextDouble();
		rnd = 2 * rnd - 1;
		rnd = Math.signum(rnd) * rnd * rnd;
		rnd = Math.signum(rnd) - rnd;

		return (int)Math.round(min + (max - min) * (rnd + 1) / 2);
	}

	public static int getRandMid(int min, int max)
	{
		return (int)((getRand(min, max) + getRand(min, max)) / 2.0 + .5);
	}
}