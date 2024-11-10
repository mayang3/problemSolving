package atcoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author neo82
 */
public class abc317_d_President {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int totalSeats = 0;
        int occupiedSeats = 0;

        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int X = scanner.nextInt();
            int Y = scanner.nextInt();
            int Z = scanner.nextInt();

            int needed = (int) (((long) X + Y) / 2 + 1) - X;

            if (X > Y) {
                occupiedSeats += Z;
            } else {
                list.add(new int[]{needed, Z});
            }

            totalSeats += Z;
        }

        int majorityOfSeats = totalSeats / 2 + 1;

        majorityOfSeats -= occupiedSeats;

        if (majorityOfSeats <= 0) {
            System.out.println(0);
            return;
        }

        Long[][] dp = new Long[list.size()][majorityOfSeats + 1];

        System.out.println(solve(dp, list, majorityOfSeats, 0));
    }

    static long solve(Long[][] dp, List<int[]> list, int majorityOfSeats, int i) {
        if (i >= list.size()) {
            if (majorityOfSeats > 0) {
                return Long.MAX_VALUE;
            } else {
                return 0;
            }
        }

        if (majorityOfSeats >= 0 && dp[i][majorityOfSeats] != null) {
            return dp[i][majorityOfSeats];
        }

        int swap = list.get(i)[0];
        int seatsInDistrict = list.get(i)[1];

        long ans = Long.MAX_VALUE;

        if (majorityOfSeats > 0) {
            ans = solve(dp, list, majorityOfSeats - seatsInDistrict, i + 1);

            if (ans != Long.MAX_VALUE) ans += swap;
        }

        ans = Math.min(ans, solve(dp, list, majorityOfSeats, i + 1));

        if (majorityOfSeats >= 0) dp[i][majorityOfSeats] = ans;

        return ans;
    }
}
