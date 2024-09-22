package atcoder;

import java.util.Scanner;

/**
 * @author neo82
 */
public class abc335_d_LoongAndTakahashi {
    // in order, top, right, bottom, left
    static int[][] DIRECTION = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[][] grid = new int[N][N];

        grid[(N / 2)][N / 2] = Integer.MAX_VALUE; // Takahashi

        dfs(grid, 1, N - 1, 0, 0);

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.print((grid[y][x] == Integer.MAX_VALUE ? "T" : grid[y][x]) + " ");
            }

            System.out.println();
        }
    }

    private static void dfs(int[][] grid, int num, int y, int x, int dir) {
        grid[y][x] = num;

        if (isPossible(y + DIRECTION[dir][0], x + DIRECTION[dir][1], grid)) {
            dfs(grid, num + 1, y + DIRECTION[dir][0], x + DIRECTION[dir][1], dir);
        } else if (isPossible(y + DIRECTION[(dir + 1) % 4][0], x + DIRECTION[(dir + 1) % 4][1], grid)) {
            dfs(grid, num + 1, y + DIRECTION[(dir + 1) % 4][0], x + DIRECTION[(dir + 1) % 4][1], (dir + 1) % 4);
        }
    }


    static boolean isPossible(int y, int x, int[][] grid) {
        if (y < 0 || x < 0 || y >= grid.length || x >= grid.length) {
            return false;
        }

        return grid[y][x] == 0;
    }
}
