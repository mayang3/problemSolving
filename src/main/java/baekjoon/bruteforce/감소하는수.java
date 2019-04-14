package baekjoon.bruteforce;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 감소하는수 {

	static long [] ans = new long[1000001];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		Queue<Long> q = new LinkedList<>();

		int qIndex = 9;

		// 1~9 까지의 값 셋팅
		for (int i=1 ; i<=qIndex ; i++) {
			q.add((long)i);
			ans[i] = i;
		}


		while (qIndex <= N) {
			if (q.isEmpty()) {
				break;
			}

			long poll = q.poll();

			// 여기가 핵심.. 굳 코드.. elegance..
			for (int i=0 ; i<poll%10 ; i++) {
				q.add(poll * 10 + i);
				ans[++qIndex] = poll * 10 + i;
			}
		}


		if (ans[N] == 0 && N != 0) {
			System.out.println("-1");
		} else {
			System.out.println(ans[N]);
		}
	}
}
