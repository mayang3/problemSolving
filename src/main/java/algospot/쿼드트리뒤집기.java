package algospot;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 쿼드트리뒤집기 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        while (n-- > 0) {
            String s = scanner.next();
            List<Integer> index = new ArrayList<>();
            index.add(0);
            System.out.println(reverse(s, index));
        }
    }

    private static String reverse(String s, List<Integer> index) {
        int idx = index.remove(0);
        char ch = s.charAt(idx);

        index.add(idx+1);

        if (ch == 'b' || ch == 'w') {
            return String.valueOf(ch);
        }

        String upperLeft = reverse(s, index);
        String upperRight = reverse(s, index);
        String lowerLeft = reverse(s, index);
        String lowerRight = reverse(s, index);

        return "x" + lowerLeft + lowerRight + upperLeft + upperRight;
    }
}
