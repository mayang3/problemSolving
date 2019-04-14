package strategies;

import java.util.Arrays;

public class BigNumberMultiply {

	// 자리수 올림 처리
	void normalize(int [] nums) {
		// 제일 앞자리 숫자를 0으로 만듬 -> 연산을 위해서..
		nums[nums.length-1] = 0;

		for (int i=0 ; i<nums.length - 1 ; ++i) {
			nums[i+1] += nums[i] / 10;
			nums[i] %= 10;
		}

	}

	// 직관적인 처리를 위해 입력을 거꾸로 받는다.
	int [] multiply(int [] a, int [] b) {

		int [] c = new int[a.length + b.length];

		for (int i=0 ; i<a.length ; ++i) {
			for (int j=0 ; j<b.length ; ++j) {
				c[i+j] += a[i] * b[j];
			}
		}

		// 자리수 올림 처리
		normalize(c);

		return c;
	}

	public static void main(String[] args) {
		BigNumberMultiply bigNumberMultiply = new BigNumberMultiply();
		int[] multiply = bigNumberMultiply.multiply(new int[] {4,3,2,1}, new int[] {8,7,6,5});

		System.out.println(Arrays.toString(multiply));
	}
}
