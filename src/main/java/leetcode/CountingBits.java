package leetcode;

import java.util.Arrays;

public class CountingBits {
	public int [] countBits(int num) {
		int [] res = new int[num+1];

		for (int i = 1; i <= num ; i++) {
			res[i] = res[i >> 1] + (i & 1);
		}

		return res;
	}

	public static void main(String[] args) {
		int num = 7;

		CountingBits cb = new CountingBits();
		int[] res = cb.countBits(num);

		System.out.println(Arrays.toString(res));
	}
}
