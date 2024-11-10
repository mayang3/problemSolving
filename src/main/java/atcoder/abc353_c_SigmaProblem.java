package atcoder;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author neo82
 */
public class abc353_c_SigmaProblem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }

        Arrays.sort(A);

        int right = N - 1;
        long cnt = 0;

        for (int left = 0; left < N - 1; left++) {
            right = Math.max(left + 1, right);

            while (left < right && (long) A[right] + A[left] >= 100000000) {
                right--;
            }

            cnt += (N - (right + 1));
        }

        long ans = 0;

        for (int i = 0; i < N; i++) {
            ans += (long) A[i] * (N - 1);
        }

        System.out.println(ans - (cnt * 100000000));
    }
}
