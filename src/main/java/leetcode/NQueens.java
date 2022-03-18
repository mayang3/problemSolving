package leetcode;

import com.sun.org.apache.xpath.internal.objects.XStringForFSB;

import java.util.*;

public class NQueens {
    Set<Integer> col = new HashSet<>();
    Set<Integer> d1 = new HashSet<>(); // from left top to right bottom.
    Set<Integer> d2 = new HashSet<>(); // from right top to left bottom.

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();

        solve(res, new ArrayList<>(), 0, n);

        return res;
    }

    private void solve(List<List<String>> res, List<String> solution, int y, int N) {
        if (y >= N) {
            if (solution.size() == N) {
                res.add(new ArrayList<>(solution));
            }

            return;
        }

        for (int x = 0; x < N; x++) {
            if (isValid(y, x)) {
                set(solution, y, x, N);
                solve(res, solution, y+1, N);
                delete(solution, y, x);
            }
        }
    }

    private void delete(List<String> solution, int y, int x) {
        solution.remove(solution.size() - 1);
        col.remove(x);
        d1.remove(y - x);
        d2.remove(y+x);
    }

    private void set(List<String> solution, int y, int x, int N) {
        solution.add(makeRow(x, N));
        col.add(x);
        d1.add(y - x);
        d2.add(y+x);
    }

    String makeRow(int x, int N) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            if (i == x) {
                sb.append("Q");
            } else {
                sb.append(".");
            }
        }

        return sb.toString();
    }

    private boolean isValid(int y, int x) {
        return !col.contains(x) && !d1.contains(y-x) && !d2.contains(y+x);
    }

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        System.out.println(nQueens.solveNQueens(1));
    }
}
