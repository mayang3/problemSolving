package leetcode;

import java.util.Arrays;

/**
 * @author baejunbeom
 */
public class TwoSum2 {

	public int[] twoSum(int[] numbers, int target) {
		int left = 0;
		int right = numbers.length - 1;

		while (left <= right) {
			if (numbers[left] + numbers[right] == target) {
				return new int[] {left + 1, right + 1};
			}

			if (numbers[left] + numbers[right] < target) {
				left++;
			} else {
				right--;
			}
		}


		return null;
	}

	public static void main(String[] args) {
		TwoSum2 twoSum2 = new TwoSum2();
		int[] ints = twoSum2.twoSum(new int[] {2, 7, 11, 15}, 9);
//				int[] ints = twoSum2.twoSum(new int[] {1, 4, 7, 9, 23, 55, 37}, 31);

		System.out.println(Arrays.toString(ints));

	}

}
