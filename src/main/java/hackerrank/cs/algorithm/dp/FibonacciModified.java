package hackerrank.cs.algorithm.dp;

import java.math.BigInteger;
import java.util.Scanner;

public class FibonacciModified {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int t1 = scanner.nextInt();
		int t2 = scanner.nextInt();
		int n = scanner.nextInt();

		BigInteger[] arr = new BigInteger[21];

		arr[1] = new BigInteger(String.valueOf(t1));
		arr[2] = new BigInteger(String.valueOf(t2));

		// Ti+2 = Ti + (Ti+1)^2
		for (int i = 3; i < 21; i++) {
			arr[i] = arr[i-2].add(arr[i-1].pow(2));

			if (i == n) {
				System.out.println(arr[i]);
				break;
			}
		}
	}
}
