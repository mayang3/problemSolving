package leetcode;

import java.util.*;

public class ValidSudoku {
    Map<Integer, Set<Character>> rowMap = new HashMap<>();
    Map<Integer, Set<Character>> columnMap = new HashMap<>();
    Map<Integer, Map<Integer, Set<Character>>> subBoxMap = new HashMap<>();

    public boolean isValidSudoku(char[][] board) {
        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                if (board[y][x] != '.') {
                    Set<Character> rowSet = rowMap.computeIfAbsent(y, t -> new HashSet<>());
                    Set<Character> columnSet = columnMap.computeIfAbsent(x, t -> new HashSet<>());
                    Set<Character> subBoxSet = subBoxMap.computeIfAbsent(y / 3, t -> new HashMap<>()).computeIfAbsent(x / 3, t -> new HashSet<>());

                    if (isPossible(y, x, Character.getNumericValue(board[y][x])) == false) {
                        return false;
                    }

                    rowSet.add(board[y][x]);
                    columnSet.add(board[y][x]);
                    subBoxSet.add(board[y][x]);
                }
            }
        }

        return true;
    }


    private boolean isPossible(int y, int x, int num) {
        char ch = Character.forDigit(num, 10);

        if (rowMap.get(y).contains(ch) || columnMap.get(x).contains(ch) || subBoxMap.get(y / 3).get(x / 3).contains(ch)) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'.','8','7','6','5','4','3','2','1'},
                {'2','.','.','.','.','.','.','.','.'},
                {'3','.','.','.','.','.','.','.','.'},
                {'4','.','.','.','.','.','.','.','.'},
                {'5','.','.','.','.','.','.','.','.'},
                {'6','.','.','.','.','.','.','.','.'},
                {'7','.','.','.','.','.','.','.','.'},
                {'8','.','.','.','.','.','.','.','.'},
                {'9','.','.','.','.','.','.','.','.'}};

        ValidSudoku validSudoku = new ValidSudoku();
        System.out.println(validSudoku.isValidSudoku(board));
    }
}
