package leetcode;

import java.util.Arrays;

public class PathWithMaximumGold {
    static int [][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int getMaximumGold(int[][] grid) {
        int max = 0;

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {

                boolean [][] visited = new boolean[grid.length][grid[0].length];

                if (isPossible(visited, grid, y, x)) {
                    max = Math.max(max, solve(visited, grid, y, x));
                }
            }
        }

        return max;
    }

    private int solve(boolean[][] visited, int[][] grid, int y, int x) {

        if (isPossible(visited, grid, y, x) == false) {
            return 0;
        }

        visited[y][x] = true;

        int max = 0;
        int val = grid[y][x];

        for (int [] direction : DIRECTIONS) {
            int nextY = y + direction[0];
            int nextX = x + direction[1];
            max = Math.max(max, val);

            if (isPossible(visited, grid, nextY, nextX)) {
                max = Math.max(max, val + solve(visited, grid, nextY, nextX));
            }
        }

        visited[y][x] = false;

        return max;
    }

    private boolean isPossible(boolean[][] visited, int[][] grid, int nextY, int nextX) {
        if (nextY < 0 || nextY >= grid.length) {
            return false;
        } else if (nextX < 0 || nextX >= grid[0].length) {
            return false;
        } else if (visited[nextY][nextX]) {
            return false;
        }

        return grid[nextY][nextX] > 0;
    }

    public static void main(String[] args) {
        int [][] grid = {{1,0,7},{2,0,6},{3,4,5},{0,3,0},{9,0,20}};

        PathWithMaximumGold pathWithMaximumGold = new PathWithMaximumGold();
        System.out.println(pathWithMaximumGold.getMaximumGold(grid));
    }
}
