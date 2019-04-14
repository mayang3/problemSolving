package strategies.tree;

import java.util.PriorityQueue;

/**
 * @author baejunbeom
 */
public class RunningMedian {

	static class Rng {
		int seed;
		int a;
		int b;

		Rng(int a, int b) {
			this.a = a;
			this.b = b;
			this.seed = 1983;
		}

		int next() {
			int ret = this.seed;
			this.seed = (int)((((long)seed * a) + b) % 20090711);

			return ret;
		}
	}

	int runningMedian(int n, Rng r) {

		PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> -o1.compareTo(o2));
		PriorityQueue<Integer> minHeap = new PriorityQueue<>();

		int ret = 0;

		// 반복문 불변식
		// 1. maxHeap 의 크기는 minHeap 의 크기와 같거나 1 더 크다.
		// 2. maxHeap.top() <= minHeap.top()
		for (int cnt = 1; cnt <= n; cnt++) {
			// 우선 1번의 불변식부터 만족시킨다.
			if (maxHeap.size() == minHeap.size()) {
				maxHeap.add(r.next());
			} else {
				minHeap.add(r.next());
			}

			// 2번 불변식이 깨졌을 경우 복구하자.
			// maxHeap 의 최대값은 항상 minHeap 의 최소값보다 작아야 한다.
			// 이 조건이 맞지 않는다면 힙을 바꿔서 넣은 것..
			if (!minHeap.isEmpty() && !maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
				int a = maxHeap.poll();
				int b = minHeap.poll();
				maxHeap.add(b);
				minHeap.add(a);
			}

			ret = (int)(((long)ret + maxHeap.peek()) % 20090711L);
		}

		return ret;
	}

	public static void main(String[] args) {
		RunningMedian runningMedian = new RunningMedian();
		int i = runningMedian.runningMedian(10000, new Rng(1273, 4936));

		System.out.println(i);
	}
}
