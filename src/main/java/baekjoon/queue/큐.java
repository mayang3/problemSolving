package baekjoon.queue;

import java.util.Scanner;

public class 큐 {
	static int front = 0;
	static int tail = 0;
	static int [] queue = new int[10001];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		while (N-- > 0) {
			String cmd = scanner.next();

			if ("push".equalsIgnoreCase(cmd)) {
				push(scanner.nextInt());
			} else if ("pop".equalsIgnoreCase(cmd)) {
				System.out.println(pop());
			} else if ("size".equalsIgnoreCase(cmd)) {
				System.out.println(size());
			} else if ("empty".equalsIgnoreCase(cmd)) {
				System.out.println(empty());
			} else if ("front".equalsIgnoreCase(cmd)) {
				System.out.println(front());
			} else if ("back".equalsIgnoreCase(cmd)) {
				System.out.println(back());
			}
		}
	}

	static void push(int x) {
		queue[tail++] = x;
	}

	static int pop() {
		if (tail - front <= 0) {
			return -1;
		}

		return queue[front++];
	}

	static int size() {
		int size = tail - front;

		return size <= 0 ? 0 : size;
	}

	static int empty() {
		return size() == 0 ? 1 : 0;
	}

	static int front() {
		if (tail - front <= 0) {
			return -1;
		}

		return queue[front];
	}

	static int back() {
		if (tail - front <= 0) {
			return -1;
		}

		return queue[tail-1];
	}


}
