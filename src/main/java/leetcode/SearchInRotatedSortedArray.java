package leetcode;

public class SearchInRotatedSortedArray {
	public int search(int[] nums, int target) {
		return solve(nums, target, 0, nums.length - 1);
	}

	private int solve(int[] nums, int target, int l, int r) {
		if (l > r) {
			return -1;
		} else if (l == r) {
			return nums[l] == target ? l : -1;
		}

		int m = (l+r) / 2;

		if (nums[m] == target) {
			return m;
		}

		int leftVal = nums[l];
		int midVal = nums[m];
		int rightVal = nums[r];

		// 왼쪽과 오른쪽 모두가 오름차순인 경우
		if (leftVal <= midVal && midVal <= rightVal) {
			if (target <= midVal) {
				return solve(nums, target, l, m);
			} else {
				return solve(nums, target, m+1, r);
			}
		} else if (leftVal <= midVal && midVal > rightVal) {
			// 왼쪽 구간이 오름차순이고, 오른쪽 구간이 오름차순이 아닌 경우
			if (leftVal <= target && target <= midVal) {
				return solve(nums,target,l,m);
			} else {
				return solve(nums,target,m+1,r);
			}
		} else if (leftVal > midVal && midVal < rightVal) {
			// 왼쪽 구간이 오름차순이 아니고, 오른쪽 구간이 오름차순인 경우
			if (midVal < target && target <= rightVal) {
				return solve(nums,target,m+1,r);
			} else {
				return solve(nums,target,l,m);
			}
		}

		return -1;
	}

	public static void main(String[] args) {
		int [] nums= {3, 1};
		int target = 1;

		SearchInRotatedSortedArray searchInRotatedSortedArray = new SearchInRotatedSortedArray();
		int res = searchInRotatedSortedArray.search(nums, target);

		System.out.println(res);
	}
}
