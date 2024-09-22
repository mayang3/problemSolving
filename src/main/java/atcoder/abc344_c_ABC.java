package atcoder;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author neo82
 */
public class abc344_c_ABC {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }

        int M = scanner.nextInt();
        int[] B = new int[M];

        for (int i = 0; i < M; i++) {
            B[i] = scanner.nextInt();
        }

        int L = scanner.nextInt();
        int[] C = new int[L];

        for (int i = 0; i < L; i++) {
            C[i] = scanner.nextInt();
        }

        Set<Long> set = new HashSet<>();

        for (int a : A) {
            for (int b : B) {
                for (int c : C) {
                    set.add((long)a + b + c);
                }
            }
        }

        int Q = scanner.nextInt();
        int [] X = new int[Q];

        for (int i = 0; i < Q; i++) {
            X[i] = scanner.nextInt();
        }

        for (int x : X) {
            if (set.contains((long)x)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }

    }
}
