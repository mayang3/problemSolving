package baekjoon.backtracking;

import java.util.Scanner;

public class 좋은수열 {
	static int n;
	static boolean stop;
	static String min;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		n = scanner.nextInt();

		solve("1");

		System.out.println(min);
	}

	static void solve(String num) {
		if (stop) {
			return;
		}

		// 또는, 여기 (아래설명..)

		if (num.length() == n) {
			stop = true;
			// 아주 중요! 얘를 빼면 timeout 이 남..
			// 이유는 최소값을 찾았는데도 그 뒤에 모든 큰값을 재귀 호출하기 때문..
			// 사실 최소값 자체를 비교할 필요가 없는 것이.. 아래에서 재귀 호출할 때, 이미 1->2->3 순서로 호출하므로,
			// 최초 찾게 되는 값이 항상 최소값이 된다.
			min = num;
			return;
		}

		for (int i = 1; i <= 3; i++) {
			// 이 위치 (loop 안) 에서 badSequence 를 걸러주는게 중요함
			// 1,2,3 각각의 호출마다 badSequence 가 생성될 수 있으므로.. 모두 체크해줘야함.
			// 여기에 넣지 않는다면, 앞의 "또는 여기" 위치에 넣어줄 수도 있음
			if (!badSequence(num + i)) {
				solve(num + i);
			}
		}
	}

	private static boolean badSequence(String num) {
		for (int i = 1; i <= num.length()/2; i++) {
			int len = num.length();
			int r = len - i;
			int l = len - i * 2;

			if (num.substring(l, r).equals(num.substring(r, len))) {
				return true;
			}
		}

		return false;
	}
}
