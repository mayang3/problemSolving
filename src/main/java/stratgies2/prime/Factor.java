package stratgies2.prime;

import java.util.Arrays;

/**
 * 약수의 개수 구하기
 */
public class Factor {
	static int [] factors;

	static void getFactorBrute(int n) {
		factors = new int[n+1];

		for (int div=1 ; div <= n ; div++) {
			for (int multiple=div ; multiple <= n ; multiple += div) {
				factors[multiple] += 1;
			}
		}
	}

	public static void main(String[] args) {
		// 1천만 이하의 모든 수의 약수의 수를 계산하는 알고리즘
		getFactorBrute(10000000);

		System.out.println("end");
	}


}
