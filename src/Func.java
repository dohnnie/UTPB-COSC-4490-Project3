package src;

public class Func
{
	public static double sigmoid(double x) {
		return Math.exp(x) / (1 + Math.exp(x));
	}

	public static double tanh(double x) {
		return Math.tanh(x);
	}

}
