package baekjoon.deque;

import java.util.Scanner;

@SuppressWarnings("ALL")
public class 덱 {

	static Node front;
	static Node back;
	static int size = 0;

	static class Node {
		int val;
		Node before;
		Node after;

		Node(int val, Node before, Node after) {
			this.val = val;
			this.before = before;
			this.after = after;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();

		while (N-- > 0) {
			String cmd = scanner.next();

			if ("push_front".equalsIgnoreCase(cmd)) {
				pushFront(scanner.nextInt());
			} else if ("push_back".equalsIgnoreCase(cmd)) {
				pushBack(scanner.nextInt());
			} else if ("pop_front".equalsIgnoreCase(cmd)) {
				System.out.println(popFront());
			} else if ("pop_back".equalsIgnoreCase(cmd)) {
				System.out.println(popBack());
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

	static void pushFront(int x) {
		Node newNode = new Node(x, null, front);

		if (front != null) {
			front.before = newNode;
		}

		if (back == null) {
			back = newNode;
		}

		front = newNode;
		size++;
	}

	static void pushBack(int x) {
		Node newNode = new Node(x, back, null);

		if (back != null) {
			back.after = newNode;
		}

		if (front == null) {
			front = newNode;
		}

		back = newNode;
		size++;
	}

	static int popFront() {
		if (front == null) {
			return -1;
		}

		int val = front.val;

		Node next = front.after;

		if (next != null) {
			next.before = null;
		} else {
			// 노드가 한개밖에 없는데 front 를 뺐다면 back 도 초기화 해준다.
			back = null;
		}

		front.after = null;
		front = next;

		size--;

		return val;
	}

	static int popBack() {
		if (back == null) {
			return -1;
		}

		int val = back.val;

		Node before = back.before;

		if (before != null) {
			before.after = null;
		} else {
			// 노드가 한개밖에 없는데 back 을 뺐다면 front 도 초기화 해준다.
			front = null;
		}

		back.before = null;
		back = before;

		size--;

		return val;
	}

	static int size() {
		return size;
	}

	static int empty() {
		return size > 0 ? 0 : 1;
	}

	static int front() {
		if (front == null) {
			return -1;
		}

		return front.val;
	}

	static int back() {
		if (back == null) {
			return -1;
		}

		return back.val;
	}
}
