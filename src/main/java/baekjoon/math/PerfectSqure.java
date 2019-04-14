package baekjoon.math;

import java.util.Scanner;

public class PerfectSqure {

	static int INF = 987654321;

	static class Result {
		int min;
		int sum;

		Result(int min, int sum) {
			this.min = min;
			this.sum = sum;
		}
	}

	static Result solve(int M, int N) {

		int sum = 0;
		int min = INF;

		for (int i=M ; i<=N ; i++) {
			double sqrt = Math.sqrt(i);

			// 핵심. 소수점을 버린 부분이, 원래의 값과 일치하는지를 확인한다.
			if (((int)sqrt) == sqrt) {
				sum += i;
				min = Math.min(min, i);
			}
		}

		return new Result(min, sum);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int M = scanner.nextInt();
		int N = scanner.nextInt();

		Result ret = solve(M, N);

		if (ret.sum == 0 && ret.min == INF) {
			System.out.println(-1);
		} else {
			System.out.println(ret.sum);
			System.out.println(ret.min);
		}
	}
}
