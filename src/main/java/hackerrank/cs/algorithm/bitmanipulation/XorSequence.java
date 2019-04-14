package hackerrank.cs.algorithm.bitmanipulation;

import java.util.Scanner;
import java.util.Stack;

/**
 * Number Binary repr. xor from 1 to n A(1)^A(2)...^A(n)

 0 0000 0000 0000=0
 1 0001 0001 0001=1
 2 0010 0011 0010=2
 3 0011 0000 0010=2
 4 0100 0100 0110=6
 5 0101 0001 0111=7
 6 0110 0111 0000=0
 7 0111 0000 0000=0
 from above we can deduce that G is periodic function with period of 8 now for a==0 or a==1 value is x for a==2 or a==3 value is 2 for a==4 or a==5 value is x+2 for a==6 or a==7 value is 0 For better undestanding how to calculate xor of 1 to n efficiently please refer this link:-xor from 1 to n here is my solution

 * @author baejunbeom
 */
public class XorSequence {

	/**
	 * 간단하게 다음과 같이 풀었지만..
	 * L 이 큰 숫자인경우에는 순차탐색이 너무 오래걸려서 timeout 발생..
	 * 어떻게 해야 O(n) 비용을 줄일 수 있을까..
	 * @param L
	 * @param R
	 * @return
	 */
	static long simpleSolve(long L, long R) {
		long sum = 0;

		Stack<Long> stack = new Stack<>();
		stack.add(0L);

		for (long i=1 ; i<R+1 ; i++) {
			long cur = stack.pop() ^ i;
			stack.push(cur);

			if (L <= i && i <= R) {
				sum ^= stack.peek();
			}
		}

		return sum;
	}

	/**
	 * Discussion Accept
	 *
	 * 8 로 mod 연산한 부분에 대해 이해가 안되었는데,
	 * discussion 의 solution 설명을 보니, 패턴을 그냥 찾은거라는 설명이..
	 *
	 * @param n
	 * @return
	 */
	static long computeXOR(long n) {
		long rem = n % 8;

		if (rem == 0 || rem == 1) return n;
		if (rem == 2 || rem == 3) return 2;
		if (rem == 4 || rem == 5) return n+2;
		if (rem == 6 || rem == 7) return 0;

		return 0L;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int Q = in.nextInt();
		for(int a0 = 0; a0 < Q; a0++){
			long L = in.nextLong();
			long R = in.nextLong();

			System.out.println(computeXOR(R) ^ computeXOR(L-1L));
		}
	}
}
