package src;

import java.util.concurrent.ThreadLocalRandom;

public class Rand
{
	public static long seed;

	public static double pct() {
		return ThreadLocalRandom.current().nextDouble();
	}

	public static int range(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max);
	}

	public static int range(int max) {
		return ThreadLocalRandom.current().nextInt(max);
	}

	public static double interval(double min, double max) {
		return ThreadLocalRandom.current().nextDouble(min, max);
	}

	public static double interval(double max) {
		return ThreadLocalRandom.current().nextDouble(max);
	}

	public static boolean bool() {
		return ThreadLocalRandom.current().nextBoolean();
	}

	public static void seed() {
		ThreadLocalRandom.current().setSeed(seed);
	}

	public static void seed(long s) {
		seed = s > 0 ? s : System.nanoTime();
		seed();
	}
}
