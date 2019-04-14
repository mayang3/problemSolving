package strategies.graph.bfs;

import java.util.*;

/**
 * @author baejunbeom
 */
public class SortingGame {

	// perm 을 정렬하기 위해 필요한 최소 뒤집기 연산의 수를 계산한다.
	static int bfs(List<Integer> perm) {
		int n = perm.size();

		// 목표 정점을 미리 계산한다.
		List<Integer> sorted = new ArrayList<>();
		sorted.addAll(perm);
		Collections.sort(sorted);

		// 방문 목록과 시작점부터 각 정점까지의 거리
		Queue<List<Integer>> q = new LinkedList<>();
		Map<String, Integer> distance = new HashMap<>();

		// 시작점을 큐에 넣는다.
		distance.put(perm.toString(), 0);

		q.add(perm);

		while (q.isEmpty() == false) {
			List<Integer> here = q.poll();

			// 목표 정점을 발견했으면 곧장 종료한다.
			if (here.equals(sorted)) return distance.get(here.toString());

			final int cost = distance.get(here.toString());

			// 가능한 모든 부분 구간을 뒤집어 본다.
			for (int i=0 ; i<n ; i++) {
				for (int j= i+2 ; j <=n ; j++) {
					List<Integer> orgHere = new ArrayList<>();
					orgHere.addAll(here);

					Collections.reverse(here.subList(i, j));

					// 역방향 정렬된 수열에 방문하지 않았다면,
					if (distance.get(here.toString()) == null || distance.get(here.toString()) == 0) {
						distance.put(here.toString(), cost + 1);
						q.add(here);
					}

					here = orgHere;
				}
			}
		}

		// oops
		return -1;
 	}

	public static void main(String[] args) {
		List<Integer> input = new ArrayList<>();
		input.add(3);
		input.add(4);
		input.add(1);
		input.add(2);

		int bfs = bfs(input);

		System.out.println(bfs);
	}
}
