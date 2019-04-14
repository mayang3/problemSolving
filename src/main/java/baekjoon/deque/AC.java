package baekjoon.deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 재채점 결과 자꾸 타임아웃이 나서.. output 을 stringbuilder 로 바꾸고,
 *
 * input 을 BufferedReader 로 바꾸니 타임아웃이 안났다..
 *
 * 앞으로 이런 일이 없게끔 하려면 아예 쭉 BufferedReader 를 input/output 을 사용하자
 */
public class AC {

	static int front = 1;

	static void solve(Deque<Integer> deque, String f) {
		front = 1;

		for (int i=0 ; i<f.length() ; i++) {
			char cmd = f.charAt(i);

			if ('R' == cmd) {
				front *= -1;
			} else if ('D' == cmd) {
				if (front == 1) {
					pollFirst(deque);
				} else {
					pollLast(deque);
				}
			}
		}

		List<Integer> ret = new ArrayList<>();

		while (!deque.isEmpty()) {
			if (front == 1) {
				ret.add(pollFirst(deque));
			} else {
				ret.add(pollLast(deque));
			}
		}

		StringBuilder builder = new StringBuilder();

		builder.append("[");

		for (int i = 0; i < ret.size(); i++) {
			if (i != 0) {
				builder.append(",");
			}

			builder.append(ret.get(i));
		}

		builder.append("]");

		System.out.println(builder.toString());
	}

	static int pollFirst(Deque<Integer> deque) {
		Integer poll = deque.pollFirst();

		if (poll == null) {
			throw new RuntimeException();
		}

		return poll;
	}

	static int pollLast(Deque<Integer> deque) {
		Integer poll = deque.pollLast();

		if (poll == null) {
			throw new RuntimeException();
		}

		return poll;
	}


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			String f = br.readLine(); // function

			int N = Integer.parseInt(br.readLine());
			String inputStr = br.readLine();

			LinkedList<Integer> deque = new LinkedList<>();

			if(N != 0) {
				String[] split = inputStr.substring(1, inputStr.length() - 1).split(",");

				for (String s : split) {
					deque.add(Integer.parseInt(s));
				}
			}

			try {
				solve(deque, f);
			} catch (Exception e) {
				System.out.println("error");
			}
		}
	}
}
