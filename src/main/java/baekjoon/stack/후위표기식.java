package baekjoon.stack;

import java.util.*;

/**
 * https://www.acmicpc.net/problem/1918
 *
 * inOrder 와 postOrder 모두 피연산자의 순서에는 변함이 없고, 연산자의 순서에만 변화가 있기 때문에 stack 에는 연산자만 넣고 연산한다.
 *
 * 1. 피연산자는 바로바로 출력한다.
 * 2. stack 에는 연산자만 넣는다.
 * 	2-1. 연산자중 ( 는 무조건 넣는다.
 * 	2-2. 연산자중 ) 를 만나면 현재 stack 에 들어있는 모든 연산자를 출력한다.
 * 	2-3. 그외 사칙연산자를 만났을 경우에는 현재 stack 에 들어있는 top 의 연산자와 우선순위를 비교하여,
 * 		 우선순위가 높은 연산자를 우선 출력하고, 사칙연산자를 stack 에 넣는다.
 *
 * * stack 에 우선순위가 낮은 연산자가 들어있다면 연산자가 stack 에 계속 쌓이게 되고, ) 를 만났을 경우 연산자를 모두 출력하게 된다.
 *
 *
 * (A+(B*C)-D/E+F)-E*F
 */
public class 후위표기식 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String inOrder = scanner.next();
		Stack<Character> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < inOrder.length(); i++) {
			char ch = inOrder.charAt(i);

			switch (ch) {
				case '(':
					stack.push(ch);
					break;
				case ')':
					while (!stack.isEmpty() && stack.peek() != '(') {
						sb.append(stack.pop());
					}

					stack.pop();
					break;

				case '-':
				case '+':
				case '*':
				case '/':
					// 연산자와 연산자가 만났을 경우 stack 에 있던 연산자를 출력한다.
					while (!stack.isEmpty() && getPriority(stack.peek()) >= getPriority(ch)) {
						sb.append(stack.pop());
					}

					stack.push(ch);
					break;

				default:
					sb.append(ch);
			}
		}

		while (stack.isEmpty() == false) {
			sb.append(stack.pop());
		}

		System.out.println(sb.toString());
	}


	static int getPriority(char ch) {
		switch (ch) {
			case '*':
			case '/':
				return 2;
			case '+':
			case '-':
				return 1;
			case '(':
			case ')':
				return 0;
			default:
				return -1;
		}
	}
}
