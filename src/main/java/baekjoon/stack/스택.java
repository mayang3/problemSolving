package baekjoon.stack;

import java.util.Scanner;

public class 스택 {
	static int cur=0;
	static int [] stack = new int[10001];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();

		while (N-- > 0) {
			String cmd = scanner.next();

			if ("push".equals(cmd)) {
				push(scanner.nextInt());
				continue;
			}

			if ("pop".equals(cmd)) {
				pop();
			} else if ("size".equals(cmd)) {
				size();
			} else if ("empty".equals(cmd)) {
				empty();
			} else if ("top".equals(cmd)) {
				top();
			}
		}
	}

	static void push(int x) {
		stack[cur++] = x;
	}

	static void pop() {
		if (cur <= 0) {
			System.out.println(-1);
			return;
		}

		cur--;
		System.out.println(stack[cur]);
		stack[cur] = 0;
	}

	static void size() {
		System.out.println(cur);
	}

	static void empty() {
		if (cur == 0) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

	static void top() {
		if (cur == 0) {
			System.out.println(-1);
		} else {
			System.out.println(stack[cur-1]);
		}
	}

}
