package leetcode;

import java.util.*;

public class PacificAtlanticWaterFlow {
    static int [][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int [][] visited = new int[m][n];

        Queue<Point> q = new LinkedList<>();

        // pacific ocean
        for (int i = 0; i < n; i++) {
            q.add(new Point(0, i));
            visited[0][i] = 1;
        }

        for (int i = 0; i < m; i++) {
            q.add(new Point(i, 0));
            visited[i][0] = 1;
        }

        while (q.isEmpty() == false) {
            Point p = q.poll();

            for (int [] direction : DIRECTIONS) {
                int nextY = p.y + direction[0];
                int nextX = p.x + direction[1];

                if (isPossible(matrix, visited, nextY, nextX, p, m, n, 1)) {
                    q.add(new Point(nextY, nextX));
                    visited[nextY][nextX] = 1;
                }
            }
        }

        List<List<Integer>> res = new ArrayList<>();

        // atlantic ocean
        for (int i = 0; i < n; i++) {
            q.add(new Point(m - 1, i));
            if (visited[m-1][i] == 1) {
                res.add(Arrays.asList(new Integer[]{m - 1, i}));
            }

            visited[m - 1][i] = 2;
        }

        for (int i = 0; i < m; i++) {
            q.add(new Point(i, n-1));

            if (visited[i][n-1] == 1) {
                res.add(Arrays.asList(new Integer[]{i, n - 1}));
            }

            visited[i][n-1] = 2;
        }

        while (q.isEmpty() == false) {
            Point p = q.poll();

            for (int [] direction : DIRECTIONS) {
                int nextY = p.y + direction[0];
                int nextX = p.x + direction[1];

                if (isPossible(matrix, visited, nextY, nextX, p, m, n, 2)) {
                    if (visited[nextY][nextX] == 1) {
                        res.add(Arrays.asList(new Integer [] {nextY, nextX}));
                    }

                    q.add(new Point(nextY, nextX));
                    visited[nextY][nextX] = 2;
                }
            }
        }

        return res;
    }

    private boolean isPossible(int[][] matrix, int[][] visited, int nextY, int nextX, Point p, int m, int n, int val) {
        if (nextY < 0 || nextY >= m) {
            return false;
        } else if (nextX < 0 || nextX >= n) {
            return false;
        } else if (visited[nextY][nextX] == val) {
            return false;
        }

        return matrix[nextY][nextX] >= matrix[p.y][p.x];
    }

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) {
        int [][] matrix  = {{1,1},{1,1},{1,1}};

        PacificAtlanticWaterFlow pacificAtlanticWaterFlow = new PacificAtlanticWaterFlow();
        System.out.println(pacificAtlanticWaterFlow.pacificAtlantic(matrix));
    }
}
