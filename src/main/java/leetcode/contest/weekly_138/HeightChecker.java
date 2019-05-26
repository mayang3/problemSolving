package leetcode.contest.weekly_138;

import java.util.Arrays;

/**
 * @author neo82
 */
public class HeightChecker {
	public static void main(String[] args) {

		int [] heights = {
			1,1,4,2,1,3
		};

		System.out.println(heightChecker(heights));
	}

	public static int heightChecker(int[] heights) {
		if (heights == null || heights.length == 0) {
			return 0;
		}

		int[] copyHeights = Arrays.copyOf(heights, heights.length);

		Arrays.sort(copyHeights);

		int cnt =0;

		for (int i = 0; i < heights.length; i++) {
			if (heights[i] != copyHeights[i]) {
				cnt++;
			}
		}

		return cnt;
	}
}
