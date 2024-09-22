package atcoder;

import java.util.*;

/**
 * @author neo82
 */
public class abc342_c_ManyReplacement {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        for (char i = 'a'; i <= 'z'; i++) {
            sb.append(i);
        }

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        String S = scanner.next();
        int Q = scanner.nextInt();

        for (int i = 0; i < Q; i++) {
            char C = scanner.next().charAt(0);
            char D = scanner.next().charAt(0);

            for (int j = 0; j < sb.length(); j++) {
                if (sb.charAt(j) == C) {
                    sb.setCharAt(j, D);
                }
            }
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < N; i++) {
            ans.append(sb.charAt(S.charAt(i) - 'a'));
        }

        System.out.println(ans);
    }
}
