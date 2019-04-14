package strategies;

import java.util.Arrays;

/**
 * @author baejunbeom
 */
public class Karatsuba {

	// a += b * (10^k);
	void addTo(int [] a, int [] b, int k) {

	}

	// a -= b;
	void subFrom(int [] a, int [] b) {

	}

	public int [] karatsuba(int [] a, int [] b) {
		int an = a.length, bn = b.length;

		// a가 b보다 짧을 경우, 둘을 바꾼다.
		if (an < bn) {
			return karatsuba(b, a);
		}

		// base case 1 : a나 b가 비어 있는 경우
		if (an == 0 || bn == 0) {
			return new int[]{};
		}

		int half = an / 2;

		// a 와 b 를 밑에서 half 자리와 나머지로 분리한다.
		int [] a0 = Arrays.copyOfRange(a, 0, half);
		int [] a1 = Arrays.copyOfRange(a, half + 1, a.length - 1);
		int [] b0 = Arrays.copyOfRange(b, 0, Math.min(b.length - 1, half));
		int [] b1 = Arrays.copyOfRange(b, Math.min(b.length - 1, half), b.length - 1);

		// z2 = a1 * b1
		int [] z2 = karatsuba(a1, b1);

		// z0 = a0 * b0
		int [] z0 = karatsuba(a0, b0);

		// a0 = a0 + a1; b0 = b0 + b1;
		addTo(a0, a1, 0); addTo(b0, b1, 0);

		// z1 = (a0 * b0) - z0 - z2;
		int [] z1 = karatsuba(a0, b0);
		subFrom(z1, z0);
		subFrom(z1, z2);

		// ret = z0 + z1 * 10^half + z2 * 10^(half*2)
		int [] ret = new int[100];

		addTo(ret, z0, 0);
		addTo(ret, z1, half);
		addTo(ret, z2, half * 2);

		return ret;
	}
}
