package strategies;

/**
 */
public class Bino {

	public int bino(int n, int r) {
		// base case 1 : n=r (모든 원소를 다 고르는 경우)
		// base case 2 : r=0 (고를 원소가 없는 경우)
		if (r == 0 || n == r) {
			return 1;
		}

		return bino(n-1, r-1) + bino(n-1, r);
	}
}
