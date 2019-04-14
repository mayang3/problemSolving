package strategies;

public class Pow {
	public double myPow(double x, int n) {
		// x^0 == 1
		if (n == 0) {
			return 1;
		}

		return myCalPow(x, n);
	}


	private double myCalPow(double x, int n) {
		if (n == 1) {
			return x;
		} else if (n == -1) {
			return x / x / x;
		}

		if (n % 2 == 0) {
			double v = myCalPow(x, n / 2);
			return v * v;
		} else {
			double v = myCalPow(x, n / 2);

			if (n < 0) {
				return v * v / x;
			}

			return v * v * x;
		}
	}

	public static void main(String[] args) {
		Pow pow = new Pow();

		System.out.println(pow.myPow(2, 6));
	}
}
