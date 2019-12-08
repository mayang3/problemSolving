package leetcode;

import java.util.Arrays;

public class NextPermutation {
	public void nextPermutation(int[] nums) {
		int n = nums.length;
		int target = -1;

		for (int i=n - 1 ; i>0; i--) {
			if (nums[i-1] < nums[i]) {
				target = i-1;
				break;
			}
		}

		int l=0;
		int r=n-1;

		if (target != -1) {
			l = target+1;

			for (int i = n - 1; i > 0; i--) {
				if (nums[i] > nums[target]) {
					swap(nums, target, i);
					break;
				}
			}
		}

		swapAll(nums,l,r);
	}

	private void swapAll(int [] nums, int l, int r) {
		while (l < r) {
			swap(nums,l, r);
			l++;
			r--;
		}
	}

	private void swap(int [] nums, int a, int b) {
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}

	public static void main(String[] args) {
		int [] nums = {2,3,1};

		NextPermutation nextPermutation = new NextPermutation();
		nextPermutation.nextPermutation(nums);
		System.out.println(Arrays.toString(nums));
	}
}
