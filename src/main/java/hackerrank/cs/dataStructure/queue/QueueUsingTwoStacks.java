package hackerrank.cs.dataStructure.queue;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author neo82
 */
public class QueueUsingTwoStacks {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int q = scanner.nextInt();

		Stack<Integer> inStack = new Stack<>();
		Stack<Integer> outStack = new Stack<>();

		for (int i = 0; i < q; i++) {
			int c = scanner.nextInt();

			if (c == 1) {
				inStack.push(scanner.nextInt());
			} else if (c == 2) {
				fetch(inStack, outStack);
				outStack.pop();
			} else {
				fetch(inStack, outStack);
				System.out.println(outStack.peek());
			}
		}
	}

	static void fetch(Stack<Integer> inStack, Stack<Integer> outStack) {
		if (!outStack.isEmpty()) {
			return;
		}

		while (!inStack.isEmpty()) {
			outStack.push(inStack.pop());
		}
	}
}
