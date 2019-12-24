package leetcode;

/**
 * @author neo82
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
	public int[] searchRange(int[] nums, int target) {
		int [] res = new int[2];

		res[0] = bs(nums, target, 0, nums.length-1, true);

		if (res[0] == -1) {
			res[1] = -1;
		} else {
			res[1] = bs(nums, target, 0, nums.length - 1, false);
		}

		return res;
	}

	private int bs(int[] nums, int target, int l, int r, boolean lower) {
		if (l > r) {
			return -1;
		} else if (l == r) {
			if (nums[l] == target) {
				return l;
			} else {
				return -1;
			}
		}

		int m = (l+r) / 2;

		if (nums[m] == target) {
			if (lower) {
				if (m == 0 || nums[m-1] != target) {
					return m;
				} else {
					return bs(nums, target, l, m, lower);
				}
			} else {
				if (m == nums.length-1 || nums[m+1] != target) {
					return m;
				} else {
					return bs(nums, target, m+1, r, lower);
				}
			}
		}

		if (nums[m] < target) {
			return bs(nums, target, m+1, r, lower);
		} else if (nums[m] > target) {
			return bs(nums, target, l, m, lower);
		}

		return -1;
	}
}
