package leetcode;

/**
 * @author neo82
 */
public class FindMinimumInRotatedSortedArray {
	public int findMin(int[] nums) {
		return solve(nums, 0, nums.length - 1);
	}

	private int solve(int[] nums, int l, int r) {
		if (l > r) {
			return 0;
		}

		if (l == r) {
			return nums[l];
		} else if (r - l == 1) {
			return Math.min(nums[l], nums[r]);
		}

		int m = (l + r) / 2;

		if (nums[l] < nums[m] && nums[m] < nums[r]) {
			return solve(nums, l, m);
		} else if (nums[m] < nums[l] && nums[m] < nums[r]) {
			return solve(nums, l, m);
		}

		return solve(nums, m+1, r);
	}

	public static void main(String[] args) {
		int [] nums = {4,5,6,7,0,1,2};

		FindMinimumInRotatedSortedArray findMinimumInRotatedSortedArray = new FindMinimumInRotatedSortedArray();
		int res = findMinimumInRotatedSortedArray.findMin(nums);

		System.out.println(res);
	}
}
