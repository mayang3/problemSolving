package leetcode;

public class ArrayNesting {
	public int arrayNesting(int[] nums) {
		int max = 0;

		for (int i = 0; i < nums.length; i++) {
			max = Math.max(max, solve(nums, i));
		}

		return max;
	}

	private int solve(int[] nums, int i) {
		if (i < 0 || nums[i] < 0) {
			return 0;
		}

		int val = nums[i];

		if (nums[i] == 0) { // for zero
			nums[i] = Integer.MIN_VALUE;
		} else {
			nums[i] = -nums[i];
		}

		return 1 + solve(nums, val);
	}

	public static void main(String[] args) {
		int [] nums = {5,4,0,3,1,6,2};

		ArrayNesting arrayNesting = new ArrayNesting();
		System.out.println(arrayNesting.arrayNesting(nums));
	}
}
