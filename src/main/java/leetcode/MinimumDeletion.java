package leetcode;

import java.util.PriorityQueue;

public class MinimumDeletion {
	public static void main(String[] args) {
		String s = "aabaa";
		int [] cost = {1,2,3,4,1};

		MinimumDeletion minimumDeletion = new MinimumDeletion();
		System.out.println(minimumDeletion.minCost(s, cost));
	}

	public int minCost(String s, int[] cost) {
		int total = 0;
		boolean found = false;
		int max = 0;

		for (int i=1; i < s.length() ; i++) {
			if (s.charAt(i-1) == s.charAt(i)) {
				found = true;
				total += cost[i-1];
				max = Math.max(max, cost[i-1]);

				if (i == s.length() - 1) {
					total += cost[i];
					max = Math.max(max, cost[i]);
					total -= max;
				}
			} else {
				if (found) {
					total += cost[i-1];
					max = Math.max(max, cost[i-1]);
					total -= max;
				}

				found = false;
				max = 0;
			}
		}

		return total;
	}
}
