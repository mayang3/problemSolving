package strategies.dp.numberOfCase;

import java.util.Arrays;

/**
 * {@link AsymTiling} 를 다른 방법으로 푼 경우
 *
 * {@link AsymTiling} 에서는 전체 타일링 개수 - 대칭 타일링 개수 = 비대칭 타일링 개수 의 식으로 문제를 풀었다면,
 *
 * 이 class 에서는 직접 비대칭 타일링 개수를 세는 방식으로 문제를 푼다.
 *
 * @author baejunbeom
 */
public class AsymTiling2 {
	private static final int MOD = 1000000007;

	private static final int [] cache = new int[101];

	private final Tiling2 tiling2 = new Tiling2();

	public AsymTiling2() {
		Arrays.fill(cache, -1);
	}

	/**
	 * 2 * width 크기의 사각형을 채우는 비대칭 방법의 수를 반환한다.
	 *
	 * @param width
	 * @return
	 */
	public int asymmetric2(int width) {
		// base case : 너비가 2 이하인 경우
		if (width <= 2) {
			return 0;
		}

		if (cache[width] != -1) {
			return cache[width];
		}

		// case A (세로 블럭 하나씩 width 2 점유, 안의 영역은 무조건 비대칭 타일이어야 한다)
		int ret = asymmetric2(width-2) % MOD;
		// case B (가로 블럭 두개씩 width 4 점유, 안의 영역은 무조건 비대칭 타일이어야 한다)
		ret = (ret + asymmetric2(width-4)) % MOD;
		// case C (가로 블럭 세개로 width 3 점유, 안의 영역은 대칭 or 비대칭 상관 없다)
		ret = (ret + tiling2.tiling(width-3)) % MOD;
		// case D (C 와 동일)
		ret = (ret + tiling2.tiling(width-3)) % MOD;

		return cache[width] = ret;
	}

}
