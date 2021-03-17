package leetcode;

import java.util.Arrays;
import java.util.TreeMap;

public class MaximumProfitInJobScheduling {
	public int jobScheduling(final int[] startTime, final int[] endTime, final int[] profit) {
		int n = startTime.length;
		int[][] jobs = new int[n][3];
		for (int i = 0; i < n; i++) {
			jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
		}

		// sort by endTime
		Arrays.sort(jobs, (a, b)->a[1] - b[1]);

		// key : endTime
		// value : profit
		TreeMap<Integer, Integer> dp = new TreeMap<>();
		dp.put(0, 0);

		for (int[] job : jobs) {
			// startTime 으로 값을 가져온다?
			int cur = dp.floorEntry(job[0]).getValue() + job[2];
			if (cur > dp.lastEntry().getValue())
				dp.put(job[1], cur);
		}
		return dp.lastEntry().getValue();
	}

	public static void main(String[] args) {
		int [] startTime = {1,2,3,4,6};
		int [] endTime = {3,5,10,6,9};
		int [] profit = {20,300,100,70,60};

		MaximumProfitInJobScheduling maximumProfitInJobScheduling = new MaximumProfitInJobScheduling();

		System.out.println(maximumProfitInJobScheduling.jobScheduling(startTime, endTime, profit));
	}
}
