package atcoder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author neo82
 */
public class abc336_d_Pyramid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }

        int[] left = new int[N];
        Arrays.fill(left, Integer.MAX_VALUE);

        left[0] = A[0] - 1;

        for (int i = 1; i < N; i++) {
            left[i] = Math.min(left[i - 1], A[i] - i - 1);
        }

        int[] right = new int[N];
        Arrays.fill(right, Integer.MAX_VALUE);
        right[N - 1] = A[N - 1] + N - 2; // == A_k + i - 1

        for (int i = N - 2; i >= 0; i--) {
            right[i] = Math.min(right[i + 1], A[i] + i - 1);
        }

        int ans = 0;

        for (int k = 0; k < N; k++) {
            int leftMin = 0; // k 가 제일 왼쪽이나 제일 오른쪽인 경우에는 size 가 1 이 된다.
            int rightMin = 0;

            if (k > 0) {
                leftMin = Math.min(left[k - 1] + k, k);
            }

            if (k < N - 1) {
                rightMin = Math.min(right[k + 1] - k, N - k - 1);
            }

            int min = Math.min(Math.min(leftMin, rightMin), A[k] - 1);

            ans = Math.max(ans, min + 1);
        }

        System.out.println(ans);
    }
}
