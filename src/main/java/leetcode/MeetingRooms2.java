package leetcode;

import java.util.*;

public class MeetingRooms2 {
	/**
	 *
	 * @param intervals
	 * @return
	 */
	public int minMeetingRooms(int[][] intervals) {
		if (intervals == null || intervals.length == 0) {
			return 0;
		}

		List<Interval> intervalList = new ArrayList<>();

		for (int i = 0; i < intervals.length; i++) {
			int [] interval = intervals[i];
			intervalList.add(new Interval(interval[0], interval[1]));
		}

		Collections.sort(intervalList, Comparator.comparingInt(o -> o.start));

		PriorityQueue<Interval> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.end));
		// 시작시간이 제일 빠른애를 최초 값으로 넣는다.
		pq.add(intervalList.get(0));

		for (int i = 1; i < intervalList.size(); i++) {
			// 여기서 interval 의 개수는 회의실의 개수이다.
			Interval interval = pq.poll();

			// 현재 회의실의 종료 시간이 다음에 일찍 끝나는 시작시간하고 겹치지 않는다면,
			// 회의실이 추가로 필요하지 않으므로 merge 해준다.
			// interval 은 end 시간 기준으로 정렬되어있고, intervalList 는 start 시간 기준으로 정렬되어 있으므로 항상 이 비교는 유효하다.

			// 즉, 현재 예약된 회의실들 중에 가장 종료 시간이 빠른 회의실을 골라내고, 다음에 남아있는 회의중에 시작 시간이 가장 빠른 녀석을 가져와서 비교하는 것이다.
			if (interval.end <= intervalList.get(i).start) {
				interval.end = intervalList.get(i).end;
			} else {
				// 겹친다면 추가 회의실이 필요하다.
				pq.offer(intervalList.get(i));
			}

			pq.offer(interval);
		}

		return pq.size();
	}

	static class Interval {
		int start;
		int end;

		Interval(int s, int e) {
			this.start = s;
			this.end = e;
		}
	}

	public static void main(String[] args) {
		int [][] intervals = {
			{0, 30},{5, 10},{15, 20}
		};

		MeetingRooms2 mr2 = new MeetingRooms2();
		System.out.println(mr2.minMeetingRooms(intervals));
	}
}