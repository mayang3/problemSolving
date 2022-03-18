package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class MaximumEarningsFromTaxi {
	public long maxTaxiEarnings(int n, int[][] rides) {
		TreeMap<Long, Long> dp = new TreeMap<>();
		dp.put(0L, 0L);

		// sort by end
		Arrays.sort(rides, Comparator.comparingInt(o -> o[1]));

		for (int i = 0; i < rides.length; i++) {
			int start = rides[i][0];
			int end = rides[i][1];
			int tip = rides[i][2];

			long cost = end - start + tip + dp.floorEntry((long)start).getValue();

			if (dp.lastEntry().getValue() < cost) {
				dp.put((long)end, cost);
			}
		}

		return dp.lastEntry().getValue();
	}

	public static void main(String[] args) {
		// 10
		//[]
		int n = 20;
		int [][] rides = {{9,10,2},{4,5,6},{6,8,1},{1,5,5},{4,9,5},{1,6,5},{4,8,3},{4,7,10},{1,9,8},{2,3,5}};

		MaximumEarningsFromTaxi maximumEarningsFromTaxi = new MaximumEarningsFromTaxi();
		System.out.println(maximumEarningsFromTaxi.maxTaxiEarnings(n, rides));
	}
}
