package leetcode;

import java.util.*;

public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        Map<Integer, Set<Integer>> rowMap = new HashMap<>();
        Map<Integer, Set<Integer>> colMap = new HashMap<>();
        Map<Integer, Map<Integer, Set<Integer>>> subBoxMap = new HashMap<>();

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                rowMap.computeIfAbsent(y, t -> new HashSet<>());
                colMap.computeIfAbsent(x, t -> new HashSet<>());
                subBoxMap.computeIfAbsent(y / 3, t -> new HashMap<>())
                        .computeIfAbsent(x / 3, t -> new HashSet<>());

                if (board[y][x] == '.') {
                    continue;
                }

                int val = Character.getNumericValue(board[y][x]);

                rowMap.get(y).add(val);
                colMap.get(x).add(val);
                subBoxMap.get(y/3).get(x/3).add(val);
            }
        }

        solve(rowMap, colMap, subBoxMap, board, 0, 0);
    }

    private boolean solve(Map<Integer, Set<Integer>> rowMap, Map<Integer, Set<Integer>> colMap, Map<Integer, Map<Integer, Set<Integer>>> subBoxMap, char[][] board, int y, int x) {
        if (board[y][x] == '.') {
            for (int num = 1; num < 10; num++) {
                if (isValid(rowMap, colMap, subBoxMap, num, y, x)) {
                    setNumber(rowMap, colMap, subBoxMap, board, y, x, num);

                    if (y == 8 && x == 8) {
                        return true;
                    } else if (x == 8) {
                        if (solve(rowMap, colMap, subBoxMap, board, y+1, 0)) {
                            return true;
                        }
                    } else if (solve(rowMap, colMap, subBoxMap, board, y, x+1)) {
                        return true;
                    }

                    delNumber(rowMap, colMap, subBoxMap, board, y, x, num);
                }
            }

        } else {
            if (y == 8 && x == 8) {
                return true;
            } else if (x == 8) {
                if (solve(rowMap, colMap, subBoxMap, board, y+1, 0)) {
                    return true;
                }
            } else if (solve(rowMap, colMap, subBoxMap, board, y, x+1)) {
                return true;
            }
        }

        return false;
    }

    private boolean isEnd(Map<Integer, Set<Integer>> rowMap) {
        int size = 0;

        for (Set<Integer> set : rowMap.values()) {
            size += set.size();
        }

        return size == 81;
    }

    private void delNumber(Map<Integer, Set<Integer>> rowMap, Map<Integer, Set<Integer>> colMap, Map<Integer, Map<Integer, Set<Integer>>> subBoxMap, char[][] board, int y, int x, int num) {
        rowMap.get(y).remove(num);
        colMap.get(x).remove(num);
        subBoxMap.get(y/3).get(x/3).remove(num);
        board[y][x] = '.';
    }

    private void setNumber(Map<Integer, Set<Integer>> rowMap, Map<Integer, Set<Integer>> colMap, Map<Integer, Map<Integer, Set<Integer>>> subBoxMap, char[][] board, int y, int x, int num) {
        rowMap.get(y).add(num);
        colMap.get(x).add(num);
        subBoxMap.get(y/3).get(x/3).add(num);
        board[y][x] = Character.forDigit(num, 10);
    }

    private boolean isValid(Map<Integer, Set<Integer>> rowMap, Map<Integer, Set<Integer>> colMap, Map<Integer, Map<Integer, Set<Integer>>> subBoxMap, int num, int y, int x) {
        if (rowMap.get(y).contains(num)) {
            return false;
        }

        if (colMap.get(x).contains(num)) {
            return false;
        }

        if (subBoxMap.get(y/3).get(x/3).contains(num)) {
            return false;
        }

        return true;
    }


    public static void main(String[] args) {
        char[][] board = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'}, {'6', '.', '.', '1', '9', '5', '.', '.', '.'}, {'.', '9', '8', '.', '.', '.', '.', '6', '.'}, {'8', '.', '.', '.', '6', '.', '.', '.', '3'}, {'4', '.', '.', '8', '.', '3', '.', '.', '1'}, {'7', '.', '.', '.', '2', '.', '.', '.', '6'}, {'.', '6', '.', '.', '.', '.', '2', '8', '.'}, {'.', '.', '.', '4', '1', '9', '.', '.', '5'}, {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        SudokuSolver sudokuSolver = new SudokuSolver();
        sudokuSolver.solveSudoku(board);

        print(board);

    }

    static void print(char [][] board) {
        for (char [] data : board) {
            System.out.println(Arrays.toString(data));
        }
    }
}
