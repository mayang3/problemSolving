package baekjoon.stack;

import java.util.Scanner;
import java.util.Stack;

public class 후위표기식2 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		String postOrder = scanner.next();

		double [] arr = new double[n];

		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}

		Stack<Double> stack = new Stack<>();

		for (int i = 0; i < postOrder.length(); i++) {
			char ch = postOrder.charAt(i);

			if (isOperator(ch)) {
				double op2 = stack.pop();
				double op1 = stack.pop();

				stack.push(calculate(ch, op1, op2));
			} else {
				stack.push(arr[ch-65]);
			}
		}

		System.out.printf("%.2f", Math.floor(stack.pop() * 100) / 100);

	}

	static double calculate(char operator, double op1, double op2) {
		switch (operator) {
			case '+':
				return op1 + op2;
			case '-':
				return op1 - op2;
			case '*':
				return op1 * op2;
			case '/':
				return op1 / op2;
			default:
				return 0;
		}
	}

	static boolean isOperator(char ch) {
		switch (ch) {
			case '+':
			case '-':
			case '*':
			case '/':
				return true;
			default:
				return false;
		}
	}
}
