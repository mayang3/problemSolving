package leetcode;

import java.util.LinkedList;

public class NonDecreasingArray {

	public static void main(String[] args) {
		int [] num = {4,2,3};

		NonDecreasingArray nonDecreasingArray = new NonDecreasingArray();

		System.out.println(nonDecreasingArray.checkPossibility(num));

	}
	public boolean checkPossibility(int[] nums) {
		int n = nums.length;
		int count = 0;

		for (int i = 1; i < n; i++) {
			if (nums[i-1] > nums[i]) {
				boolean check = false;

				// i-1 을 바꿀 수 없는 경우
				if ((i-1 > 0 && nums[i-2] <= nums[i]) || i-1 == 0) {
					check |= true;
				}

				// i 를 바꿀 수 없는 경우
				if ((i < n - 1 && nums[i-1] <= nums[i+1]) || i == n-1) {
					check |= true;
				}

				if (!check) return false;

				count++;
			}
		}

		return count > 1 ? false : true;
	}
}

