package atcoder;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author neo82
 */
public class abc289_d_StepUpRobot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[] A = new int[N];

        for (int i = 0; i < N; i++) {
            A[i] = scanner.nextInt();
        }

        int M = scanner.nextInt();

        Set<Integer> traps = new HashSet<>();

        for (int i = 0; i < M; i++) {
            traps.add(scanner.nextInt());
        }

        int X = scanner.nextInt();

        boolean[] steps = new boolean[X + 1];
        steps[0] = true;

        for (int i = 1; i < X + 1; i++) {
            for (int canStep : A) {
                if (i - canStep < 0) break;

                if (traps.contains(i)) {
                    steps[i] = false;
                } else {
                    steps[i] |= steps[i - canStep];
                }
            }
        }

        System.out.println(steps[X] ? "Yes" : "No");
    }
}
