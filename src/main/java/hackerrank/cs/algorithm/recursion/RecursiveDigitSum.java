package hackerrank.cs.algorithm.recursion;

import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class RecursiveDigitSum {
	static int digitSum(String n, int k) {

		char[] chars = n.toCharArray();

		// 중간값이 MAX_INTEGER 범위를 벗어나는 부분에 대해 주의하자..
		long sum = 0;

		for (char ch : chars) {
			sum += Integer.parseInt(String.valueOf(ch));
		}

		long totalSum = sum * k;

		return cal(String.valueOf(totalSum).toCharArray());
	}

	private static int cal(char[] superDigits) {
		if (superDigits.length == 1) {
			return Integer.parseInt(String.valueOf(superDigits[0]));
		}

		long sum = 0;

		for (char superDigit : superDigits) {
			sum += Integer.parseInt(String.valueOf(superDigit));
		}

		return cal(String.valueOf(sum).toCharArray());
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String n = in.next();
		int k = in.nextInt();
		int result = digitSum(n, k);
		System.out.println(result);
		in.close();
	}
}
