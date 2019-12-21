package leetcode;

import java.util.Arrays;

public class SortColors {
	public void sortColors(int[] nums) {
		int [] count = new int[3];

		for (int i = 0; i < nums.length; i++) {
			count[nums[i]]++;
		}

		int j = 0;

		for (int i = 0; i < 3; i++) {
			int end = j + count[i];

			for (; j < end; j++) {
				nums[j] = i;
			}
		}
	}

	public static void main(String[] args) {
		int [] nums = {2,0,2,1,1,0,0,0,0,0};

		SortColors sc = new SortColors();
		sc.sortColors(nums);

		System.out.println(Arrays.toString(nums));
	}
}
