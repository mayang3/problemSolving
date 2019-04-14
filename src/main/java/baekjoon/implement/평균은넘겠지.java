package baekjoon.implement;

import java.util.Scanner;

public class 평균은넘겠지 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int C = scanner.nextInt();

		for (int i = 0; i < C; i++) {
			int N = scanner.nextInt();

			int sum = 0;

			int [] arr = new int[N];

			for (int j = 0; j < N; j++) {
				int point = scanner.nextInt();

				arr[j] = point;
				sum += point;
			}

			double avg = (double)sum / (double)N;

			int cnt = 0;

			for (int j = 0; j < N; j++) {
				if ((double)arr[j] > avg) {
					cnt++;
				}
			}

			double res = (double)cnt / (double)N * (double)100;

			System.out.println(String.format("%.3f", res) + "%");
		}
	}
}
