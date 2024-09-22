package atcoder;

import java.util.Scanner;

/**
 * @author neo82
 */
public class abc337_d_CheatingGomokuNarabe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int H = scanner.nextInt();
        int W = scanner.nextInt();
        int K = scanner.nextInt();

        String[] grid = new String[H];

        for (int i = 0; i < H; i++) {
            grid[i] = scanner.next();
        }

        int o = 0;
        int x = 0;

        int ans = Integer.MAX_VALUE;

        if (W >= K) {
            for (int r = 0; r < H; r++) {
                o = 0;
                x = 0;

                for (int c = 0; c < K; c++) {
                    if (grid[r].charAt(c) == 'o') {
                        o++;
                    } else if (grid[r].charAt(c) == 'x') {
                        x++;
                    }
                }

                if (x == 0) {
                    ans = Math.min(ans, K - o);
                }

                for (int left = 1; left < W - K + 1; left++) {
                    int right = left + K - 1;

                    if (grid[r].charAt(right) == 'o') {
                        o++;
                    } else if (grid[r].charAt(right) == 'x') {
                        x++;
                    }

                    if (grid[r].charAt(left - 1) == 'o') {
                        o--;
                    } else if (grid[r].charAt(left - 1) == 'x') {
                        x--;
                    }

                    if (x == 0) {
                        ans = Math.min(ans, K - o);
                    }
                }
            }
        }

        if (H >= K) {
            for (int r = 0; r < W; r++) {
                o = 0;
                x = 0;

                for (int c = 0; c < K; c++) {
                    char a = grid[c].charAt(r);

                    if (a == 'o') {
                        o++;
                    } else if (a == 'x') {
                        x++;
                    }
                }

                if (x == 0) {
                    ans = Math.min(ans, K - o);
                }

                for (int left = 1; left < H - K + 1; left++) {
                    int right = left + K - 1;

                    if (grid[right].charAt(r) == 'o') {
                        o++;
                    } else if (grid[right].charAt(r) == 'x') {
                        x++;
                    }

                    if (grid[left - 1].charAt(r) == 'o') {
                        o--;
                    } else if (grid[left - 1].charAt(r) == 'x') {
                        x--;
                    }

                    if (x == 0) {
                        ans = Math.min(ans, K - o);
                    }
                }
            }
        }

        System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);

    }
}
