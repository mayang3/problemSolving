package leetcode;

/**
 * @author neo82
 */
public class FindPeakElement {
	public int findPeakElement(int[] nums) {
		return solve(0, nums.length-1, nums);
	}

	private int solve(int start, int end, int[] nums) {
		if (start == end) {
			return start;
		}

		int m = (start + end) / 2;

		if (m == 0 && nums[m] > nums[m+1]) {
			return m;
		} else if (m == nums.length - 1 && nums[m-1] < nums[m]) {
			return m;
		}

		if (nums[m] < nums[m+1]) {
			return solve(m+1, end, nums);
		} else {
			return solve(start, m, nums);
		}
	}

	public static void main(String[] args) {
		FindPeakElement findPeakElement = new FindPeakElement();

		int [] nums = {1,2,1,3,5,6,4};

		System.out.println(findPeakElement.findPeakElement(nums));
	}
}
