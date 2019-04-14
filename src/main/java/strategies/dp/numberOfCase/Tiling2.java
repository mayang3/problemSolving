package strategies.dp.numberOfCase;

import java.util.Arrays;

/**
 * @author baejunbeom
 */
public class Tiling2 {
	// tiling 이 반환할 수 있는 최대치 + 1 이 mod 값이다.
	private static final int mod = 1000000007;
	private static final int [] cache = new int[101];

	public Tiling2() {
		Arrays.fill(cache, -1);
	}

	public int tiling(int width) {
		// base case : width 가 1 이하일 때
		if (width <= 1) {
			return 1;
		}

		// memoization
		if (cache[width] != -1) {
			return cache[width];
		}

		return cache[width] = (tiling(width-2) + tiling(width-1)) % mod;
	}

	public static void main(String[] args) {
		Tiling2 tiling2 = new Tiling2();
		int tiling = tiling2.tiling(5);

		System.out.println(tiling);
	}

}
