package algospot;

import java.util.PriorityQueue;
import java.util.Scanner;

public class STRJOIN {

	static int concat(int [] arr) {
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		// O(n)
		for (int i = 0; i < arr.length; i++) {
			pq.add(arr[i]); // logn
		}

		int ret = 0;

		while (pq.size() > 1) {
			int min1 = pq.poll();
			int min2 = pq.poll();
			pq.add(min1 + min2);

			ret += min1 + min2;
		}

		return ret;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int C = scanner.nextInt();

		while (C-- > 0) {
			int N = scanner.nextInt();
			int [] arr = new int[N];

			for (int i = 0; i < N; i++) {
				arr[i] = scanner.nextInt();
			}

			System.out.println(concat(arr));
		}
	}
}
