package atcoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * @author neo82
 */
public class abc332_d_SwappingPuzzle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int H = scanner.nextInt();
        int W = scanner.nextInt();

        int[][] A = new int[H][W];
        int[][] B = new int[H][W];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                A[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                B[i][j] = scanner.nextInt();
            }
        }

        List<Integer> p = new ArrayList<>();

        for (int i = 0; i < H; i++) {
            p.add(i);
        }

        int ans = Integer.MAX_VALUE;

        do {
            List<Integer> q = new ArrayList<>();

            for (int i = 0; i < W; i++) {
                q.add(i);
            }

            do {
                boolean match = true;

                for (int i = 0; i < H; i++) {
                    for (int j = 0; j < W; j++) {
                        if (A[p.get(i)][q.get(j)] != B[i][j]) {
                            match = false;
                            break;
                        }
                    }

                    if (!match) break;
                }

                if (!match) continue;

                int c1 = getInversionCount(p, H);
                int c2 = getInversionCount(q, W);

                ans = Math.min(ans, c1 + c2);

            } while (nextPermutation(q, W));

        } while (nextPermutation(p, H));

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
    }

    private static int getInversionCount(List<Integer> list, int size) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (list.get(i) > list.get(j)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean nextPermutation(List<Integer> list, int size) {
        int N = list.size();
        int i = N - 2;

        while (i >= 0 && list.get(i) >= list.get(i + 1)) {
            i--;
        }

        if (i == -1) {
            return false;
        }

        int j = N - 1;

        while (list.get(i) >= list.get(j)) {
            j--;
        }

        Collections.swap(list, i, j);
        Collections.reverse(list.subList(i + 1, N));

        return true;
    }
}
