package algospot;

import java.util.Scanner;

public class RATIO {
	// 도경이가 할 생각이 있는 최대 게임의 수
	static long L = (long)2e9;
	// b 게임 중 a 게임 승리했을 때의 승률
	static int ratio(long b, long a) {
		return (int)((a * 100) / b);
	}

	// 최소 몇 연승 해야 승률이 올라갈까?

	/**
	 * @param games
	 * @param won
	 * @return
	 */
	static int neededGames(long games, long won) {
		// 불가능한 경우를 미리 걸러낸다. (20억 게임을 모두 추가로 이겼는데도 승률이 올라가지 않는 경우)
		if (ratio(games, won) == ratio(games + L, won + L)) {
			return -1;
		}

		long lo = 0;
		long hi = L;

		while (lo + 1 < hi) {
			long mid = (lo + hi) / 2;

			if (ratio(games, won) == ratio(games + mid, won + mid)) {
				lo = mid;
			} else {
				hi = mid;
			}
		}

		return (int)hi;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			int N = scanner.nextInt(); // gaems
			int M = scanner.nextInt(); // won

			System.out.println(neededGames(N, M));
		}
	}
}
