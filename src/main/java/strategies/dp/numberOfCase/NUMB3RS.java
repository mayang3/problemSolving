package strategies.dp.numberOfCase;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author baejunbeom
 */
public class NUMB3RS {

	static int n = 8;
	static int d = 2;
	static int p = 3;
	static int q = 1;

	// connected[i][j] 마을 i,j 가 연결되어 있는지에 대한 여부값
	static int [][] connected = {
		{0, 1, 1, 1, 0, 0, 0, 0},
		{1, 0, 0, 1, 0, 0, 0, 0},
		{1, 0, 0, 1, 0, 0, 0, 0},
		{1, 1, 1, 0, 1, 1, 0, 0},
		{0, 0, 0, 1, 0, 0, 1, 1},
		{0, 0, 0, 1, 0, 0, 0, 1},
		{0, 0, 0, 0, 1, 0, 0, 0},
		{0, 0, 0, 0, 1, 1, 0, 0}
	};
	// deg[i] = 마을 i 와 연결된 마을의 개수 (차수)
	static int [] deg = {
		3, 2, 2, 5, 3, 2, 1, 2
	};

	static double search(LinkedList<Integer> path) {
		// base case : d 일이 지난 경우
		// recursive 한번씩 돌때마다 path 가 1씩 늘어나므로, 아래와 같은 체크가 가능하다.
		if (path.size() == d+1) {
			// 이 시나리오가 q에서 끝나지 않는다면 무효
			if (path.getLast() != q) {
				return 0.0;
			}

			// path 의 출현 확률을 계산한다.
			double ret = 1.0;

			// path 에 연결된 마을의 차수로 나눈다.
			for (int i=0 ; i<path.size() - 1 ; i++) {
				ret /= deg[path.get(i)];
			}

			return ret;
		}

		double ret = 0;
		// 경로의 다음 위치를 결정한다.
		// 현재 path 로 갈 수 있는 경로는 모두 체크해본다.
		for (int there = 0 ; there < n ; there++) {
			if (connected[path.getLast()][there] == 1) {
				path.addLast(there);
				ret += search(path);
				path.removeLast();
			}
		}

		return ret;
	}

	static double [][] cache = new double[51][101];

	static {
		for (double [] ca : cache) {
			Arrays.fill(ca, -1);
		}
	}

	private static int count = 0;
	// days 일째에 here 번 마을에 숨어 있다고 가정하고,
	// 마지막 날에 q번 마을에 숨어 있을 조건부 확률을 반환한다.
	// 이 알고리즘은 O(nd) 의 부분문제를 갖고 각각을 O(n) 시간에 해결하므로,
	// 전체 시간 복잡도는 O(n^2d) 가 된다.
	static double search2(int here, int days) {
		// base case : d 일이 지난 경우,
		if (days == d) {
			return (here == q ? 1.0 : 0.0);
		}

		// memoization
		if (cache[here][days] > -0.5) {
			return cache[here][days];
		}

		cache[here][days] = 0.0;

		for (int there=0 ; there < n ; there++) {
			if (connected[here][there] == 1) {
				// 여기서 덧셈처리를 해주는 것은, 각 시나리오의 확률을 더해주는 것이다.
				cache[here][days] += search2(there, days+1) / deg[here];
			}
		}

		return cache[here][days];
	}

	static double search3(int here, int days) {
		// base case : 0 일째.
		if (days == 0) {
			return (here == p ? 1.0 : 0.0);
		}

		if (cache[here][days] > -0.5) {
			return cache[here][days];
		}

		cache[here][days] = 0.0;

		for (int there=0; there<n ; there++) {
			if (connected[here][there] == 1) {
				cache[here][days] += search3(there, days-1) / deg[there];
			}
		}

		return cache[here][days];
	}

	public static void main(String[] args) {
		LinkedList<Integer> integers = new LinkedList<>();

		integers.add(p);

		System.out.println(search(integers));
		System.out.println(search2(p, 0));
//		System.out.println(search3(q, 2));
	}
}
