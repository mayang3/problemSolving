package hackerrank.cs.algorithm.implement;

import java.math.BigInteger;
import java.util.Scanner;

public class ExtraLongFactorials {
	static void extraLongFactorials(int n) {
		System.out.println(factorial(n));
	}

	private static String factorial(int num) {
		BigInteger bigInteger = new BigInteger("1");

		for (int i=2 ; i<=num ; i++) {
			bigInteger = bigInteger.multiply(new BigInteger(String.valueOf(i)));
		}

		return bigInteger.toString();
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		extraLongFactorials(n);
		in.close();
	}
}
