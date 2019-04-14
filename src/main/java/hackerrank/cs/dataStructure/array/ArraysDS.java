package hackerrank.cs.dataStructure.array;

import java.util.Scanner;

/**
 */
public class ArraysDS {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int [] ret = new int[n];

		for (int i=0 ; i<n ; i++) {
			ret[i] = scanner.nextInt();
		}

		for (int i=n-1 ; i>=0 ; i--) {
			System.out.print(ret[i] + " ");
		}
	}
}
