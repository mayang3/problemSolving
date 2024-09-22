package atcoder;

import java.util.Scanner;

/**
 * @author neo82
 */
public class SumEqualZero {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        long [] L = new long[N];
        long [] R = new long[N];

        long sumL = 0;
        long sumR = 0;

        for (int i = 0; i < N; i++) {
            L[i] = scanner.nextInt();
            R[i] = scanner.nextInt();

            sumL += L[i];
            sumR += R[i];
        }

        if (sumL > 0 || sumR < 0) {
            System.out.println("No");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (sumL < 0) {
                long diff = Math.min(R[i] - L[i], -sumL);
                sumL += diff;
                L[i] += diff;
            }
        }

        System.out.println("Yes");

        for (int i = 0; i < N; i++) {
            System.out.print(L[i] + " ");
        }
        System.out.println();

    }
}
