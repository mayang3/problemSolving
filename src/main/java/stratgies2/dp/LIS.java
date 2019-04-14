package stratgies2.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * Longest Increasing Subsequence
 *
 * @author baejunbeom
 */
public class LIS {

	static int N;
	static int [] cache;
	static int [] sequence;

	static int lis(int start) {

		if (cache[start] != -1) {
			return cache[start];
		}

		cache[start] = 1;

		for (int next=start+1 ; next<N ; next++) {
			// 현재 기준값보다 큰 부분 수열들만 검사한다.
			if (sequence[start] < sequence[next]) {
				// cache[j] 는 현재 값의 길이 1과, 다음 부분수열의 max + 1 을 비교해서 큰값으로 설정한다.
				cache[start] = Math.max(cache[start], 1 + lis(next));
			}
		}

		return cache[start];
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		sequence = new int[N];
		cache = new int[N];

		for (int i=0 ; i<N ; i++) {
			sequence[i] = scanner.nextInt();
		}

		Arrays.fill(cache, -1);

		System.out.println(lis(0));
	}
}
