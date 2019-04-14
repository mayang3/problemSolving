package stratgies2.dp_advance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * input)
 * 5
 * 5 2 3 4 6
 *
 * output)
 * 2, 3, 4, 6
 *
 * @author baejunbeom
 */
public class LisPrint {
	static int N;
	static int [] cache = new int[101];
	static int [] S = new int[100];
	static int [] choices = new int[101];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();

		for (int i=0 ; i<N ; i++) {
			S[i] = scanner.nextInt();
		}

		Arrays.fill(cache, -1);

		System.out.println(lis4(-1));

		List<Integer> seq = new ArrayList<>();
		reconstruct(-1, seq);

		System.out.println(seq);
	}

	// S[start] 에서 시작하는 증가 부분 수열 중 최대 길이를 반환한다.
	static int lis4(int start) {
		if (cache[start+1] != -1) {
			return cache[start+1];
		}

		// 항상 S[start] 는 있기 때문에 길이는 최하 1
		cache[start+1] = 1;

		int bestNext = -1;
		for (int next = start + 1 ; next < N ; next++) {
			if (start == -1 || S[start] < S[next]) {
				// length of increase subsequence
				int cand = lis4(next) + 1;

				// 원래의 LIS 라면, 이 부분에서 Math.max(cache[start+1], cand) 처리를 했지만,
				// 여기서는 실제 리턴되는 값인, "부분 수열의 길이" 이외에 수열을 찾기 위해 "최적해의 인덱스" 도 함께 저장한다.
				if (cand > cache[start+1]) {
					cache[start+1] = cand;
					bestNext = next;
				}
			}
		}

		// 최적해의 index 를 choices 배열에 저장해둔다.
		choices[start+1] = bestNext;
		return cache[start+1];
	}

	/**
	 * 위에서 저장해두었던 최적해의 index 를 가지고, 실제 수열을 복원한다.
	 * @param start
	 * @param seq
	 */
	static void reconstruct(int start, List<Integer> seq) {
		if (start != -1) {
			seq.add(S[start]);
		}

		int next = choices[start+1];

		if (next != -1) {
			reconstruct(next, seq);
		}
	}
}
