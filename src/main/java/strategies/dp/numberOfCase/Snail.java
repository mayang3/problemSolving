package strategies.dp.numberOfCase;

import java.util.Arrays;

/**
 * @author baejunboem
 */
public class Snail {
	private static final int N = 10;
	private static final int M = 5;

	private static final int [][] cache = new int[100][1000];

	/**
	 * 달팽이가 days 일 동안 climbed 미터를 기어올라 왔다고 할때,
	 * m 일 전까지 n 미터를 기어올라갈 수 있는 경우의 수
	 *
	 * @param days 달팽이가 기어올라간 날짜
	 * @param climbed 달팽이가 기어 올라간 미터
	 * @return 경우의 수
	 */
	public int climb(int days, int climbed) {
		// base case : m 일이 모두 지난 경우
		// 기어올라간 경우가 N 이상인 경우만 경우에 수의 합산한다.
		if (days == M) {
			return climbed >= N ? 1 : 0;
		}

		if (cache[days][climbed] != -1) {
			return cache[days][climbed];
		}

		return cache[days][climbed] = climb(days+1, climbed+1) + climb(days+1, climbed+2);
	}

	public static void main(String[] args) {
		for (int[] c : cache) {
			Arrays.fill(c, -1);
		}

		Snail snail = new Snail();
		int climb = snail.climb(0, 0);

		System.out.println(Math.pow(2, M));
		System.out.println(climb);
		System.out.println((double)climb / Math.pow(2, M) * 100);
	}
}
