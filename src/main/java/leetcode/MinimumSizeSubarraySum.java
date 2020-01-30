package leetcode;

public class MinimumSizeSubarraySum {

	public int minSubArrayLen(int s, int[] nums) {
		int l = 0;
		int r = 0;
		int sum = 0;
		int min = Integer.MAX_VALUE;

		while (r < nums.length) {
			sum += nums[r++];

			// 하나씩 sum 에 더해가면서, sum 이 s 를 넘었다면,
			// 다시 왼쪽 인덱스를 최대한 당긴다.
			while (sum >= s) {
				min = Math.min(min, r-l);
				sum -= nums[l++];
			}
		}

		return min == Integer.MAX_VALUE ? 0 : min;
	}

	public static void main(String[] args) {
		int s = 15;
		int [] nums = {5,1,3,5,10,7,4,9,2,8};

		MinimumSizeSubarraySum minimumSizeSubarraySum = new MinimumSizeSubarraySum();
		int res = minimumSizeSubarraySum.minSubArrayLen(s, nums);

		System.out.println(res);
	}
}
