package atcoder;

import java.util.*;

/**
 * @author neo82
 */
public class abc348_d_MedicinesOnGrid {
    static int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int H = scanner.nextInt();
        int W = scanner.nextInt();

        int[] start = new int[3];
        int[] end = new int[3];

        char[][] grid = new char[H][W];

        for (int y = 0; y < H; y++) {
            String s = scanner.next();

            for (int x = 0; x < W; x++) {
                char c = s.charAt(x);

                if (c == 'S') {
                    start[0] = y;
                    start[1] = x;
                } else if (c == 'T') {
                    end[0] = y;
                    end[1] = x;
                }

                grid[y][x] = c;
            }
        }

        int N = scanner.nextInt();


        List<Integer> r = new ArrayList<>();
        List<Integer> c = new ArrayList<>();
        List<Integer> e = new ArrayList<>();


        for (int i = 0; i < N; i++) {
            int R = scanner.nextInt() - 1;
            int C = scanner.nextInt() - 1;
            int E = scanner.nextInt();

            if (R == start[0] && C == start[1]) {
                start[2] = E;
                continue;
            }

            if (R == end[0] && C == end[1]) {
                end[2] = E;
                continue;
            }

            r.add(R);
            c.add(C);
            e.add(E);
        }

        r.add(start[0]);
        c.add(start[1]);
        e.add(start[2]);

        r.add(end[0]);
        c.add(end[1]);
        e.add(end[2]);

        N = r.size();

        boolean[][] isReachable = new boolean[N][N];
        // pair (y,x) 를 하나의 dot 으로 처리
        for (int i = 0; i < N; i++) {
            int[][] distance = bfs(r.get(i), c.get(i), grid);

            for (int j = 0; j < N; j++) {
                if (distance[r.get(j)][c.get(j)] <= e.get(i)) {
                    isReachable[i][j] = true;
                }
            }
        }


        boolean[] visited = new boolean[N];

        Queue<Integer> queue = new LinkedList<>();
        queue.add(N - 2);
        visited[N - 2] = true;

        while (!queue.isEmpty()) {
            int size = Integer.valueOf(queue.size());

            for (int i = 0; i < size; i++) {
                int here = queue.poll();

                for (int j = 0; j < N; j++) {
                    if (!visited[j] && isReachable[here][j]) {
                        if (j == N - 1) {
                            System.out.println("Yes");
                            return;
                        }

                        queue.add(j);
                        visited[j] = true;
                    }
                }
            }
        }

        System.out.println("No");
    }

    static int[][] bfs(int y, int x, char[][] grid) {
        int H = grid.length;
        int W = grid[0].length;

        int[][] dist = new int[H][W];

        for (int[] d : dist) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        dist[y][x] = 0;

        while (!queue.isEmpty()) {
            int size = Integer.valueOf(queue.size());

            for (int i = 0; i < size; i++) {
                int[] here = queue.poll();

                for (int[] dir : DIRECTIONS) {
                    int nextY = here[0] + dir[0];
                    int nextX = here[1] + dir[1];

                    if (nextY >= 0 && nextX >= 0 && nextY < H && nextX < W && grid[nextY][nextX] != '#') {
                        if (dist[nextY][nextX] > dist[here[0]][here[1]] + 1) {
                            dist[nextY][nextX] = dist[here[0]][here[1]] + 1;
                            queue.add(new int[]{nextY, nextX});
                        }
                    }
                }
            }
        }

        return dist;
    }


}
