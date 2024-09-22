package leetcode;

/**
 * @author neo82
 */
public class  LongestPalindromicSubstringTemp {
    public static void main(String[] args) {
        LongestPalindromicSubstringTemp longestPalindromicSubstringTemp = new LongestPalindromicSubstringTemp();
        System.out.println(longestPalindromicSubstringTemp.longestPalindrome("babad"));
    }

    public String longestPalindrome(String s) {
        int N = s.length();
        boolean [][] dp = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            dp[i][i] = true;
        }

        int maxLength = 1;
        int ansLeft = 0;
        int ansRight = 0;

        for (int i = 0; i < N - 1; i++) {
            int j = i + 1;

            if (s.charAt(i) == s.charAt(j)) {
                dp[i][j] = true;
                maxLength = 2;
                ansLeft = i;
                ansRight = j;
            }
        }

        for (int len = 3; len <= N; len++) {
            for (int left = 0; left < N - len + 1; left++) {
                int right = left + len - 1;

                if (dp[left+1][right-1] && s.charAt(left) == s.charAt(right)) {
                    dp[left][right] = true;

                    if (maxLength < right - left + 1) {
                        maxLength = right - left + 1;
                        ansLeft = left;
                        ansRight = right;
                    }
                }
            }
        }

        return s.substring(ansLeft, ansRight + 1);
    }
}
