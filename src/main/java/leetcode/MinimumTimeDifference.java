package leetcode;

import java.util.*;

public class MinimumTimeDifference {
	public int findMinDifference(List<String> timePoints) {
		int [] minutes = new int[timePoints.size()];

		for (int i = 0; i < timePoints.size(); i++) {
			String [] split = timePoints.get(i).split(":");
			minutes[i] = Integer.valueOf(split[0]) * 60 + Integer.valueOf(split[1]);
		}

		Arrays.sort(minutes);

		int min = Integer.MAX_VALUE;

		for (int i = 1; i < minutes.length; i++) {
			int diff = Math.abs(minutes[i-1] - minutes[i]);

			min = Math.min(min, diff);
			min = Math.min(min, 1440-diff);
		}

		int lastDiff = Math.abs(minutes[0] - minutes[minutes.length-1]);

		min = Math.min(min, lastDiff);
		min = Math.min(min, 1440-lastDiff);

		return min;
	}

	public static void main(String[] args) {
		List<String> times = new ArrayList<>();

		times.addAll(Arrays.asList("00:00","03:50","08:50"));

		MinimumTimeDifference minimumTimeDifference = new MinimumTimeDifference();
		System.out.println(minimumTimeDifference.findMinDifference(times));
	}
}
