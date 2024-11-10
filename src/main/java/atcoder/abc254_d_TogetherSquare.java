package atcoder;

import java.util.Scanner;

/**
 * @author neo82
 */
public class abc254_d_TogetherSquare {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int count = 0;

        for (long num = 1; num <= N; num++) {
            count += getFactorCount(num * num, N);
        }

        System.out.println(count);
    }

    private static int getFactorCount(long s, int N) {
        int cnt = 0;
        for (int one = 1; one <= (int) Math.sqrt(s); one++) {
            if (s % one == 0) {
                int another = (int) (s / one);

                if (one <= N && another <= N) {
                    cnt++;
                }
            }
        }

        return cnt * 2 - 1;
    }
}
