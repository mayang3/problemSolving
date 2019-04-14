package hackerrank.cs.algorithm.dp;

import java.util.Scanner;

public class MA {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int t = scanner.nextInt();

		while (t-- > 0) {
			int n = scanner.nextInt();

			int [] a = new int[n];

			for (int i = 0; i < n; i++) {
				a[i] = scanner.nextInt();
			}

			int[] ret = maxSubarray(a);

			System.out.println(ret[0] + " " + ret[1]);
		}
	}

	/**
	 * maxSubSeq 는 기본적으로 0보다 큰 수만 더할때 가장 크다.
	 * : 만약 0보다 큰 수가 하나도 없고 모두 음수라면, 음수중에 가장 큰 수 하나만 더하는게 가장 크다.
	 * : 음수를 더해나갈수록 계속 수는 적어지므로..
	 *
	 * maxSubArr
	 * : 이전까지 더한합과 현재 A[i] 를 더했을때 음수가 된 경우라면, 새로 시작하는것이 항상 유리하다.
	 * : 왜냐하면,, 음수는 다음 항인 A[i+1] 의 숫자를 깍아먹기 때문이다.
	 *
	 * : 그래서 음수라면 여태까지 더한 값을 0으로 설정하고 음수가 아니라면 계속 추적한다. -> maxEndingHere
	 *
	 * : 그런데, 더한 값이 음수가 아닌 경우라도, 음수가 더해진다면 여태까지 더한 최대값보다 적어질 수도 있고, 커질 수도 있다.
	 * : 그래서 계속 더하고 있는 값이 음수가 아닌 경우 -> 그리고 여태까지 구한 최대값보다 더한 값이 큰 경우에만, 최대값을 refresh 해준다. -> maxSoFar
	 * @param arr
	 * @return
	 */
	static int[] maxSubarray(int[] arr) {

		int maxSoFar = Integer.MIN_VALUE;
		int maxEndingHere = 0;
		int maxSequence = 0;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < arr.length; i++) {
			maxEndingHere += arr[i];

			max = Math.max(max, arr[i]);
			maxSequence = Math.max(maxSequence, maxSequence + arr[i]);
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
			maxEndingHere = Math.max(maxEndingHere, 0);
		}

		if (maxSequence == 0 && maxSequence != max && max < 0) {
			maxSequence = max;
		}

		return new int[] {maxSoFar, maxSequence};
	}
}
