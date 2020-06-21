package leetcode;

import java.util.*;

public class IntervalListIntersections {
	public int[][] intervalIntersection(int[][] A, int[][] B) {
		PriorityQueue<Interval> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.start == o2.start) {
				return o1.end - o2.end;
			}

			return o1.start - o2.start;
		});

		for (int [] a : A) {
			pq.add(new Interval(a[0], a[1]));
		}

		for (int [] b : B) {
			pq.add(new Interval(b[0], b[1]));
		}

		if (pq.isEmpty()) {
			return new int[0][0];
		}

		List<Interval> retList = new ArrayList<>();

		Interval first = pq.poll();

		int beforeStart = first.start;
		int beforeEnd = first.end;

		while (pq.isEmpty() == false) {
			Interval next = pq.poll();

			int nextStart = next.start;
			int nextEnd = next.end;

			if (beforeEnd >= nextStart) {
				retList.add(new Interval(Math.max(beforeStart, nextStart), Math.min(beforeEnd, nextEnd)));
			}

			if (nextEnd > beforeEnd) {
				beforeStart = nextStart;
				beforeEnd = nextEnd;
			}
		}

		if (retList.isEmpty()) {
			return new int[0][0];
		}

		int [][] ret = new int[retList.size()][2];

		for (int i = 0; i < retList.size(); i++) {
			int [] sub = new int[2];
			sub[0] = retList.get(i).start;
			sub[1] = retList.get(i).end;

			ret[i] = sub;
		}

		return ret;
	}

	static class Interval {
		int start;
		int end;

		public Interval(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) {
		int[][] A = {{0, 2}, {5, 10}, {13, 23}, {24, 25}};
		int[][] B = {{1, 5}, {8, 12}, {15, 24}, {25, 26}};

		IntervalListIntersections intersections = new IntervalListIntersections();

		int[][] res = intersections.intervalIntersection(A, B);

		System.out.println(Arrays.deepToString(res));
	}
}
