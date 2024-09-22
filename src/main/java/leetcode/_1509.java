package leetcode;

import java.util.Arrays;

/**
 * @author neo82
 */
public class _1509 {
    public int minDifference(int[] nums) {
        int N = nums.length;

        if (N <= 4) {
            return 0;
        }

        Arrays.sort(nums);


        int diff = Integer.MAX_VALUE;


        for (int left = 1, right = 3; right < N - 1; left++, right++) {
            diff = Math.min(diff, nums[N - 1] - nums[0]);
        }

        int left = 0;
        int right = N - 3 - 1;

        for (int i = 0; i < 4; i++) {
            diff = Math.min(diff, nums[right++] - nums[left++]);
        }

        return diff;
    }

    public static void main(String[] args) {
        _1509 test = new _1509();

        int[] nums = {20, 66, 68, 57, 45, 18, 42, 34, 37, 58};

        System.out.println(test.minDifference(nums));
    }
}
