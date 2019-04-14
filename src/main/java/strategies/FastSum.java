package strategies;

/**
 * @author baejunbeom
 */
public class FastSum {

	int fastSum(int n) {
		// base case
		if (n == 1) {
			return 1;
		}

		if (n % 2 == 1) {
			return fastSum(n-1) + n;
		}

		return 2 * fastSum(n/2) + (n/2) * (n/2);
	}

	public static void main(String[] args) {
		FastSum fastSum = new FastSum();
		int count = fastSum.fastSum(10);

		System.out.println(count);
	}
}
