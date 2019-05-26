package codeforce.div3.round555;

import java.util.Scanner;

/**
 * http://codeforces.com/contest/1157/problem/B
 *
 * 문제의 중요한부분.
 *
 * function 은 한번만 실행이 된다!
 *
 * 연속된 subsegment 에만 실행이 된다!
 *
 */
public class LongNumber {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		String nums = scanner.next();

		int [] f = new int[10];

		for (int i = 1; i < 10; i++) {
			f[i] = scanner.nextInt();
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < nums.length(); i++) {
			int num = Character.getNumericValue(nums.charAt(i));

			if (f[num] > num) {
				sb.append(changeSubSequence(nums, f, i));
				break;
			}

			sb.append(num);
		}

		System.out.println(sb.toString());
	}

	static String changeSubSequence(String nums, int[] f, int i) {
		StringBuilder sb = new StringBuilder();


		while (i < nums.length()) {
			int num = Character.getNumericValue(nums.charAt(i));

			if (num > f[num]) {
				break;
			}

			sb.append(f[num]);
			i++;
		}

		if (i < nums.length()) {
			sb.append(nums.substring(i));
		}

		return sb.toString();
	}
}
