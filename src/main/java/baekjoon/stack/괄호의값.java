package baekjoon.stack;

import java.util.Scanner;
import java.util.Stack;

/**
 * 이 문제에서 얻을 수 있는 팁은,
 * char 타입과 int 타입이 뒤섞여 있을 경우에 이것을 한개의 stack 에서 처리할 수 있는 트릭이다.
 */
public class 괄호의값 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();

		System.out.println(solve(input));
	}

	/**
	 * @param input
	 */
	static int solve(String input) {
		Stack<Integer> stack = new Stack<>();

		for (int i=0 ; i<input.length() ; i++) {
			char c = input.charAt(i);

			if ('(' == c) {
				stack.push(-1 * '(');
			} else if ('[' == c) {
				stack.push(-1 * '[');
			} else if (')' == c) {
				if (stack.isEmpty()) {
					return 0;
				}

				int pop = stack.pop();

				if (-1 * '(' == pop) {
					stack.push(2);
					continue;
				}

				int add = 0;

				while (pop != -1 * '(') {
					if (pop == -1 * '[' || stack.isEmpty()) {
						return 0;
					}

					add += pop;
					pop = stack.pop();
				}

				stack.push(add*2);

			} else if (']' == c) {
				if (stack.isEmpty()) {
					return 0;
				}

				int pop = stack.pop();

				if (-1 * '[' == pop) {
					stack.push(3);
					continue;
				}

				int add = 0;

				while (pop != -1 * '[') {
					if (pop == -1 * '('|| stack.isEmpty()) {
						return 0;
					}

					add += pop;
					pop = stack.pop();
				}

				stack.push(add*3);
			}
		}

		int ret = 0;

		// 마지막에 남아있는 답들은 전부 더하기 연산에 속한다.
		while (!stack.isEmpty()) {
			int pop = stack.pop();

			if (pop < 0) {
				return 0;
			}

			ret += pop;
		}

		return ret;
	}



}
