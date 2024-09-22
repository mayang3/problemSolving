package leetcode;

import java.util.Arrays;

/**
 * @author neo82
 */
public class MinimumIncrementToMakeArrayUnique {
    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);

        int ans = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                int newNum = nums[i-1] + 1;
                ans += (newNum - nums[i]);
                nums[i] = newNum;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2};

        MinimumIncrementToMakeArrayUnique minimumIncrementToMakeArrayUnique = new MinimumIncrementToMakeArrayUnique();
        System.out.println(minimumIncrementToMakeArrayUnique.minIncrementForUnique(nums));
    }
}
