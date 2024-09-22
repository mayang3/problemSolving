package atcoder;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author neo82
 */
public class abc328_d_TakeABC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String S = scanner.next();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < S.length(); i++) {
            char c = S.charAt(i);

            if (c == 'C' && stack.size() > 1) {
                char b = stack.pop();
                char a = stack.pop();

                if (b != 'B' || a != 'A') {
                    stack.push(a);
                    stack.push(b);
                    stack.push(c);
                }
            } else {
                stack.push(c);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }

        System.out.println(sb);
    }
}
