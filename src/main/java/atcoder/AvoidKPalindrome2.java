package atcoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author neo82
 */
public class AvoidKPalindrome2 {

    static long ans = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int K = scanner.nextInt();

        String s = scanner.next();

        char[] charArray = s.toCharArray();

        Arrays.sort(charArray);

        solve(new StringBuilder(), new HashSet<>(), 0, new String(charArray), N, K);

        System.out.println(ans);
    }

    private static void solve(StringBuilder sb, Set<Integer> visited, int i, String s, int N, int K) {
        if (i >= s.length()) {
            if (sb.length() == N) {
                if (!hasPalindrome(sb, K)) {
                    ans++;
                }
            }

            return;
        }

        for (int j = 0; j < s.length(); j++) {

            if (visited.contains(j)) {
                continue;
            }

            if (j > 0 && s.charAt(j) == s.charAt(j - 1) && !visited.contains(j - 1)) {
                continue;
            }

            visited.add(j);
            sb.append(s.charAt(j));
            solve(sb, visited, i + 1, s, N, K);
            sb.deleteCharAt(sb.length() - 1);
            visited.remove(j);
        }
    }

    private static boolean hasPalindrome(StringBuilder sb, int K) {
        for (int i = 0; i < sb.length() - K + 1; i++) {

            boolean flag = true;
            int left = i;
            int right = left + K - 1;

            while (left <= right) {
                if (sb.charAt(left) != sb.charAt(right)) {
                    flag = false;
                    break;
                }

                left++;
                right--;
            }

            if (flag) {
                return true;
            }
        }

        return false;
    }
}
