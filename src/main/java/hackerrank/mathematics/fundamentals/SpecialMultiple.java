package hackerrank.mathematics.fundamentals;

import java.util.Scanner;

/**
 * good discussion..
 *
 * clever way to do this problem effeciently is by checking if [9,90,99,900....n]%N==0.
 *
 * so,how to generate [9,90,99,900....n] series?
 *
 * Basically numbers from [1, 2, 3 , 4.....n] are [1,10,11,100......n] in Binary form,
 *
 * so observe the pattern and you will realize that if you replace '1' by '9' you get a number series [9,90,99,900.....n]
 *
 */
public class SpecialMultiple {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int T = scanner.nextInt();

		while (T-- > 0) {
			long N = scanner.nextLong();

			for (long i = 1; i < Long.MAX_VALUE; i++) {
				String s = Long.toBinaryString(i).replaceAll("1", "9");

				if (Long.parseLong(s) % N == 0) {
					System.out.println(s);
					break;
				}
			}
		}
	}
}
