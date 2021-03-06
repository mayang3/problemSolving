package leetcode;

import java.util.*;

/**
 * Q.  Given a collection of intervals, merge all overlapping intervals.
 *
 * @author baejunbeom
 */
public class MergeInterval {

	/**
	 * @author baejunbeom
	 */
	public int[][] merge(int[][] intervals) {
		if (intervals == null) {
			return new int[0][0];
		}

		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));

		// O(NlogN)
		for (int [] interval : intervals) {
			pq.add(interval);
		}

		int start = -1;
		int end = -1;

		List<int[]> res = new ArrayList<>();

		while (pq.isEmpty() == false) {
			int[] poll = pq.poll();

			if (end < poll[0]) {
				if (end != -1) {
					res.add(new int[] {start, end});
				}
				start = poll[0];
				end = poll[1];
			} else if (end >= poll[0]) {
				end = Math.max(end, poll[1]);
			}
		}

		if (end != -1) {
			res.add(new int[] {start, end});
		}

		int [][] resArr = new int[res.size()][2];

		for (int i = 0; i < resArr.length; i++) {
			resArr[i] = res.get(i);
		}

		return resArr;
	}

	public static void main(String[] args) {
		int [][] intervals = {{1,4},{4,5}};

		MergeInterval mergeInterval = new MergeInterval();
		System.out.println(Arrays.deepToString(mergeInterval.merge(intervals)));
	}

}
