package hackerrank.cs.algorithm.bitmanipulation;

import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class FlippingBits {
	static long flippingBits(long N) {
		long seed64 = 0;

		for (int i=0 ; i<32 ; i++) {
			seed64 += (1L << i);
		}

		return seed64 ^ N;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int T = in.nextInt();
		for(int a0 = 0; a0 < T; a0++){
			long N = in.nextLong();
			long result = flippingBits(N);
			System.out.println(result);
		}
		in.close();
	}
}
