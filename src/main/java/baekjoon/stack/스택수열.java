package baekjoon.stack;

import java.util.*;

public class 스택수열 {
	static int N;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();

		int [] input = new int[N];

		for (int i=0 ; i<N ; i++) {
			input[i] = scanner.nextInt();
		}

		List<String> ret = solve(input);

		for (String s : ret) {
			System.out.println(s);
		}
	}

	static List<String> solve(int [] input) {
		int cnt = 1;
		int i = 0;

		MyStack myStack = new MyStack();
		myStack.push(cnt++);

		while (i < N && myStack.stack.size() <= 100002) {
			if (cnt <= input[i]) {
				myStack.push(cnt++);
			} else if (!myStack.isEmpty()) {
				int pop = myStack.pop();

				if (pop != input[i]) {
					return makeNoList();
				}

				i++;
			}
		}


		return myStack.getRet();
	}

	private static List<String> makeNoList() {
		List<String> ret = new ArrayList<>();
		ret.add("NO");
		return ret;
	}

	/**
	 * @author baejunbeom
	 */
	static class MyStack {
		List<String> ret = new ArrayList<>();
		Stack<Integer> stack = new Stack<>();

		void push(int item) {
			stack.push(item);
			ret.add("+");
		}

		int pop() {
			ret.add("-");
			return stack.pop();
		}

		boolean isEmpty() {
			return stack.isEmpty();
		}

		List<String> getRet() {
			return ret;
		}

	}

}
