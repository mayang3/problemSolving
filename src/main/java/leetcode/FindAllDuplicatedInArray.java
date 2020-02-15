package leetcode;

import java.util.ArrayList;
import java.util.List;

public class FindAllDuplicatedInArray {
	public List<Integer> findDuplicates(int[] nums) {
		List<Integer> res = new ArrayList<>();

		for (int i = 0; i < nums.length; i++) {
			int idx = Math.abs(nums[i]) - 1;

			if (nums[idx] < 0) {
				res.add(Math.abs(nums[i]));
			} else {
				nums[idx] *= -1;
			}
		}

		return res;
	}

	public static void main(String[] args) {
		int [] nums = {4,3,2,7,8,2,3,1};

		FindAllDuplicatedInArray findAllDuplicatedInArray = new FindAllDuplicatedInArray();
		List<Integer> res = findAllDuplicatedInArray.findDuplicates(nums);

		System.out.println(res);
	}
}
