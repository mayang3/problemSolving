package baekjoon.dp;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 1st try.. timeout..
 */
public class Upstairs {

	static int [] input;
	static int [][] cache;

	static int bruteforce(int i, boolean [] steps) {
		if (i < 0) {
			return 0;
		}

		int ret = 0;

		for (int step=1 ; step<3 ; step++) {
			if (i < input.length-1 && step == 1 && steps[i+1]) {
				continue;
			}

			if (i-step >= 0) {
				steps[i - step] = true;
				ret = Math.max(ret, bruteforce(i - step, steps));
				steps[i - step] = false;
			}
		}

		return input[i] + ret;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		input = new int[N];
		cache = new int[N][2];

		for (int [] c : cache) {
			Arrays.fill(c, -1);
		}

		boolean [] steps = new boolean[N];

		for (int i=0 ; i<N ; i++) {
			input[i] = scanner.nextInt();
		}

		steps[input.length-1] = true;

		System.out.println(bruteforce(input.length-1, steps));

	}
}
