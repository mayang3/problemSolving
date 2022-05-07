package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author junbeom.bae
 */
public class PathWithMinimumEffort {

    static int [][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int minimumEffortPath(int[][] heights) {
        int N = heights.length;
        int M = heights[0].length;

        int [][] cost = new int[N][M];

        for (int [] c : cost) {
            Arrays.fill(c, Integer.MAX_VALUE);
        }

        cost[0][0] = 0;

        PriorityQueue<Point> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        pq.add(new Point(0, 0, 0));

        while (pq.isEmpty() == false) {
            Point p = pq.poll();

            for (int [] dir : DIRECTIONS) {
                int nextY = dir[0] + p.y;
                int nextX = dir[1] + p.x;

                if (cost[p.y][p.x] < p.cost) {
                    continue;
                }

                if (isPossible(nextY, nextX, N, M)) {
                    int nextCost = Math.max(Math.abs(heights[nextY][nextX] - heights[p.y][p.x]), p.cost);

                    if (nextCost < cost[nextY][nextX]) {
                        cost[nextY][nextX] = nextCost;
                        pq.add(new Point(nextY, nextX, nextCost));
                    }
                }
            }
        }

        return cost[N-1][M-1];
    }

    boolean isPossible(int y, int x, int N, int M) {
        if (y < 0 || x < 0 || y >= N || x >= M) {
            return false;
        }

        return true;
    }

    static class Point {
        int y;
        int x;
        int cost;

        public Point(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        int [][] heights = {{1,2,1,1,1},{1,2,1,2,1},{1,2,1,2,1},{1,2,1,2,1},{1,1,1,2,1}};

        PathWithMinimumEffort pathWithMinimumEffort = new PathWithMinimumEffort();
        System.out.println(pathWithMinimumEffort.minimumEffortPath(heights));
    }
}
