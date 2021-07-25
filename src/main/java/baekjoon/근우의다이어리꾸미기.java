package baekjoon;

import java.util.Scanner;

public class 근우의다이어리꾸미기 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        System.out.println(solve(N));
    }

    private static int solve(int N) {
        String s = String.valueOf(N);
        int len = s.length();

        if (len == 1) {
            return 1;
        }

        String diff = "";

        for (int i = 0; i < len; i++) {
            diff += "1";
        }

        return Integer.valueOf(diff) <= N ? len : len-1;
    }
}
