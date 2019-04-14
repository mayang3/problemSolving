package stratgies2.exhaustiveSearch;

import java.util.Scanner;

/**
 * 이 문제는 각 단계마다 한 쌍을 만들어야 하므로, pair 배열 중 현재 친구가 맺어지지 않은 인덱스만을 체크하는 부분이 중요하다.
 *
 * 만약에, i,j 중에 true 로 이미 체크되었던 또 체크하게 된다면 재귀호출이 꼬이게 되서 값이 정확히 나오지 않는다.
 *
 * (한쌍씩, true 값을 표시해 친구여부를 체크 했다가, 해제할때도 한쌍씩 false 로 해제하기 때문에..)
 *
 * @author baejunbeom
 */
public class Picnic2 {
	static int N;
	static boolean [][] friends;

	/**
	 *
	 * @param pair
	 * @return
	 */
	static int solve(boolean [] pair) {
		if (allPairTrue(pair)) {
			return 1;
		}

		int i = findNextIndex(pair);

		if (i >= N) {
			return 0;
		}

		int sum = 0;

		for (int j=i+1 ; j<N ; j++) {
			if (pair[j] == false && friends[i][j]) {
				pair[i] = pair[j] = true;
				sum += solve(pair);
				pair[i] = pair[j] = false;
			}
		}

		// 만약 i 번째 학생이 짝을 찾지 못했다면 그 이상은 진행할 필요가 없다.
		// 해당 조합으로는 풀 수 있는 경우의 수가 0 이다.
		return sum;
	}

	private static int findNextIndex(boolean[] pair) {
		for (int i=0 ; i<pair.length ; i++) {
			if (pair[i] == false) {
				return i;
			}
		}

		return 0;
	}

	private static boolean allPairTrue(boolean[] pair) {
		for (boolean p : pair) {
			if (!p) return false;
		}

		return true;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int C = scanner.nextInt();

		while (C-- > 0) {
			N = scanner.nextInt();
			int M = scanner.nextInt();

			friends = new boolean[N][N];
			boolean [] pair = new boolean[N];

			while (M-- > 0) {
				int m1 = scanner.nextInt();
				int m2 = scanner.nextInt();

				friends[m1][m2] = true;
				friends[m2][m1] = true;
			}

			System.out.println(solve(pair));
		}
	}
}
