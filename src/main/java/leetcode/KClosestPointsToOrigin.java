package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {
	public int[][] kClosest(int[][] points, int K) {
		PriorityQueue<Distance> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.d < o2.d) {
				return -1;
			}

			return o1.d == o2.d ? 0 : 1;
		});

		for (int [] p : points) {
			double d = Math.sqrt(Math.pow(p[0], 2) + Math.pow(p[1], 2));
			pq.add(new Distance(d, p));
		}

		int [][] ret = new int[K][2];

		for (int i = 0; i < K; i++) {
			Distance distance = pq.poll();
			ret[i][0] = distance.p[0];
			ret[i][1] = distance.p[1];
		}

		return ret;
	}

	static class Distance {
		double d;
		int [] p;

		Distance(double d, int [] p) {
			this.d = d;
			this.p = p;
		}
	}

	public static void main(String[] args) {
		int [][] points = {{3,3},{5,-1},{-2,4}};
		int K = 2;

		KClosestPointsToOrigin kClosestPointsToOrigin = new KClosestPointsToOrigin();
		int[][] kClosest = kClosestPointsToOrigin.kClosest(points, K);

		System.out.println(Arrays.deepToString(kClosest));
	}
}
