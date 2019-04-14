package hackerrank.cs.algorithm.implement;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 3가지 경우의 수가 있다.
 *
 * 1. k 가 홀수인 경우
 * 2. k 가 짝수인 경우
 * 3. 나머지가 0인 경우
 *
 * k 로 나누어 떨어질 수 있는 숫자들의 집합은 k 의 나머지 조합에 의존된다
 *
 * 예를 들어, k 가 3 이라고 하면,
 *
 * 0을 제외한, 나머지의 조합은 (1,2) 뿐이다.
 *
 * 이 말은, 나머지가 1인 녀석과 2인 녀석이 함께 있다면 해당 조합에서는 더하기 연산을 통해 k가 존재할 수 있다는 뜻이다.
 *
 * 그러므로 k 의 나머지 조합에서는 둘중 한가지만 선택이 가능하다.
 *
 * 그런데, 문제에서는 가장 개수가 많은 조합을 출력하라고 했으므로, 나머지가 1 or 2 인 녀석들 중에 카운트가 더 많은 녀석을 가져온다.
 *
 * 그리고 special case 로 나머지가 0인 경우가 있는데.
 *
 * 나머지가 0인 경우는 오직 1번만 선택될 수 있다.
 *
 * (왜냐하면 나머지가 0인 경우가 2개 이상 될 경우, 나머지가 0인 수들은 더해서 k 로 나누어 떨어지게 된다.)
 * (하지만, 나머지가 0인 경우가 1개만 존재하면, 나머지가 0 이 아닌 수들과 더해지게 되므로 나누어 떨어지지 않는다. )
 *
 * 그리고, 또 한가지 경우는 k 가 짝수인 경우인데,
 *
 * 예를 들어 4같은 경우 나머지 조합이 (1,3), (2,2) 이다.
 *
 * 이때 2,2 와 같이 나머지의 두 수가 똑같은 경우라면 이 역시 0's case 처럼 1번만 카운팅 되어야 한다.
 *
 * 만약, 두번 카운팅 된다면, 이 역시 두 수를 합해도 k 로 나누어질 수 있다.
 *
 * 예를 들어보자.
 *
 * 10 4
 * 1 2 3 4 5 6 7 8 9 10
 *
 * 위와 같은 경우 나머지의 배열은
 *
 * 1,2,3,0,1,2,3,0,1,2 이다.
 *
 * 나머지가 2인 녀석이 3개나 있는데, 이것이 다 카운트 된면 안된다.
 *
 * 예를 들어, 2와 6이 카운트 된다면 둘을 더하면 k 인 4 로 나누어 떨어지게 되므로 정답이 될 수 없다.
 *
 */
public class NonDivisibleSubset {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int k = scanner.nextInt();

		// k 에 대한 remainder array
		Set[] rCounts = new Set[k];

		for (int i=0 ; i<k; i++) {
			rCounts[i] = new HashSet();
		}

		for (int i = 0; i < n; i++) {
			int v = scanner.nextInt();

			rCounts[v%k].add(v);
		}

		Set<Integer> ret = new HashSet<Integer>();

		for (int i=1 ; i<=k/2 ; i++) {
			if (i == k-i) {
				continue;
			}

			if (rCounts[i].size() <= rCounts[k-i].size()) {
				ret.addAll(rCounts[k-i]);
			} else {
				ret.addAll(rCounts[i]);
			}
		}

		int count = ret.size();

		// special case
		if (rCounts[0].size() > 0) {
			count++;
		}

		// even case
		if (k % 2 == 0 && rCounts[k/2].size() > 0) {
			count++;
		}

		System.out.println(count);
	}
}
