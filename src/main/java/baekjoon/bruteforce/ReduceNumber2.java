package baekjoon.bruteforce;

import java.util.Arrays;
import java.util.Scanner;

/**
 * k 번째 수를 찾는 문제를 응용할 수 있을듯 하다..
 *
 * 앞의 경우의 수를 dp 로 찾아서, 앞의 경우의 수만큼 skip 하자..
 *
 * 그러기 위해서는 우선 solve 에 각 자리수가 주어졌을때,
 *
 * 해당 자리수로 만들 수 있는 감소하는 수의 "개수" 를 반환하자.
 *
 * TODO
 */
public class ReduceNumber2 {

	static long [][] cache = new long[100][100];

	static long solve(int i, int n) {
		if (n==0 && i >0) {
			return 1;
		} else if (n==0 && i==0) {
			return 0;
		}

		if (cache[i][n] != -1) {
			return cache[i][n];
		}

		int count = 0;

		for (int j=i-1 ; j>=0 ; j--) {
			count += solve(j, n-1);
		}

		return cache[i][n] = count;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		for (long [] c : cache) {
			Arrays.fill(c, -1);
		}

		System.out.println(solve(1, 1));
	}

}
