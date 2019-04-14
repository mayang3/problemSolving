package baekjoon.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Accepted !!
 * @author baejunbeom
 */
public class Upstairs2 {

	static int MAX;
	static int [] input;
	static int [][] cache;

	static int desSolve(int start, int before) {
		if (start < 0) {
			return 0;
		}

		int max = 0;

		// 맨 위의 계단이거나, 연속해서 두계단을 밟지 않은 경우, 한 계단을 내려갈 수 있다.
		if ((start == MAX || before > start + 1) && start - 1 >= 0) {
			if (cache[start - 1][start] == -1) {
				cache[start - 1][start] = desSolve(start - 1, start);
			}

			max = Math.max(max, cache[start-1][start]);
		}

		// 계단이 있기만 하다면, 두 계단은 언제든 내려갈 수 있다.
		if (start-2 >= 0) {
			if (cache[start - 2][start] == -1) {
				cache[start - 2][start] = desSolve(start - 2, start);
			}

			max = Math.max(max, cache[start-2][start]);
		}

		// 한 계단을 내려간 경우와,
		// 두계단을 내려간 경우중에 최대 값을 현재 계단의 점수와 더해서 반환한다.
		return input[start] + max;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		input = new int[N];
		cache = new int[N+2][N+2];
		MAX = input.length -1;

		for (int [] c : cache) {
			Arrays.fill(c, -1);
		}

		for (int i=0 ; i<N ; i++) {
			input[i] = scanner.nextInt();
		}

		System.out.println(desSolve(input.length-1, input.length-1));

	}
}
