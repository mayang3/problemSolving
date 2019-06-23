package leetcode;

import java.util.Arrays;

/**
 *
 * the key point is to find
 *
 * product array of elements to the left and
 * product array of elements to the right
 *
 * @author neo82
 */
public class ProductOfArrayExceptSelf {

	public int[] productExceptSelf(int[] nums) {
		int n = nums.length;

		int [] leftProduct = new int[n];
		int [] rightProduct = new int[n];

		leftProduct[0] = 1;
		rightProduct[n-1] = 1;

		for (int i = 0; i < n - 1; i++) {
			leftProduct[i+1] = leftProduct[i] * nums[i];
		}

		for (int i = n-1; i > 0; i--) {
			rightProduct[i-1] = rightProduct[i] * nums[i];
		}

		int [] output = new int[n];

		for (int i = 0; i < n; i++) {
			output[i] = leftProduct[i] * rightProduct[i];
		}

		return output;
	}

	public static void main(String[] args) {
		int [] nums = {1,2,3,4};

		ProductOfArrayExceptSelf poae = new ProductOfArrayExceptSelf();
		int[] ret = poae.productExceptSelf(nums);

		System.out.println(Arrays.toString(ret));
	}
}
