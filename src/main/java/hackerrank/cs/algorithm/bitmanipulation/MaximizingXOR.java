package hackerrank.cs.algorithm.bitmanipulation;

import java.util.Scanner;

/**
 *
 *
 * @author baejunbeom
 */
public class MaximizingXOR {

	/**
	 * Accept 받긴햇지만..
	 * @param l
	 * @param r
	 * @return
	 */
	static int maximizingXor(int l, int r) {

		int max = -1;

		for (int i=l ; i<=r ; i++) {
			for (int j=i ; j<=r ; j++) {
				max = Math.max(max, i ^ j);
			}
		}

		return max;
	}

	/**
	 * l : 10 , r 1000000 을 넣어보면 이 구현은 잘 나온다.
	 * 위의 메소드는 timeout..
	 * @param l
	 * @param r
	 * @return
	 */
	static int maximizingXor2(int l, int r) {

		// if l is 10, 1010
		// if r is 15, 1111

		int v = l ^ r; // 0101(5)

		v |= v >> 1; // 0101(5) or 0010(2) = 0111 (7)
		v |= v >> 2; // 0111(7) or 0001(1) = 0111 (7)
		v |= v >> 4; // 0111(7) or 0000(0) = 0111 (7)
		v |= v >> 8; // 0111(7) or 0000(0) = 0111 (7)
		v |= v >> 16; // 0111(7) or 0000(0) = 0111 (7)

		return v;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int l = in.nextInt();
		int r = in.nextInt();

//		System.out.println(maximizingXor(l, r));

		int result = maximizingXor2(l, r);
		System.out.println(result);
		in.close();
	}
}
