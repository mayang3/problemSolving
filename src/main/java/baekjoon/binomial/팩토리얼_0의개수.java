package baekjoon.binomial;

import java.util.Scanner;

/**
 * 좋은 풀이 ..
 *
 * http://ksj14.tistory.com/entry/BackJoon1676-%ED%8C%A9%ED%86%A0%EB%A6%AC%EC%96%BC-0%EC%9D%98-%EA%B0%9C%EC%88%98
 */
public class 팩토리얼_0의개수 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int two = 0;
		int five = 0;

		for (int i=2 ; i<=n ; i*=2) {
			two += n / i;
		}

		for (int i=5 ; i<=n ; i*=5) {
			five += n / i;
		}

		System.out.println(Math.min(two, five));
	}
}
