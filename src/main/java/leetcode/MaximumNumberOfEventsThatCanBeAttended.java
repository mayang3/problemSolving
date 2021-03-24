package leetcode;

import java.util.*;

public class MaximumNumberOfEventsThatCanBeAttended {
	public int maxEvents(int[][] A) {
		// 0. 시간 시간으로 정렬한다. O(nlogn)
		// 시작 시간으로 우선 정렬해주어야 아래 loop 에서 d 시간과 일치하는 녀석을 뽑아올때 남김없이 뽑아올 수 있다.
		Arrays.sort(A, Comparator.comparingInt(o -> o[0]));

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		int i = 0;
		int n = A.length;
		int res = 0;

		for (int d = 1; d <= 100000; d++) {
			// 1. 참석 못하는 구간은 버린다.
			while (!pq.isEmpty() && pq.peek() < d) {
				pq.poll();
			}

			// 2. 참석 가능한 구간을 넣어준다.
			// 이때 시작시간을 기준으로 loop 를 돌리므로, startTime 과 d 값이 정확히 일치하는 녀석들만 넣어줘도 빠지는 녀석 없이 넣을 수 있다.
			while (i < n && A[i][0] == d) {
				pq.add(A[i++][1]);
			}

			if (pq.isEmpty() == false) {
				pq.poll();
				res++;
			}
		}

		return res;
	}

	public static void main(String[] args) {
		int [][] events = {{1,2},{1,2},{3,3},{1,5},{1,5}};

		MaximumNumberOfEventsThatCanBeAttended attended = new MaximumNumberOfEventsThatCanBeAttended();
		System.out.println(attended.maxEvents(events));
	}
}
