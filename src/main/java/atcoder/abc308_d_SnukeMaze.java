package atcoder;

import java.util.*;

/**
 * @author neo82
 */
public class abc308_d_SnukeMaze {
    static int[][] DICRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int H = scanner.nextInt();
        int W = scanner.nextInt();

        String[] grid = new String[H];

        for (int i = 0; i < H; i++) {
            grid[i] = scanner.next();
        }

        if (grid[0].charAt(0) != 's') {
            System.out.println("No");
            return;
        }

        Map<Character, Character> dict = makeHashMap();

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0});

        boolean[][] visited = new boolean[H][W];
        visited[0][0] = true;

        while (!q.isEmpty()) {
            int size = Integer.valueOf(q.size());

            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();

                if (cur[0] == H - 1 && cur[1] == W - 1) {
                    System.out.println("Yes");
                    return;
                }

                for (int[] dir : DICRECTIONS) {
                    int nextY = cur[0] + dir[0];
                    int nextX = cur[1] + dir[1];

                    if (isPossible(cur, nextY, nextX, dict, grid, H, W, visited)) {
                        q.add(new int[]{nextY, nextX});
                        visited[nextY][nextX] = true;
                    }
                }
            }
        }

        System.out.println("No");
    }

    private static boolean isPossible(int[] cur, int y, int x, Map<Character, Character> dict, String[] grid, int H, int W, boolean[][] visited) {
        if (y < 0 || x < 0 || y >= H || x >= W) {
            return false;
        }

        if (visited[y][x]) {
            return false;
        }

        char curChar = grid[cur[0]].charAt(cur[1]);
        char nextChar = dict.get(curChar);

        return grid[y].charAt(x) == nextChar;
    }

    private static Map<Character, Character> makeHashMap() {
        Map<Character, Character> dict = new HashMap<>();

        dict.put('s', 'n');
        dict.put('n', 'u');
        dict.put('u', 'k');
        dict.put('k', 'e');
        dict.put('e', 's');

        return dict;
    }
}
