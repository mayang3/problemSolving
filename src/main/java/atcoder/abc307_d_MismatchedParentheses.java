package atcoder;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author neo82
 */
public class abc307_d_MismatchedParentheses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        String s = scanner.next();

        int left = 0;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == ')' && left > 0) {
                while (!stack.isEmpty()) {
                    if (stack.pop() == '(') {
                        left--;
                        break;
                    }
                }
            } else {
                if (c == '(') {
                    left++;
                }

                stack.add(c);
            }
        }

        StringBuilder sb =  new StringBuilder();

        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }

        System.out.println(sb);
    }
}
