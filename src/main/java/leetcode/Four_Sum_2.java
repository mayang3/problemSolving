package leetcode;

import java.util.HashMap;
import java.util.Map;

public class Four_Sum_2 {

	/**
	 *
	 * we can choose any two arrays and we get the same result.
	 *
	 * Let us take the four numbers as :
	 *
	 * 1,2,-6,3
	 *
	 * Possible combinations are:
	 *
	 * (1+2) and (-6+3)
	 * (1-6) and (2+3)
	 * (1+3) and (2-6)
	 * (2-6) and (1+3)
	 * (2+3) and (1-6)
	 *
	 * @param A
	 * @param B
	 * @param C
	 * @param D
	 * @return
	 */
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

		int cnt = 0;

		Map<Integer, Integer> sumMap = new HashMap<>();

		for (int a : A) {
			for (int b : B) {
				sumMap.put(a+b, sumMap.getOrDefault(a+b,0) + 1);
			}
		}

		for (int c : C) {
			for (int d : D) {
				cnt += sumMap.getOrDefault(-1 * (c+d), 0);
			}
		}

		return cnt;
	}

	public static void main(String[] args) {
		int [] A = {1, 2};
		int [] B = {-2, -1};
		int [] C = {-1, 2};
		int [] D = {0, 2};

		Four_Sum_2 fs2 = new Four_Sum_2();

		int ret = fs2.fourSumCount(A, B, C, D);

		System.out.println(ret);
	}
}
