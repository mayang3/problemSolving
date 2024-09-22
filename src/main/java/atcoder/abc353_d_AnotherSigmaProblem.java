package atcoder;

import java.util.Scanner;

/**
 * @author neo82
 */
public class abc353_d_AnotherSigmaProblem {
    static final int MOD = 998244353;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[] A = new int[N];
        int[] d = new int[11];

        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
            d[Integer.toString(A[i]).length()]++;
        }

        long[] pow = new long[11];
        pow[0] = 1;

        for (int i = 1; i < 11; i++) {
            pow[i] = (pow[i - 1] * 10) % MOD;
        }

        long ans = 0;

        for (int i = 0; i < N; i++) {
            ans = (ans + ((long) A[i] * i % MOD)) % MOD;
            d[Integer.toString(A[i]).length()]--;

            for (int k = 1; k < 11; k++) {
                ans = (ans + (pow[k] * A[i] % MOD) * d[k] % MOD) % MOD;
            }
        }

        System.out.println(ans);
    }
}
