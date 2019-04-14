package strategies.dp.numberOfCase;

/**
 * @author baejunbeom
 */
public class AsymTiling {
	private static final int MOD = 1000000007;

	private Tiling2 tiling2 = new Tiling2();

	/**
	 * 홀수, 짝수 각각의 경우에서, 왼쪽의 회색 부분을 채우고 나면,
	 * 나머지 오른쪽 회색 부분은 대칭이기 때문에 자연스럽게 결정된다.
	 *
	 * 결국 이 방법들은 회색 부분 하나를 채우는 방법과 1:1 대응된다.
	 *
	 * 이것이 핵심임..
	 *
	 * @param width
	 * @return
	 */
	public int asymmetric(int width) {
		if (width % 2 == 1) {
			return (tiling2.tiling(width) - tiling2.tiling(width/2) + MOD) % MOD;
		}

		int ret = tiling2.tiling(width);

		ret = (ret - tiling2.tiling(width/2) + MOD) % MOD;
		// 여기서 -1 을 하는 이유는 짝수인 경우 대칭 타일링 방법이 두개 나오는데,
		// 그중 하나가 바로 위에서 계산되었기 때문이다.
		ret = (ret - tiling2.tiling(width/2-1) + MOD) % MOD;

		return ret;
	}

	public static void main(String[] args) {
		AsymTiling asymTiling = new AsymTiling();
		System.out.println(asymTiling.asymmetric(92));
	}
}
