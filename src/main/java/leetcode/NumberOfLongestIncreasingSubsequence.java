package leetcode;

import java.util.Arrays;

/**
 * @author neo82
 */
public class NumberOfLongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {
        int N = nums.length;
        int max = 1;
        int [] dp = new int[N];
        int [] count = new int[N];
        Arrays.fill(dp, 1);
        Arrays.fill(count, 1);


        for (int i=1; i<N; i++) {
            for (int j=0; j<i; j++) {
                if (nums[j] < nums[i]) {
                    if (dp[i] < dp[j] + 1) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];
                    } else if (dp[i] == dp[j] + 1) {
                        count[i] += count[j];
                    }
                }

                max = Math.max(max, dp[i]);
            }
        }

        int ans = 0;

        for (int i=0; i<N; i++) {
            if (dp[i] == max) {
                ans += count[i];
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int [] nums = {1,2,2,2,3,1};

        NumberOfLongestIncreasingSubsequence numberOfLongestIncreasingSubsequence = new NumberOfLongestIncreasingSubsequence();
        System.out.println(numberOfLongestIncreasingSubsequence.findNumberOfLIS(nums));
    }
}
