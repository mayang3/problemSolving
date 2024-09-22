package atcoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author neo82
 */
public class abc340_c_DivideAndDivide {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        long N = scanner.nextLong();

        Map<Long, Long> dp = new HashMap<>();

        System.out.println(solve(dp, N));
    }

    private static long solve(Map<Long, Long> dp, long N) {

        if (dp.containsKey(N)) {
            return dp.get(N);
        }

        long num1 = N / 2;
        long num2 = (N + 1) / 2;
        long ans = N;

        if (num1 >= 2) {
            ans += solve(dp, num1);
        }

        if (num2 >= 2) {
            ans += solve(dp, num2);
        }

        dp.put(N, ans);

        return ans;
    }
}
