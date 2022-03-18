package leetcode;

public class FindPeakElement2 {
	public int findPeakElement(int[] nums) {
		if (nums.length == 1) {
			return 0;
		}

		return binarySearch(nums, 0, nums.length-1);
	}

	int binarySearch(int[] nums, int left, int right) {
		while (left <= right) {
			int mid = (left + right) / 2;

			if (isPeak(nums, mid)) {
				return mid;
			} else if (isIncreasing(nums, mid)) {
				left = mid+1;
			} else if (isDecreasing(nums, mid)) {
				right = mid-1;
			} else if (left < right && isFlat(nums, mid)) {
				int res = binarySearch(nums, left, mid);
				return res >= 0 ? res : binarySearch(nums, mid + 1, right);
			} else {
				// 오목한 경우
				left = mid + 1;
			}
		}

		return -1;
	}

	private boolean isDecreasing(int[] nums, int mid) {
		if (mid == 0) {
			return nums[mid] > nums[mid+1];
		}

		if (mid == nums.length - 1) {
			return nums[mid-1] > nums[mid];
		}

		return (nums[mid-1] > nums[mid] && nums[mid] >= nums[mid+1]) || (nums[mid-1] >= nums[mid] && nums[mid] > nums[mid+1]);
	}

	private boolean isIncreasing(int[] nums, int mid) {
		if (mid == 0) {
			return nums[mid] < nums[mid+1];
		}

		if (mid == nums.length - 1) {
			return nums[mid-1] < nums[mid];
		}

		return nums[mid-1] < nums[mid] && nums[mid] < nums[mid+1];
	}

	private boolean isFlat(int[] nums, int mid) {
		if (mid == 0) {
			return nums[mid] == nums[mid + 1];
		}

		if (mid == nums.length - 1) {
			return nums[mid - 1] == nums[mid];
		}

		return nums[mid - 1] == nums[mid] && nums[mid] == nums[mid + 1];
	}

	private boolean isPeak(int[] nums, int mid) {
		if (mid == 0) {
			return nums[mid] > nums[mid + 1];
		} else if (mid == nums.length - 1) {
			return nums[mid - 1] < nums[mid];
		}

		return nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1];
	}

	public static void main(String[] args) {
		int[] nums = {1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};

		System.out.println(nums.length-1);

		FindPeakElement2 findPeakElement2 = new FindPeakElement2();
		System.out.println(findPeakElement2.findPeakElement(nums));
	}
}
