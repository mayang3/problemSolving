package atcoder;

import java.util.Scanner;

/**
 * @author neo82
 */
public class abc378_d_CountSimplePaths {
    static int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int H = scanner.nextInt();
        int W = scanner.nextInt();
        int K = scanner.nextInt();

        String[] S = new String[H];

        for (int i = 0; i < H; i++) {
            S[i] = scanner.next();
        }

        boolean[][] visited = new boolean[H][W];
        int count = 0;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if (!visited[i][j] && S[i].charAt(j) == '.') {
                    count += dfs(S, visited, i, j, K);
                }
            }
        }

        System.out.println(count);
    }

    private static int dfs(String[] s, boolean[][] visited, int i, int j, int k) {
        if (k == 0) {
            return 1;
        }

        int H = s.length;
        int W = s[0].length();

        visited[i][j] = true;

        int count = 0;

        for (int [] dir : DIRECTIONS) {
            int nextY = i + dir[0];
            int nextX = j + dir[1];

            if (nextY >= 0 && nextX >= 0 && nextY < H && nextX < W && !visited[nextY][nextX] && s[nextY].charAt(nextX) == '.') {
                count += dfs(s, visited, nextY, nextX, k - 1);
            }
        }

        visited[i][j] = false;

        return count;
    }
}
