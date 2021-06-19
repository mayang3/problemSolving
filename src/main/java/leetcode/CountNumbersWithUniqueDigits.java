package leetcode;

import java.util.HashSet;
import java.util.Set;

public class CountNumbersWithUniqueDigits {
    public int countNumbersWithUniqueDigits(int n) {
        int [] dp = new int[11];

        int base = 9;

        dp[1] = 10;
        dp[2] = base * base + dp[1];

        for (int i = 3; i <= n ; i++) {
            dp[i] = dp[i-1] + base * getMultiple(11-i);
        }

        return dp[n];
    }

    private int getMultiple(int num) {
        if (num == 9) {
            return num;
        }

        return num * getMultiple(num + 1);
    }

    public int backtrack(int n) {
        int cnt = 0;
        int nonCnt = 0;

        for (int i = 0; i <= Math.pow(10, n); i++) {
            String s = String.valueOf(i);
            Set<Character> set = new HashSet<>();
            boolean success = true;

            for (int j = 0; j < s.length(); j++) {
                if (set.contains(s.charAt(j))) {
                    success = false;
                    nonCnt++;
                    break;
                } else {
                    set.add(s.charAt(j));
                }
            }

            if (success) {
                cnt++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        CountNumbersWithUniqueDigits countNumbersWithUniqueDigits = new CountNumbersWithUniqueDigits();
        System.out.println(countNumbersWithUniqueDigits.countNumbersWithUniqueDigits(10));
    }
}