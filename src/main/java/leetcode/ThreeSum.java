package leetcode;

import java.util.*;

public class ThreeSum {
	// [-4, -1, -1, 0, 1, 2]
	public List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);

		List<List<Integer>> resList = new ArrayList<>();

		for (int i = 0; i < nums.length-2; i++) {
			if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {

				int lo = i + 1;
				int hi = nums.length - 1;
				int target = -nums[i];

				while (lo < hi) {
					if (nums[lo] + nums[hi] == target) {
						resList.add(Arrays.asList(nums[i], nums[lo], nums[hi]));

						while (lo < hi && nums[lo] == nums[lo+1]) {
							lo++;
						}

						while (lo < hi && nums[hi] == nums[hi-1]) {
							hi--;
						}

						// 아래의 lo 와 hi 는 무조건 함께 변경되어야 한다.
						// 그 이유는 둘중 하나만 변경되고 나머지는 변경되지 않는 경우에 있어서 target 을 맞출 수 있는 경우는 없다.

						// 이게 무슨 말인고 하니... target 이 10 라고 할때, 1+2+3+4 은 target 을 만족하는데, 이중 한가지 숫자가 "바뀌는" 경우에는 무조건 target 을 만족할 수 없다.
						// (1 이라도 틀려지면, target 에 일치하는 숫자가 틀려지게 된다.)
						lo++;
						hi--;
					} else if (nums[lo] + nums[hi] < target) {
						lo++;
					} else {
						hi--;
					}
				}
			}
		}

		return resList;
	}

	public static void main(String[] args) {
		int [] nums = {0,0,0};

		ThreeSum _ThreeSum = new ThreeSum();
		List<List<Integer>> lists = _ThreeSum.threeSum(nums);

		System.out.println(lists);
	}
}
