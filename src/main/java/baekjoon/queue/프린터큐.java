package baekjoon.queue;

import java.util.*;

public class 프린터큐 {
	static int N;
	static int M;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int TC = scanner.nextInt();

		while (TC-- > 0) {
			N = scanner.nextInt();
			M = scanner.nextInt();

			int [] arr = new int[N];

			for (int i=0 ; i<N ; i++) {
				arr[i] = scanner.nextInt();
			}

			solve(arr);
		}
	}

	static class Item {
		int index;
		int priority;

		Item(int index, int priority) {
			this.index = index;
			this.priority = priority;
		}
	}

	/**
	 * priority queue 와 dequeue 를 사용해서 푼 문제..
	 * @param arr
	 */
	static void solve(int [] arr) {
		int higherPriority = 0;
		int printPriority = arr[M];

		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		Queue<Item> queue = new LinkedList();

		for (int i=0 ; i<arr.length ; i++) {
			if (printPriority < arr[i]) {
				higherPriority++;
			}

			queue.add(new Item(i, arr[i]));
			pq.add(arr[i]);
		}

		int cnt = 0;

		while (!queue.isEmpty()) {
			Item poll = queue.poll();

			if (poll.priority == printPriority) {
				if (higherPriority > 0) {
					queue.add(poll);
				} else if (poll.index != M && higherPriority == 0) {
					pq.poll();
					cnt++;
				} else if (poll.index == M) {
					System.out.println(++cnt);
					break;
				}
			} else if (poll.priority > printPriority) {
				// 여기서 무조건 빠지면 안된다..
				// poll.priority 가 printPriority 보다는 크지만, poll.priority 보다 큰 우선순위가 배열에 존재한다면 뒤로 보내야 한다.
				if (pq.peek() > poll.priority) {
					queue.add(poll);
				} else {
					pq.poll();
					higherPriority--;
					cnt++;
				}
			} else if (poll.priority < printPriority) { // 이건 무조건이 맞음..
				queue.add(poll);
			}
		}
	}
}
