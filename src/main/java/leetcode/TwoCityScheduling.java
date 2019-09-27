package leetcode;

import java.util.Arrays;

/**
 * @author neo82
 */
public class TwoCityScheduling {
	public int twoCitySchedCost(int[][] costs) {

		Arrays.sort(costs, (o1, o2) -> Math.abs(o2[0] - o2[1]) - Math.abs(o1[0] - o1[1]));

		int A = costs.length / 2;
		int B = A;

		int sum = 0;

		for (int [] cost : costs) {
			if (A == 0) {
				sum += cost[1];
				B--;
				continue;
			} else if (B == 0) {
				sum += cost[0];
				A--;
				continue;
			}

			if (cost[0] < cost[1]) {
				sum += cost[0];
				A--;
			} else {
				sum += cost[1];
				B--;
			}
		}

		return sum;
	}

	public static void main(String[] args) {
		int [][] costs = {
			{259, 770},
			{448, 54},
			{926, 667},
			{184, 139},
			{840, 118},
			{577, 469}
		};

		TwoCityScheduling twoCityScheduling = new TwoCityScheduling();

		System.out.println(twoCityScheduling.twoCitySchedCost(costs));

	}
}
