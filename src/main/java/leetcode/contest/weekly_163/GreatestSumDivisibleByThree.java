package leetcode.contest.weekly_163;

public class GreatestSumDivisibleByThree {
	public int maxSumDivThree(int[] nums) {
		int sum = 0;
		int leftOne = 10001;
		int leftTwo = 10001;

		for (int i = 0; i < nums.length; i++) {
			int val = nums[i];

			sum += val;

			if (val % 3 == 1) {
				leftTwo = Math.min(leftTwo, leftOne + val);
				leftOne = Math.min(leftOne, val);

			} else if (val % 3 == 2) {
				leftOne = Math.min(leftOne, leftTwo + val);
				leftTwo = Math.min(leftTwo, val);
			}
		}

		if (sum % 3 == 0) {
			return sum;
		} else if (sum % 3 == 1) {
			return sum - leftOne;
		} else if (sum % 3 == 2) {
			return sum - leftTwo;
		}

		return -1;
	}

	public static void main(String[] args) {
		int [] nums = {3,6,5,1,8};

		GreatestSumDivisibleByThree greatestSumDivisibleByThree = new GreatestSumDivisibleByThree();
		int ret = greatestSumDivisibleByThree.maxSumDivThree(nums);

		System.out.println(ret);
	}
}
