package leetcode;

import java.util.*;

public class MeetingRooms2 {
	/**
	 *
	 * @param intervals
	 * @return
	 */
	public int minMeetingRooms(int[][] intervals) {
		Map<Integer, Integer> map = new TreeMap<>();

		for (int [] interval : intervals) {
			map.merge(interval[0], 1, Integer::sum);
			map.merge(interval[1], -1, Integer::sum);
		}

		int room = 0;
		int max = 0;

		for (int val : map.values()) {
			room += val;
			max = Math.max(max, room);
		}

		return max;
	}

	public static void main(String[] args) {
		int [][] intervals = {
			{7, 10},{2, 4}
		};

		MeetingRooms2 mr2 = new MeetingRooms2();
		System.out.println(mr2.minMeetingRooms(intervals));
	}
}