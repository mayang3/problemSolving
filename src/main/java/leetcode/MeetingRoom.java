package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MeetingRoom {
	public boolean canAttendMeetings(int[][] intervals) {
		PriorityQueue<Time> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.start));

		for (int [] interval : intervals) {
			pq.add(new Time(interval[0], interval[1]));
		}

		int usedTime = 0;


		while (pq.isEmpty() == false) {
			Time time = pq.poll();

			if (time.start < usedTime) {
				return false;
			}

			usedTime = time.end;
		}

		return true;
	}

	static class Time {
		int start;
		int end;

		public Time(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) {
		int [][] intervals = {
			{7,10},{2,4}
		};

		MeetingRoom mr = new MeetingRoom();
		boolean res = mr.canAttendMeetings(intervals);

		System.out.println(res);
	}
}
