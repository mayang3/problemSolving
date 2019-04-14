package baekjoon.implement;

import java.util.Scanner;

/**
 * 아래와 같은 알고리즘의 경우 다음 테스트 케이스를 정확히 찾아내지 못한다.
 *
 * 10
 * 1
 * 1
 *
 * 정답) 2
 *
 * 의 경우, 아래 알고리즘은 자리수마다 체크하므로 20으로 갔다가 다시 10칸 내려오는 12칸을 계산한다.
 *
 * 하지만 실제 답은 9번 채널로 갔다가 + 버튼을 한번 누르는 2 이다
 *
 * 크게 두 가지의 경우가 있다.
 *
 * 1. 현재 채널에서 수동버튼만을 이용해서 이동하는 경우
 * 2. 현재 채널에서 목적지 채널까지의 가장 가까운 채널을 찍고 이동한 후에, 나머지 채널을 수동으로 이동하는 경우
 */
public class 리모컨 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String N = scanner.next();
		int M = scanner.nextInt();

		boolean [] errors = new boolean[10];

		for (int i=0 ; i<M ; i++) {
			errors[scanner.nextInt()] = true;
		}

		String S = "";

		int cnt = 0;

		for (int i=0 ; i<N.length() ; i++) {
			char ch = N.charAt(i);
			int num = Integer.valueOf(ch + "");

			int j=num;
			int k=num;

			while (((i > 0 && j>=0) || (i == 0 && j > 0)) || k <=9) {
				if ((i > 0 && j >= 0 && errors[j]) || (i == 0 && j > 0 && errors[j])) {
					j--;
				} else if(k <=9 && errors[k]) {
					k++;
				} else if ((i > 0 && j >= 0 && errors[j] == false) || (i == 0 && j > 0 && errors[j] == false)){
					S += j;
					cnt++;
					break;
				} else if (k <= 9 && errors[k] == false) {
					S += k;
					cnt++;
					break;
				}
			}

			// 현재 자리수의 모든 숫자를 찾을 수 없는 경우
			if (S.length() < i) {
				S += "0";
			}
		}

		// 만약 수동 버튼으로 이동하는것이 채널버튼을 누르는경우보다 적다면 수동버튼을 이용해 이동한다.
		if (Math.abs(Integer.valueOf(N) - 100) <= cnt) {
			System.out.println(Math.abs(Integer.valueOf(N) - 100));
		} else {
			// 모든 숫자를 찾을 수 없는 경우에는 현재 채널에서 수동버튼만 눌러서 이동 가능하다.
			System.out.println(cnt + Math.abs(Integer.valueOf(N) - (Integer.valueOf(S) == 0 ? 100 : Integer.valueOf(S))));
		}

	}
}
