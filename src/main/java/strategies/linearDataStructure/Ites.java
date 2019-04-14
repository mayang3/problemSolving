package strategies.linearDataStructure;

import java.util.*;

/**
 * @author baejunbeom
 */
public class Ites {

	static long loopSignal(int i) {
		if (i == 0) {
			return 1983;
		}

		return (long)((loopSignal(i-1) * 214013 + 2531011) % Math.pow(2, 32));
	}

	static int makeSignal(int i) {
		return (int)(loopSignal(i-1) % 10000 + 1);
	}

	static class RandomSignal {
		long seed = 1983;

		int next() {
			long ret = seed;
			seed = (long)(((seed * 214013L) + 2531011L) % Math.pow(2, 32));
			return (int)(ret % 10000L + 1L);
		}
	}

	/**
	 * 최초의 가장 simple 한 알고리즘
	 * @param signals
	 * @param k
	 * @return
	 */
	static int simple(List<Integer> signals, int k) {
		int ret = 0;

		for (int head = 0 ; head < signals.size() ; head++) {
			int sum = 0;

			for (int tail = head ; tail < signals.size() ; tail++) {
				// sum 은 [head, tail] 구간의 합이다.
				sum += signals.get(tail);

				if (sum == k) {
					ret++;
				}

				if (sum >= k) {
					break;
				}
			}
		}

		return ret;
	}

	static int optimized(List<Integer> signals, int k) {
		int ret = 0;
		int tail = 0;
		int rangeSum = signals.get(0);

		for (int head = 0 ; head < signals.size() ; head++) {
			// rangeSum 이 k 이상인 최초의 구간을 만날 때까지 tail 을 옮긴다.
			while (rangeSum < k && tail + 1 < signals.size()) {
				rangeSum += signals.get(++tail);
			}

			if (rangeSum == k) {
				ret++;
			}

			// signals.get(head) 는 이제 구간에서 빠진다.
			// (핵심) head + 1에서 부터 tail 가지의 rangeSum 은 유지시킨다!!
			// 결국에는 이전의 계산값을 활용한다는 의미에서 dynamic programming 이라고 볼 수 있을듯..
			rangeSum -= signals.get(head);
		}

		return ret;
	}

	static int countRange(int k, int n) {
		// 현재 구간에 들어 있는 숫자들을 저장하는 큐
		Queue<Integer> range = new LinkedList<>();

		int ret = 0;
		int rangeSum = 0;
		RandomSignal randomSignal = new RandomSignal();

		for (int i = 0 ; i < n ; i++) {
			// 계산할 구간의 숫자를 추가한다.
			int newSignal = randomSignal.next();
			rangeSum += newSignal;
			range.add(newSignal);

			// 구간의 합이 k 를 초과하는 동안, 구간에서 숫자를 뺀다.
			while (rangeSum > k) {
				// 큐에서도 head 를 제거하고, sum 에서도 제거한다.
				rangeSum -= range.poll();
			}

			if (rangeSum == k) {
				ret++;
			}
		}

		return ret;
	}

	public static void main(String[] args) {
		System.out.println(countRange(3578452, 5000000));
	}
}
