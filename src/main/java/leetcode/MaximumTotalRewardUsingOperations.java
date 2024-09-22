package leetcode;

import java.util.Arrays;

/**
 * @author neo82
 */
public class MaximumTotalRewardUsingOperations {

    public int maxTotalReward(int[] rewardValues) {
        Arrays.sort(rewardValues);

        int N = rewardValues.length;

        Integer [][] dp = new Integer[N+1][4001];


        return solve(dp, rewardValues, 0, 0, N);
    }

    private int solve(Integer[][] dp, int[] rewardValues, int i, int r, int N) {
        if (i == N) {
            return r;
        }

        if (dp[i][r] != null) {
            return dp[i][r];
        }

        int include = 0;

        if (rewardValues[i] > r) {
            include = solve(dp, rewardValues, i+1, r + rewardValues[i], N);
        }

        int exclude = solve(dp, rewardValues, i+1, r, N);

        return dp[i][r] = Math.max(include, exclude);
    }

    public static void main(String[] args) {
        int [] rewardValues = {1,6,4,3,2};

        MaximumTotalRewardUsingOperations maximumTotalRewardUsingOperations = new MaximumTotalRewardUsingOperations();
        System.out.println(maximumTotalRewardUsingOperations.maxTotalReward(rewardValues));
    }
}
