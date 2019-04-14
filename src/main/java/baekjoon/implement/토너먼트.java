package baekjoon.implement;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 토너먼트 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		int X = scanner.nextInt();
		int Y = scanner.nextInt();

		System.out.println(findCount(N, X, Y));
	}

	static int findCount(int N, int X, int Y) {
		Queue<Integer> q = new LinkedList<>();

		for (int i=1 ; i<=N ; i++) {
			q.add(i);
		}

		int cnt = 0;

		while (!q.isEmpty() && q.size() > 1) {
			int size = q.size();
			int loopSize = (int)Math.ceil((double)q.size() / 2.0);
			cnt++;

			if (size == 2) {
				return cnt;
			}


			for (int i=0 ; i<loopSize ; i++) {
				int first = q.poll();

				// q 의 사이즈가 짝수이거나, q 의 사이즈가 홀수일 경우에는 마지막 인덱스가 아닐 경우 이다.
				if (i != loopSize - 1 || size % 2 == 0) {
					int second = q.poll();

					if ((first == X && second == Y) || (first == Y && second == X)) {
						return cnt;
					} else if (first == X) {
						q.add(first);
					} else if (second == X) {
						q.add(second);
					} else if (first == Y) {
						q.add(first);
					} else if (second == Y) {
						q.add(second);
					} else {
						q.add(first);
					}
				} else {
					// 홀수이고 마지막 인덱스일 경우
					q.add(first);
				}
			}
		}

		return -1;
	}
}
