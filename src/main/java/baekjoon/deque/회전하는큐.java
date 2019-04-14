package baekjoon.deque;

import java.util.*;

/**
 * 예제에서는 2 or 3번의 회전 없이 1번의 출력만을 통해 주어진 값을 다 뽑아낼 수 있으므로, 출력값은 0이다.
 *
 * 뽑는 것은 앞에서만 뽑을 수 있다.
 */
public class 회전하는큐 {
	static int N;

	static class ShiftResult {
		int cnt;
		LinkedList<Integer> queue;

		ShiftResult(int cnt, LinkedList<Integer> queue) {
			this.cnt = cnt;
			this.queue = queue;
		}
	}

	/**
	 * 1. 오른쪽으로 시프트 가능한 경우
	 * 	1-1 0 < poll < queue.first  이거나,
	 * 	1-2. mid < poll < N 인 경우
	 * 2. 왼쪽으로 시프트 가능한 경우
	 * 	1-1. right < poll < N 이거나
	 * 	1-2. 0 < poll < mid 인 경우
	 * @param queue
	 * @param frontList
	 * @return
	 */
	static int solve(LinkedList<Integer> queue, LinkedList<Integer> frontList) {
		int cnt = 0;

		while (!frontList.isEmpty()) {
			if (queue.peekFirst() == frontList.peekFirst()) {
				queue.pollFirst();
				frontList.pollFirst();
			} else {
				int poll = frontList.poll();

				ShiftResult left = leftShift(queue, poll);
				ShiftResult right = rightShift(queue, poll);

				if (left.cnt <= right.cnt) {
					cnt += left.cnt;
					queue = left.queue;
				} else {
					cnt += right.cnt;
					queue = right.queue;
				}
			}
		}

		return cnt;
	}

	private static ShiftResult rightShift(LinkedList<Integer> queue, int poll) {
		LinkedList<Integer> lsq = new LinkedList<>(queue);

		int left=0;

		while (true) {
			if (lsq.peek() == poll) {
				lsq.poll();
				break;
			}

			lsq.addLast(lsq.pollFirst());
			left++;
		}

		return new ShiftResult(left, lsq);
	}

	private static ShiftResult leftShift(LinkedList<Integer> queue, int poll) {
		LinkedList<Integer> rsq = new LinkedList<>(queue);

		int right = 0;

		while (true) {
			if (rsq.peek() == poll) {
				rsq.poll();
				break;
			}

			rsq.addFirst(rsq.pollLast());
			right++;
		}

		return new ShiftResult(right, rsq);
	}

	// 4 5 6 7 8 9 10
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		int M = scanner.nextInt();

		LinkedList<Integer> deque = new LinkedList<>();

		for (int i = 1; i <= N; i++) {
			deque.add(i);
		}

		LinkedList<Integer> popList = new LinkedList<>();

		while (M-- > 0) {
			popList.add(scanner.nextInt());
		}

		System.out.println(solve(deque, popList));
	}
}
