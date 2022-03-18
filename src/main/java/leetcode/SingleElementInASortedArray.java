package leetcode;

public class SingleElementInASortedArray {
	public int singleNonDuplicate(int[] nums) {
		int left = 0;
		int right = nums.length - 1;

		while (left < right) {
			int mid = (left+right) / 2;

			Pair pair = getPair(nums, mid);

			if (pair.p1 == pair.p2) {
				return nums[pair.p1];
			}

			int leftLen = pair.p1 - left;

			if (leftLen % 2 == 0) {
				left = pair.p2 + 1;
			} else {
				right = pair.p1 - 1;
			}
		}

		return nums[left];
	}

	private Pair getPair(int[] nums, int mid) {
		if (mid == 0) {
			return new Pair(0, 1);
		}

		if (mid == nums.length - 1) {
			return new Pair(nums.length-1, nums.length-2);
		}

		if (nums[mid-1] == nums[mid]) {
			return new Pair(mid-1, mid);
		}

		if (nums[mid] == nums[mid+1]) {
			return new Pair(mid, mid+1);
		}

		return new Pair(mid, mid);
	}

	static class Pair {
		int p1;
		int p2;

		public Pair(int p1, int p2) {
			this.p1 = p1;
			this.p2 = p2;
		}
	}

	public static void main(String[] args) {
		int [] nums = {1,1,2,3,3,4,4,8,8};

		SingleElementInASortedArray singleElementInASortedArray = new SingleElementInASortedArray();
		System.out.println(singleElementInASortedArray.singleNonDuplicate(nums));
	}
}
