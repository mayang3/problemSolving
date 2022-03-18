package leetcode;

public class FlipStringToMonotoneIncreasing {

	// 왼쪽에서 오른쪽으로 순차탐색하면서, 왼쪽에서 뒤집을 수 있는 갯수와 오른쪽에서 뒤집을 수 있는 갯수를 더해준다.
	public int minFlipsMonoIncr(String s) {
		int N = s.length();

		if (N == 1) {
			return 0;
		}

		// 왼쪽에 있는 1의 갯수
		int [] left = new int[N];
		// 오른쪽에 있는 0의 갯수
		int [] right = new int[N];

		int one = Character.getNumericValue(s.charAt(0));
		int zero = s.charAt(N-1) == '0' ? 1 : 0;

		for (int i = 1; i < N; i++) {
			left[i] = one;
			one += Character.getNumericValue(s.charAt(i));
		}

		for (int i = N-2; i >= 0 ; i--) {
			right[i] = zero;
			zero += (s.charAt(i) == '0' ? 1 : 0);
		}

		int min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			min = Math.min(min, left[i] + right[i]);
		}

		return min;
	}

	public static void main(String[] args) {
		FlipStringToMonotoneIncreasing flipStringToMonotoneIncreasing = new FlipStringToMonotoneIncreasing();
		System.out.println(flipStringToMonotoneIncreasing.minFlipsMonoIncr("0100101101"));
	}
}
