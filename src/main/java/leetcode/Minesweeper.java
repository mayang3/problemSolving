package leetcode;

import java.util.*;

public class Minesweeper {
    static char UNREVEALED_MINE = 'M';
    static char UNREVEALED_EMPTY_SQUARE = 'E';
    static char REVEALED_BLANK_SQUARE = 'B';
    static char REVEALED_MINE = 'X';

    static int[][] DIRECTIONS = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    public char[][] updateBoard(char[][] board, int[] click) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(click[0], click[1]));

        while (q.isEmpty() == false) {
            int size = Integer.valueOf(q.size());

            for (int i = 0; i < size; i++) {
                Point point = q.poll();

                int y = point.y;
                int x = point.x;

                if (board[y][x] == UNREVEALED_MINE) {
                    board[y][x] = REVEALED_MINE;
                    return board;
                }

                int unrevealedMine = 0; // M
                List<Point> candidates = new ArrayList<>();

                for (int[] dir : DIRECTIONS) {
                    int nextY = y + dir[0];
                    int nextX = x + dir[1];

                    if (isPossible(nextY, nextX, board)) {
                        char ch = board[nextY][nextX];

                        // UNREVEALED_EMPTY_SQUARE 인 경우에만 recursive 하게 확인할 수 있는 대상이다.
                        if (ch == UNREVEALED_MINE) {
                            unrevealedMine++;
                        } else if (ch == UNREVEALED_EMPTY_SQUARE) {
                            candidates.add(new Point(nextY, nextX));
                        }
                    }
                }

                if (unrevealedMine > 0) {
                    board[y][x] = Character.forDigit(unrevealedMine, 10);
                } else {
                    board[y][x] = REVEALED_BLANK_SQUARE;

                    for (Point p : candidates) {
                        board[p.y][p.x] = REVEALED_BLANK_SQUARE; // to prevent duplicates
                        q.add(p);
                    }
                }
            }
        }

        return board;
    }

    private boolean isPossible(int nextY, int nextX, char[][] board) {
        if (nextY < 0 || nextY >= board.length) {
            return false;
        } else if (nextX < 0 || nextX >= board[0].length) {
            return false;
        }

        return true;
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

        char[][] board = {{'B','1','E','1','B'},{'B','1','M','1','B'},{'B','1','1','1','B'},{'B','B','B','B','B'}};
        int[] click = {1,2};
        Minesweeper minesweeper = new Minesweeper();
        System.out.println(Arrays.deepToString(minesweeper.updateBoard(board, click)));
    }
}
