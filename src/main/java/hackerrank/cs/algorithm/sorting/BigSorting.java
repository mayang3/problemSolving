package hackerrank.cs.algorithm.sorting;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

/**
 */
public class BigSorting {

	static int LONG_TYPE_MAX_LENGTH = 18;
	/**
	 * accepted
	 * @param arr
	 * @return
	 */
	static String[] bigSorting(String[] arr) {

		Arrays.sort(arr, (o1, o2) -> {
			int l1 = o1.length();
			int l2 = o2.length();

			if (l1 < l2) return -1;
			if (l1 > l2) return 1;

			if (l1 < LONG_TYPE_MAX_LENGTH && l2 < LONG_TYPE_MAX_LENGTH) {
				return Long.valueOf(o1).compareTo(Long.valueOf(o2));
			}

			return new BigInteger(o1).compareTo(new BigInteger(o2));
		});

		return arr;
	}

	/**
	 * time over
	 * @param arr
	 * @return
	 */
	static String[] bigSorting2(String[] arr) {

		BigInteger[] ba = new BigInteger[arr.length];

		for (int i=0 ; i<arr.length ; i++) {
			ba[i] = new BigInteger(arr[i]);
		}

		Arrays.sort(ba, BigInteger::compareTo);

		String [] ret = new String[arr.length];

		for (int i=0 ; i<arr.length ; i++) {
			ret[i] = ba[i].toString();
		}

		return ret;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		String[] arr = new String[n];
		for(int arr_i = 0; arr_i < n; arr_i++){
			arr[arr_i] = in.next();
		}
		String[] result = bigSorting(arr);
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + (i != result.length - 1 ? "\n" : ""));
		}
		System.out.println("");


		in.close();
	}
}
