package codeforce.div2.round374.B;

import java.util.Scanner;

/**
 * http://codeforces.com/problemset/problem/721/B
 */
public class PassWords {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int k = scanner.nextInt();

		int [] length = new int[101];

		for (int i = 0; i < n; i++) {
			length[scanner.next().length()]++;
		}

		int goalLength = scanner.next().length();

		int sum = 0;

		for (int i = 1; i < goalLength; i++) {
			sum += length[i];
		}

		int min = sum + getMinBlockTime(sum, k) + 1;

		sum += length[goalLength];

		int max = sum + getMaxBlockTime(sum, k);

		System.out.print(min + " " + max);
	}

	static int getMinBlockTime(int sum, int k) {
		return (sum / k) * 5;
	}

	static int getMaxBlockTime(int sum, int k) {
		// max 값이 정확히 걸치는 경우는 1을 빼주어야 한다 -> 이미 찾은 경우이므로..
		sum = sum % k == 0 ? sum - 1 : sum;

		return (sum / k) * 5;
	}
}
