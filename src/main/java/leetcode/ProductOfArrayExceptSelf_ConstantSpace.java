package leetcode;

import java.util.Arrays;

/**
 * @author neo82
 */
public class ProductOfArrayExceptSelf_ConstantSpace {
	public int[] productExceptSelf(int[] nums) {
		int n = nums.length;

		int [] output = new int[n];

		output[0] = 1;

		for (int i = 0; i < n-1; i++) {
			output[i+1] = output[i] * nums[i];
		}

		int r = 1;

		for (int i = n-1; i >= 0 ; i--) {
			output[i] = output[i] * r;
			r *= nums[i];
		}

		return output;
	}

	public static void main(String[] args) {
		int [] nums = {1,2,3,4};

		ProductOfArrayExceptSelf_ConstantSpace poae = new ProductOfArrayExceptSelf_ConstantSpace();
		int[] ret = poae.productExceptSelf(nums);

		System.out.println(Arrays.toString(ret));
	}
}
