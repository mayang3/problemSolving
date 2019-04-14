package codeforce.div2.round537.B;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;


public class AverageSuperheroGangPower {
	/**
	 * 2 4 6
	 * 4 7
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		//
		long k = scanner.nextLong();
		// m : operation count
		long m = scanner.nextLong();

		//
		long sum = 0;
		long [] arr = new long[n+1];

		for (int i = 1; i <= n; i++) {
			arr[i] = scanner.nextLong();
			sum += arr[i];
		}

		solve(n, k, m, sum, arr);

	}

	public static double solve(int n, long k, long m, long sum, long[] arr) {
		Arrays.sort(arr);

		// 최초 아무 element 도 제거하지 않았을 경우의 최대 avg
		double mx = ((double)sum + (double)Math.min(m, n * k)) / (double)n;

		// O(min(n,m))
		for (int i = 1; i <= Math.min(n - 1, m); i++) {
			sum -= arr[i];
			// 현재 인덱스에 해당하는 element 를 제거한 나머지를 가지고 만든 max sum
			double maxSum = (double)sum + (double)Math.min(m-i, (n-i) * k);
			// 현재 인덱스에 해당하는 element 를 제거한 나머지를 가지고 만든 max avg
			mx = Math.max(mx, maxSum / (double) (n-i));
		}

		return mx;
	}

	/**
	 * 잘못된 풀이
	 * @param n
	 * @param k
	 * @param m
	 * @param sum
	 * @param arr
	 * @return
	 */
	public static double solve2(int n, long k, long m, long sum, long[] arr) {
		LinkedList<Long> ll = new LinkedList<Long>();

		for (int i = 1; i < arr.length; i++) {
			ll.add(arr[i]);
		}

		double avg = (double)sum / (double)n;

		Collections.sort(ll);

		// 평균보다 낮은 녀석들을 제거해준다.
		// 이만큼만 제거해도 괜찮다는 보장을 할 수 없음
		for (int i = 0; i < n ; i++) {
			if (m > 0 && ((double)ll.getFirst() < avg)) {
				ll.removeFirst();
				m--;
			}
		}

		// 연산이 끝났으면 종료
		if (m == 0) {
			return (double)(getSum(ll) + m) / (double)ll.size();
		}

		// 연산이 남은 경우
		int superHeroCount = ll.size();

		if (superHeroCount * k >= m) {
			return (double)(getSum(ll) + m) / (double)ll.size();
		} else {
			// else if (superHeroCount * k < m)
			return (double)(getSum(ll) + (superHeroCount * k)) / (double)ll.size();
		}
	}

	static long getSum(LinkedList<Long> ll) {
		long sum = 0;

		for (int i = 0; i < ll.size(); i++) {
			sum += ll.get(i);
		}

		return sum;
	}
}
