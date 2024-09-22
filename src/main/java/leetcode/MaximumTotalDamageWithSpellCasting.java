package leetcode;

import java.util.Arrays;

/**
 * @author neo82
 */
public class MaximumTotalDamageWithSpellCasting {

    // [1,3,6,7]
    public long maximumTotalDamage(int[] power) {
        int N = power.length;
        long[] dp = new long[N];

        Arrays.sort(power);

        long ans;
        long max = 0;

        ans = dp[0] = power[0];

        for (int i = 1, j = 0; i < N; i++) {

            if (power[i] == power[i - 1]) {
                ans = Math.max(ans, (dp[i] = dp[i - 1] + power[i]));
            } else {
                while (power[j] + 2 < power[i]) {
                    max = Math.max(max, dp[j++]);
                }

                ans = Math.max(ans, (dp[i] = power[i] + max));
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] power = {1,3,6,7};

        MaximumTotalDamageWithSpellCasting maximumTotalDamageWithSpellCasting = new MaximumTotalDamageWithSpellCasting();

        System.out.println(maximumTotalDamageWithSpellCasting.maximumTotalDamage(power));
    }
}
