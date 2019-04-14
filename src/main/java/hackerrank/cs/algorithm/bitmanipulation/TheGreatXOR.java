package hackerrank.cs.algorithm.bitmanipulation;

import java.util.Scanner;

/**
 * TODO 솔루션 봤는데 아직 이해못함.. 다시 보자
 * @author baejunbeom
 */
public class TheGreatXOR {

	static long theGreatXor(long x){

		long highest = (long)(Math.log(Long.highestOneBit(x)) / Math.log(2)) + 1;


		return 0;
//		return (long)Math.pow(2, zeroBitCount) + oneBitCount;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		for(int a0 = 0; a0 < q; a0++){
			long x = in.nextLong();
			long result = theGreatXor(x);
			System.out.println(result);
		}
	}
}
