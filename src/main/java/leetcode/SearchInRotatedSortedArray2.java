package leetcode;

public class SearchInRotatedSortedArray2 {
	public int search(int[] nums, int target) {
		int findRotateIndex = getRotateIndex(nums);
		int right = nums.length - 1;

		if (findRotateIndex == 0) {
			return binarySearch(nums, target, 0, right);
		} else if (nums[findRotateIndex] <= target && target <= nums[right]) {
			return binarySearch(nums, target, findRotateIndex, right);
		} else {
			return binarySearch(nums, target, 0, findRotateIndex-1);
		}
	}

	private int binarySearch(int [] nums, int target, int left, int right) {
		while (left <= right) {
			int mid = (left + right) / 2;

			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}

		return -1;
	}

	private int getRotateIndex(int[] nums) {
		int left = 0;
		int right = nums.length - 1;

		if (nums[left] < nums[right] || nums.length == 1) {
			return 0;
		}

		while (left <= right) {
			int mid = (left + right) / 2;

			if (nums[mid] > nums[mid + 1]) {
				return mid + 1;
			} else {
				if (nums[mid] < nums[left]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}
			}
		}

		return 0;
	}

	public static void main(String[] args) {
		int [] nums = {1};
		int target = 0;

		SearchInRotatedSortedArray2 searchInRotatedSortedArray2 = new SearchInRotatedSortedArray2();
		System.out.println(searchInRotatedSortedArray2.search(nums, target));
	}
}
