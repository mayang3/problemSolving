package leetcode;

import java.util.ArrayList;
import java.util.List;

public class SummaryRange {
	public List<String> summaryRanges(int[] nums) {
		int start = nums[0];
		int cur = nums[0];
		int end = nums[0];

		List<String> ret = new ArrayList<>();

		for (int i = 1; i < nums.length; i++) {
			cur++;

			if (nums[i] == cur) {
				end = cur;
				if (i == nums.length -1) {
					if (start == end) {
						ret.add(start + "");
					} else {
						ret.add(start + "->" + end);
					}
				}

			} else {
				if (start == end) {
					ret.add(start + "");
				} else {
					ret.add(start + "->" + end);
				}

				start = cur = end = nums[i];

				if (i == nums.length - 1) {
					ret.add(start + "");
				}
			}
		}

		return ret;
	}

	public static void main(String[] args) {
		int [] nums = {0,1,2,4,5,7};

		SummaryRange summaryRange = new SummaryRange();
		List<String> ret = summaryRange.summaryRanges(nums);

		System.out.println(ret);
	}
}
