package strategies.graph.bfs;

import java.util.*;

/**
 * @author baejunbeom
 */
@SuppressWarnings("ALL")
public class SortingGame2 {
	static Map<String, Integer> toSort = new HashMap<>();

	/**
	 * [0,..., n-1] 의 모든 순열에 대해 toSort[] 를 계산해 저장한다.
	 * @param n
	 */
	static void preCalc(int n) {
		List<Integer> perm = new ArrayList<>(n);

		// 정렬된 상태부터 역으로 찾아간다.
		for (int i=0; i<n ; i++) perm.add(i);

		Queue<List<Integer>> q = new LinkedList<>();

		q.add(perm);

		// 최초 정렬된 상태는 cost 가 0 이다.
		toSort.put(perm.toString(), 0);

		while (!q.isEmpty()) {
			List<Integer> here = q.poll();

			int cost = toSort.get(here.toString());

			// 순서를 뒤집을 수 있는 모든 경우에 대해 bfs 로 탐색을 하며 비용을 계산한다.
			// bfs depth 가 늘어날 때마다, 이전 단계의 뒤집힌 수열에서 다시 한번 뒤집는 단계이다.
			for (int i=0; i<n; i++) {
				for (int j=i+2; j<=n; j++) {
					List<Integer> orgHere = new ArrayList<>();
					orgHere.addAll(here);

					Collections.reverse(here.subList(i, j));

					if (toSort.get(here.toString()) == null || toSort.get(here.toString()) == 0) {
						toSort.put(here.toString(), cost + 1);
						q.add(here);
					}

					here = orgHere;
				}
			}
		}
	}

	/**
	 *
	 * @param perm permutation (순열)
	 * @return
	 */
	static int solve(final List<Integer> perm) {
		// perm 을 [0...n-1] 의 순열로 변환한다.
		int n = perm.size();

		List<Integer> fixed = new ArrayList<>();

		for (int i=0 ; i<n ; i++) {
			int smaller = 0;

			for (int j=0 ; j<n ; j++) {
				if (perm.get(j) < perm.get(i)) {
					smaller++;
				}
			}

			fixed.add(smaller);
		}

		//
		return toSort.get(fixed.toString());
	}

	public static void main(String[] args) {
		List<Integer> perm = new ArrayList<>();
		perm.add(3);
		perm.add(4);
		perm.add(1);
		perm.add(2);

		preCalc(4);
		int solve = solve(perm);

		System.out.println(solve);

	}
}
