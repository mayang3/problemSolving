package atcoder;

import java.util.Scanner;

/**
 * @author neo82
 */
public class abc305_d_SleepLog_Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[] A = new int[N];
        int[] pSum = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }

        for (int i = 1; i < N; i++) {
            if (i % 2 == 0) {
                pSum[i] = pSum[i - 1] + (A[i] - A[i - 1]);
            } else {
                pSum[i] = pSum[i - 1];
            }
        }

        int Q = scanner.nextInt();

        for (int i = 0; i < Q; i++) {
            int L = scanner.nextInt();
            int R = scanner.nextInt();

            System.out.println(f(R, pSum, A) - f(L, pSum, A));
        }
    }

    private static int binarySearch(int[] A, int val) {
        int left = 0;
        int right = A.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (val >= A[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    private static long f(int x, int[] pSum, int[] A) {
        int i = binarySearch(A, x);

        if (i < 0) {
            return 0;
        } else if (i >= A.length - 1) {
            return pSum[pSum.length - 1];
        }

        // y = y_0 + y_1 - y_0 / x_1 - x_0 * (x - x_0)
        return pSum[i] + ((long) pSum[i + 1] - pSum[i]) / (A[i + 1] - A[i]) * (x - A[i]);
    }
}
