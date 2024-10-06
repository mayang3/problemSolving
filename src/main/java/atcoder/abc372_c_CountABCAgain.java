package atcoder;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author neo82
 */
public class abc372_c_CountABCAgain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int Q = scanner.nextInt();

        String s = scanner.next();
        StringBuilder sb = new StringBuilder();
        Set<Integer> indexes = new HashSet<>();

        for (int i = 2; i < N; i++) {
            if (i == 2) {
                sb.append(s, 0, 3);
            } else {
                sb.deleteCharAt(0);
                sb.append(s.charAt(i));
            }

            if ("ABC".equals(sb.toString())) {
                indexes.add(i - 2);
            }
        }

        StringBuilder changeable = new StringBuilder(s);

        for (int i = 0; i < Q; i++) {
            int X = scanner.nextInt() - 1;

            changeable.setCharAt(X, scanner.next().charAt(0));

            indexes.remove(X - 2);
            indexes.remove(X - 1);
            indexes.remove(X);

            for (int j = X; j < X + 3; j++) {
                char A = changeable.charAt(Math.min(Math.max(j - 2, 0), N - 1));
                char B = changeable.charAt(Math.min(Math.max(j - 1, 0), N - 1));
                char C = changeable.charAt(Math.min(Math.max(j, 0), N - 1));

                if (A == 'A' && B == 'B' && C == 'C') {
                    indexes.add(j - 2);
                }
            }

            System.out.println(indexes.size());
        }
    }
}
