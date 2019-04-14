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
	public static class Interval {
		int start;
		int end;

		Interval() {
			start = 0;
			end = 0;

		}

		Interval(int s, int e) {
			start = s;
			end = e;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("Interval{");
			sb.append("start=").append(start);
			sb.append(", end=").append(end);
			sb.append('}');
			return sb.toString();
		}
	}

	public List<Interval> merge(List<Interval> intervals) {

		LinkedList<Interval> resultList = new LinkedList<>();

		if (intervals == null || intervals.isEmpty()) {
			return resultList;
		}

		Collections.sort(intervals, (o1, o2) -> {
			if (o1.start == o2.start) {
				return 0;
			}

			return o1.start < o2.start ? -1 : 1;
		});

		for (Interval interval : intervals) {

			if (resultList.isEmpty()) {
				resultList.add(interval);
				continue;
			}

			Interval last = resultList.getLast();

			if (last.end < interval.start) {
				resultList.addLast(interval);
			} else if (last.start <= interval.start && last.end >= interval.start && last.end <= interval.end) {
				last.end = interval.end;
			}

		}


		return resultList;
	}

	public static void main(String[] args) {

		MergeInterval mergeInterval = new MergeInterval();
		List<Interval> merge = mergeInterval.merge(makeIntervalList());

		System.out.println(merge);
	}

	private static List<Interval> makeIntervalList() {

		List<Interval> intervalList = new ArrayList<>();

		intervalList.add(new Interval(1,3));
		intervalList.add(new Interval(2,6));
		intervalList.add(new Interval(8,10));
		intervalList.add(new Interval(15,18));

		return intervalList;
	}

}
