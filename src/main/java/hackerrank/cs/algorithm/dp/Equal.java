package hackerrank.cs.algorithm.dp;

import java.util.Arrays;

/**
 * @author baejunbeom
 */
public class Equal {
	private int [] number = {2,2,3,7};
	private int [] offset = {0,1,2,5};
	private int [] cache = new int[4];

	public int count(int index) {
		if (allSame(number)) {
			return 1;
		}

		if (index == number.length) {
			return 0;
		}

		int min = 1000000000 - 1;
		int next = index + 1;
		int org = number[index];

		for (int of : offset) {
			number[index] += of;

			min = Math.min(count(next), min);

			number[index] = org;
		}

		return min;
	}

	private boolean allSame(int[] number) {
		int temp = number[0];

		for (int n : number) {
			if (temp != n) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {

		Equal equal = new Equal();

		Arrays.fill(equal.cache, -1);
		int count = equal.count(0);

		System.out.println(count);
	}
}
