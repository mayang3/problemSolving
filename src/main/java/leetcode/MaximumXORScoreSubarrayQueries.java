package leetcode;

import java.util.Arrays;

/**
 * @author neo82
 */
public class MaximumXORScoreSubarrayQueries {
    public static void main(String[] args) {
        int[] nums = {2, 8, 4, 32, 16, 1};
        int[][] q = {{0, 2}, {1, 4}, {0, 5}};

        MaximumXORScoreSubarrayQueries maximumXORScoreSubarrayQueries = new MaximumXORScoreSubarrayQueries();
        System.out.println(Arrays.toString(maximumXORScoreSubarrayQueries.maximumSubarrayXor(nums, q)));
    }

    /**
     * 처음에는 segment tree 로 시도했다. but 다음과 같은 이유로 segment tree 는 포기. dp 로 품
     * 그러나 XOR 연산에서는 구간을 두 개로 나누어 처리할 수 없습니다.
     * 구간 [l, r]에 대해 두 부분 [l, mid]와 [mid+1, r]로 나눌 때, 이 두 부분의 XOR 값을 독립적으로 구하고 이를 결합하여 전체 구간의 XOR 값을 얻을 수 없습니다.
     * 이는 XOR 연산이 구간 간의 의존성을 가지기 때문입니다.
     * 예를 들어, a ^ b ^ c와 b ^ c ^ d의 XOR 값을 구할 때, 중간에 있는 b ^ c 부분이 두 구간 모두에 걸쳐 있기 때문에,
     * 단순히 두 구간의 XOR 값을 결합하는 방식으로 전체 구간의 XOR 값을 구할 수 없습니다.
     *
     *
     * @param nums
     * @param queries
     * @return
     */
    public int[] maximumSubarrayXor(int[] nums, int[][] queries) {
        int N = nums.length;

        int[][] dp = new int[N][N];
        int[][] xor = new int[N][N];


        for (int i = 0; i < N; i++) {
            xor[i][i] = nums[i];
            dp[i][i] = nums[i];
        }

        for (int len = 2; len <= N; len++) {
            for (int left = 0; left < N - len + 1; left++) {
                int right = left + len - 1;

                xor[left][right] = xor[left][right - 1] ^ xor[left + 1][right];
                dp[left][right] = Math.max(Math.max(dp[left][right - 1], dp[left + 1][right]), xor[left][right]);
            }
        }


        int [] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            ans[i] = dp[queries[i][0]][queries[i][1]];
        }

        return ans;
    }
}
