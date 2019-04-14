package hackerrank.compete;

import java.util.*;

/**
 * 풀 수 있을것 같은데.. time over..
 *
 * @author baejunbeom
 */
public class CloudyDay {

	private static PriorityQueue<Town> pq = new PriorityQueue<>((o1, o2) -> {
		if (o1.p == o2.p) {
			return 0;
		}

		return o1.p < o2.p ? 1 : -1;
	});

	static class Town {
		long x;
		long p;

		Town(long x, long p) {
			this.x = x;
			this.p = p;
		}
	}

	static class Cloud {
		long y;
		long r;

		Cloud(long y, long r) {
			this.y = y;
			this.r = r;
		}

		public boolean covered(Town town) {
			return false;
		}

		public boolean after(Town town) {
			return false;
		}

		public boolean before(Town town) {
			return false;
		}
	}

	/**
	 *
	 * @param p 각 town 의 인구, p.lengh 는 town 의 개수가 된다.
	 * @param x town 의 위치, x.length 도 마찬가지로 town 의 개수이다.
	 * @param y cloud 의 위치, y.length 는 구름의 개수이다.
	 * @param r cloud 의 반지름, y[i] 의 크기는 (y[i] - r[i], y[i] + r[i]) 가 된다.
	 * @return
	 */
	static long maximumPeople(long[] p, long[] x, long[] y, long[] r) {

		for (int i=0 ; i<p.length ; i++) {
			pq.add(new Town(x[i], p[i]));
		}

		LinkedList<Cloud> cloudList = new LinkedList<>();

		for (int i=0 ; i<y.length ; i++) {
			cloudList.add(new Cloud(y[i], r[i]));
		}

		Collections.sort(cloudList, (o1, o2) -> {
			if (o1.y == o2.y) return 0;
			return o1.y < o2.y ? 1 : -1;
		});

		while (pq.isEmpty() == false) {
			Town town = pq.poll();

			while(true) {
				Cloud cloud = cloudList.getLast();

				// 1. 구름이 타운을 덮은 경우, 그 타운의 인구수는 계산하지 않고 다음 타운으로 넘어간다.
				if (cloud.covered(town)) {
					break;
				}

				// 2. 구름이 타운을 덮지 않은 경우,
				// 2-1. 구름이 타운보다 뒤에 있다면 다음 구름을 검사한다.
				if (cloud.after(town)) {
					continue;
				}

				// 2-2. 구름이 타운보다 앞에 있다면 다음 타운을 검사한다.
				if (cloud.before(town)) {
					break;
				}
			}

		}

		// 1. 결국 인구수가 가장 많은 town 의 cloud 를 구하는 문제이다.

		// 2. 인구수가 가장 많은 town 의 cloud 수를 구한다음에, 이 인구를 제거해주고,

		// 3. 나머지 인구들을 O(n) 에 탐색하면서 cloud 에 포함되지 않은 인구들만을 합산해준다.



		return 0;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		long[] p = new long[n];
		for(int p_i = 0; p_i < n; p_i++){
			p[p_i] = in.nextLong();
		}
		long[] x = new long[n];
		for(int x_i = 0; x_i < n; x_i++){
			x[x_i] = in.nextLong();
		}
		int m = in.nextInt();
		long[] y = new long[m];
		for(int y_i = 0; y_i < m; y_i++){
			y[y_i] = in.nextLong();
		}
		long[] r = new long[m];
		for(int r_i = 0; r_i < m; r_i++){
			r[r_i] = in.nextLong();
		}
		long result = maximumPeople(p, x, y, r);
		System.out.println(result);
		in.close();
	}
}
