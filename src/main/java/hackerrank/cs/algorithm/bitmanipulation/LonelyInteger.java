package hackerrank.cs.algorithm.bitmanipulation;

import java.util.Scanner;

/**
 * 같은 숫자는 무조건 짝수개의 입력이라는 제약조건이 주어질때, XOR 을 사용하면 무조건 0 이 된다.
 *
 * 그리고 다른 숫자는 유니크 하다면 그 숫자만이 남게된다.
 *
 * @author baejunbeom
 */
public class LonelyInteger {
	static int lonelyinteger(int[] a) {
		int ret = 0;

		for (int i=0; i<a.length ; i++) {
			ret ^= a[i];
		}

		return ret;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] a = new int[n];
		for(int a_i = 0; a_i < n; a_i++){
			a[a_i] = in.nextInt();
		}
		int result = lonelyinteger(a);
		System.out.println(result);
	}
}
