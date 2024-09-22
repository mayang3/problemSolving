package atcoder;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author neo82
 */
public class abc372_d_Buildings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[] heights = new int[N];

        for (int i = 0; i < N; i++) {
            heights[i] = scanner.nextInt();
        }

        int[] ans = new int[N];

        Stack<Integer> stack = new Stack<>();
        stack.push(heights[N - 1]);

        for (int i = N - 2; i >= 0; i--) {
            ans[i] = stack.size();

            while (!stack.isEmpty() && stack.peek() < heights[i]) {
                stack.pop();
            }

            stack.push(heights[i]);
        }

        print(ans);

    }


    public static void print(int[] ans) {
        for (int num : ans) {
            System.out.print(num + " ");
        }
    }
}
