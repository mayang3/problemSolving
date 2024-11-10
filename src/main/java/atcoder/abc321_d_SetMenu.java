package atcoder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author neo82
 */
public class abc321_d_SetMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int P = scanner.nextInt();

        int[] A = new int[N];
        int[] B = new int[M];
        long[] pSum = new long[M];

        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }

        for (int i = 0; i < M; i++) {
            B[i] = scanner.nextInt();
        }

        Arrays.sort(A);
        Arrays.sort(B);

        for (int i = 0; i < pSum.length; i++) {
            pSum[i] = B[i];

            if (i > 0) {
                pSum[i] += pSum[i - 1];
            }
        }

        long ans = 0;

        for (int num : A) {
            if (num >= P) {
                ans += ((long) P * M);
            } else {
                int i = binarysearch(B, P - num);

                if (i >= 0) {
                    ans += ((long) num * (i + 1)) + pSum[i];
                }

                ans += ((long) P * (M - i - 1));
            }
        }

        System.out.println(ans);
    }

    private static int binarysearch(int[] B, int target) {
        int left = 0;
        int right = B.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (B[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }
}
