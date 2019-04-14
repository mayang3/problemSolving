package leetcode;

/*

Given an integer n, myCount the total number of digit 1 appearing in all non-negative integers less than or equal to n.

For example:
Given n = 13,
Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

*/


/**
 *
 * level : hard
 *
 * 1 try : fail
 *
 * @author baejunbeom
 */
public class NumberOfDigitOne {

	public int myCount(int n) {

		int countr = 0;
		for (long i = 1; i <= n; i *= 10) {
        long divider = i * 10;
			countr += (n / divider) * i + Math.min(Math.max(n % divider - i + 1, 0L), i);
		}
		return countr;
	}


	public static void main(String[] args) {
		NumberOfDigitOne numberOfDigitOne = new NumberOfDigitOne();
		int count = numberOfDigitOne.myCount(13);

		System.out.println(count);
	}
}
