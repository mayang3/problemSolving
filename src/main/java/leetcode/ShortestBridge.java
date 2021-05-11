package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestBridge {
    static int [][] DIRECTION = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int shortestBridge(int[][] A) {
        Queue<Point> q = makeQueue(A);

        int bridge = 0;

        while (q.isEmpty() == false) {
            int size = Integer.valueOf(q.size());

            for (int i = 0; i < size; i++) {
                Point point = q.poll();

                int y = point.y;
                int x = point.x;

                if (foundGroup(A, y, x)) {
                    return bridge;
                }

                for (int [] dir : DIRECTION) {
                    int nextY = dir[0] + y;
                    int nextX = dir[1] + x;

                    if (isPossible(A, nextY, nextX, 0)) {
                        A[nextY][nextX] = 2;
                        q.add(new Point(nextY, nextX));
                    }
                }
            }

            bridge++;
        }

        return -1;
    }

    private boolean foundGroup(int[][] A, int y, int x) {
        for (int [] dir : DIRECTION) {
            int nextY = dir[0] + y;
            int nextX = dir[1] + x;

            if (isPossible(A, nextY, nextX, 1)) {
                return true;
            }
        }

        return false;
    }

    private Queue<Point> makeQueue(int[][] A) {
        Queue<Point> q = new LinkedList<>();

        for (int y = 0; y < A.length; y++) {
            for (int x = 0; x < A[y].length; x++) {
                if (A[y][x] == 1) {
                    dfs(q, A, y, x);
                    return q;
                }
            }
        }

        return q;
    }

    private void dfs(Queue<Point> q, int[][] A, int y, int x) {
        A[y][x] = 2;
        q.add(new Point(y, x));

        for (int [] dir : DIRECTION) {
            int nextY = dir[0] + y;
            int nextX = dir[1] + x;

            if (isPossible(A, nextY, nextX, 1)) {
                dfs(q, A, nextY, nextX);
            }
        }
    }

    private boolean isPossible(int [][] A, int y, int x, int val) {
        if (y >= A.length || y < 0) {
            return false;
        } else if (x >= A.length || x < 0) {
            return false;
        }

        return A[y][x] == val;
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
        int [][] A = {{1,1,1,1,1},{1,0,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,1,1,1,1}};

        ShortestBridge sb = new ShortestBridge();
        System.out.println(sb.shortestBridge(A));
    }
}
