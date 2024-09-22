package leetcode;

/**
 * @author neo82
 */
public class FindTheCountOfMonotonicPairsI {
    public static void main(String[] args) {
        int[] nums = {2, 3, 2};

        FindTheCountOfMonotonicPairsI findTheCountOfMonotonicPairsI = new FindTheCountOfMonotonicPairsI();
        System.out.println(findTheCountOfMonotonicPairsI.countOfPairs(nums));
    }

    private static final int MOD = 1000000007;

    public int countOfPairs(int[] nums) {
        Integer[][][] dp = new Integer[nums.length][51][51];

        // i=0 의 위치에서 실행을 보장하기 위해서 각 값의 최소/최대인 0,50 을 넘겨줄 필요가 있다.
        return solve(dp, nums, 0, 0, 50);
    }


    int solve(Integer[][][] dp, int[] nums, int i, int prevX, int prevY) {
        if (i >= nums.length) {
            return 1;
        }

        if (dp[i][prevX][prevY] != null) {
            return dp[i][prevX][prevY];
        }

        int ans = 0;

        for (int j = 0; j <= nums[i]; j++) {
            int x = j;
            int y = nums[i] - j;

            if (x >= prevX && y <= prevY) {
                ans = (ans + solve(dp, nums, i + 1, x, y)) % MOD;
            }
        }


        return dp[i][prevX][prevY] = ans;
    }
}
