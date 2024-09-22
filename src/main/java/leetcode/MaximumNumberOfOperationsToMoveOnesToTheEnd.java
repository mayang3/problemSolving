package leetcode;

import java.util.LinkedList;

/**
 * @author neo82
 */
public class MaximumNumberOfOperationsToMoveOnesToTheEnd {
    public int maxOperations(String s) {
        int ones = 0;
        int ans = 0;
        int N = s.length();


        for (int i = 0; i < N; i++) {
            ones += s.charAt(i) - '0';

            if (i > 0 && s.charAt(i) < s.charAt(i-1)) {
                ans += ones;
            }
        }


        return ans;
    }

    public static void main(String[] args) {
        MaximumNumberOfOperationsToMoveOnesToTheEnd maximumNumberOfOperationsToMoveOnesToTheEnd = new MaximumNumberOfOperationsToMoveOnesToTheEnd();
        System.out.println(maximumNumberOfOperationsToMoveOnesToTheEnd.maxOperations("1010"));
    }
}
