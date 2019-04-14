package baekjoon.bruteforce;

import java.util.Scanner;

public class Subset {

	static int N;
	static int [] set;
	static int S;
	static int cnt = 0;

	static void solve(int total, int i) {
		if (i > 0 && S == total) {
			cnt++; // 이 부분이 핵심이다. 여기서 1 을 리턴하고 덧셈을 한다면 이후 부분집합은 더해질 수 없다.
		} else if (i >= N) {
			return;
		}

		for (int j=i ; j<N ; j++) {
			solve(total + set[j] , j+1);
		}
	}


	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		N = scanner.nextInt();
		S = scanner.nextInt();

		set = new int[N];

		for (int i=0; i<N ; i++) {
			set[i] = scanner.nextInt();
		}

		solve(0, 0);
		System.out.println(cnt);
	}
}
