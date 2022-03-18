package leetcode.contest.weekly_283;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppendKIntegersWithMinimalSum {
	public long minimalKSum(int[] nums2, int k) {

		List<Integer> nums = new ArrayList<>();

		for (int i = 0; i < nums2.length; i++) {
			nums.add(nums2[i]);
		}

		nums.add((int)(1e9)+1);

		Collections.sort(nums);

		int last = 0;
		long sum = 0;

		for (int i = 0; i < nums.size(); i++) {
			if (k == 0) {
				break;
			}

			long dist = (long)nums.get(i) - last - 1;

			if (dist > 0) {
				// k가 dist 와 같거나 그 이상으로 남은 경우
				if (k >= dist) {
					sum += ((dist * (last + 1L + nums.get(i)-1)) / 2L);
					k-=dist;
				} else {
					// k 가 dist 보다 적은 경우
					return sum + ((k * (last + 1L + last + k))) / 2L;
				}
			}

			last = nums.get(i);
		}

		return sum;
	}

	public static void main(String[] args) {
		int [] nums = {5,6};
		int k = 6;

		AppendKIntegersWithMinimalSum appendKIntegersWithMinimalSum = new AppendKIntegersWithMinimalSum();
		System.out.println(appendKIntegersWithMinimalSum.minimalKSum(nums, k));
	}
}
