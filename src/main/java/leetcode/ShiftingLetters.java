package leetcode;

import java.util.Locale;

public class ShiftingLetters {

	public static void main(String[] args) {
		String s = "ab";
		int [] shifts = {1,2};

		ShiftingLetters shiftingLetters = new ShiftingLetters();
		System.out.println(shiftingLetters.shiftingLetters(s, shifts));
	}
	public String shiftingLetters(String s, int[] shifts) {
		if (shifts == null || shifts.length == 0) {
			return s;
		}

		int n = shifts.length;
		long [] pSum = new long[n];
		pSum[n-1] = shifts[n-1];

		for (int i = n-2; i >= 0; i--) {
			pSum[i] = (pSum[i+1] + shifts[i]) % 26;
		}

		char [] arr = s.toCharArray();

		for (int i = 0; i < arr.length; i++) {
			arr[i] = (char)((arr[i] - 'a' + pSum[i]) % 26 + 'a');
		}

		return String.valueOf(arr);
	}
}
