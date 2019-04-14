package hackerrank.cs.algorithm.greedy;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 칼로리를 최소화할수 있는 cup cake 의 먹는 순서를 찾는 문제.
 *
 * greed algorithm 에 해당되는데.. 기본적으로 max j 번째 수는 항상 작은 칼로리의 케이크를 가져와야 한다는 점만 알면 매우 쉽다.
 *
 */
public class MarcsCakewalk {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();


		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

		for (int i = 0; i < n; i++) {
			pq.add(scanner.nextInt());
		}

		long sum = 0;

		for (int j = n-1; j >= 0 ; j--) {
			sum += Math.pow(2, j) * pq.poll();
		}

		System.out.println(sum);
	}
}