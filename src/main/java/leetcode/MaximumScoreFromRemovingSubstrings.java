package leetcode;

import java.util.Stack;

/**
 * @author neo82
 */
public class MaximumScoreFromRemovingSubstrings {

    public static void main(String[] args) {
        MaximumScoreFromRemovingSubstrings maximumScoreFromRemovingSubstrings = new MaximumScoreFromRemovingSubstrings();
        System.out.println(maximumScoreFromRemovingSubstrings.maximumGain("aabbaaxybbaabb", 5, 4));
    }


    public int maximumGain(String s, int x, int y) {
        Stack<Character> stack = new Stack<>();

        int ans = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (!stack.isEmpty() && stack.peek() == 'b' && c == 'a') {
                // baa pattern
                if (i == s.length() - 1 || s.charAt(i + 1) == 'a') {
                    stack.pop();
                    ans += y;
                } else {
                    if (y >= x) {
                        stack.pop();
                        ans += y;
                    } else {
                        stack.add(c);
                    }
                }
            } else if (!stack.isEmpty() && stack.peek() == 'a' && c == 'b') {
                if (i == s.length() - 1 || s.charAt(i + 1) == 'b') {
                    stack.pop();
                    ans += x;
                } else {
                    if (x >= y) {
                        stack.pop();
                        ans += x;
                    } else {
                        stack.add(c);
                    }
                }
            } else {
                stack.add(s.charAt(i));
            }
        }

        return ans;
    }

}
