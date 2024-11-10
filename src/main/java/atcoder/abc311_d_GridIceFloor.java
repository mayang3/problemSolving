package atcoder;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author neo82
 */
public class abc311_d_GridIceFloor {
    static int N, M;
    static char[][] grid;
    static boolean[][] visited;
    static boolean[][] inQueue;
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1}; // 상하좌우

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine(); // 개행 문자 소비

        grid = new char[N][M];
        visited = new boolean[N][M];
        inQueue = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String line = sc.nextLine();
            grid[i] = line.toCharArray();
        }

        bfs(2, 2);

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    static void bfs(int startX, int startY) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(startX - 1, startY - 1, null)); // 인덱스 조정
        visited[startX - 1][startY - 1] = true;

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            if (p.direction != null) {

            }
        }
    }

    static class Point {
        int x, y;
        DIRECTION direction;


        Point(int x, int y, DIRECTION direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }

    enum DIRECTION {
        UP(1, 0),
        DOWN(-1, 0),
        LEFT(0, -1),
        RIGHT(0, 1);

        int y;
        int x;

        DIRECTION(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
