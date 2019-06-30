package leetcode;

import java.util.Arrays;

/**
 * @author neo82
 */
public class RotateArray {
	// method 1
//	public void rotate(int[] nums, int k) {
//		int n = nums.length;
//
//		for (int i = 0; i < k; i++) {
//			for (int j = n-1; j > 0 ; j--) {
//				int t = nums[j];
//				nums[j] = nums[j-1];
//				nums[j-1] = t;
//			}
//		}
//
//		System.out.println(Arrays.toString(nums));
//	}

	public void rotate(int [] nums, int k) {
		k %= nums.length;

		reverse(nums, 0,nums.length-1);
		reverse(nums, 0, k-1);
		reverse(nums, k, nums.length-1);
	}

	private void reverse(int [] nums, int start, int end) {
		while (start < end) {
			int t = nums[start];
			nums[start] = nums[end];
			nums[end] = t;

			start++;
			end--;
		}
	}

	public static void main(String[] args) {
		int [] nums = {1,2,3,4,5,6,7};
		int k = 3;

		RotateArray rotateArray = new RotateArray();
		rotateArray.rotate(nums, k);

		System.out.println(Arrays.toString(nums));
	}
}
