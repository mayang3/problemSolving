package atcoder;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author neo82
 */
public class abc366_d_CuboidSumQuery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        Map<Integer, long[][]> map = new HashMap<>();

        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                for (int z = 1; z <= N; z++) {
                    int val = scanner.nextInt();

                    long[][] matrix = map.computeIfAbsent(x, t -> new long[N + 1][N + 1]);

                    matrix[y][z] = val;

                    if (y > 1) {
                        matrix[y][z] += matrix[y - 1][z];
                    }

                    if (z > 1) {
                        matrix[y][z] += matrix[y][z - 1];
                    }

                    if (y > 1 && z > 1) {
                        matrix[y][z] -= matrix[y - 1][z - 1];
                    }
                }
            }
        }

        int Q = scanner.nextInt();

        for (int i = 0; i < Q; i++) {
            int Lx = scanner.nextInt();
            int Rx = scanner.nextInt();
            int Ly = scanner.nextInt();
            int Ry = scanner.nextInt();
            int Lz = scanner.nextInt();
            int Rz = scanner.nextInt();

            long ans = 0;

            for (int x = Lx; x <= Rx; x++) {
                long[][] matrix = map.get(x);

                ans += matrix[Ry][Rz];

                if (Ly > 1) {
                    ans -= matrix[Ly - 1][Rz];
                }

                if (Lz > 1) {
                    ans -= matrix[Ry][Lz - 1];
                }

                if (Ly > 1 && Rz > 1) {
                    ans += matrix[Ly - 1][Lz - 1];
                }
            }

            System.out.println(ans);
        }
    }
}
