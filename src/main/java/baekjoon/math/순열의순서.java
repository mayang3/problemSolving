package baekjoon.math;

import java.util.LinkedList;
import java.util.Scanner;

public class 순열의순서 {
	// 각 자리수의 이항계수를 먼저 구해둔다.
	static long [] binomial = new long[22];
	
	static {
		binomial [0] = binomial[1] = 1;

		for (int i = 2; i <= 21 ; i++) {
			binomial[i] = binomial[i-1] * i;
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int op = scanner.nextInt();

		LinkedList<Integer> ll = makeLinkedList(n);

		if (op == 1) {
			long k = scanner.nextLong();

			printPermutationByK(n, k, ll, 0);
		} else if (op == 2) {

			int [] perm2 = new int[n];

			for (int i = 0; i < n; i++) {
				perm2[i] = scanner.nextInt();
			}

			printKByPermutation(n, 1, ll, perm2, 0);
		}
	}

	/**
	 * 여기서 k 를 int 형으로 써서 최초 계속 오답.
	 *
	 * perm2 순열과 일치하는 순열이 몇번째 순열인지를 구하기.
	 *
	 * @param n
	 * @param k
	 * @param perm1
	 * @param perm2
	 * @param cur
	 */
	static void printKByPermutation(int n, long k, LinkedList<Integer> perm1, int[] perm2, int cur) {
		if (cur == n-1) {
			System.out.print(k);
			return;
		}

		for (int i = cur; i < n; i++) {
			int num1 = perm1.get(i);
			int num2 = perm2[cur];

			if (num1 == num2) {
				perm1.add(cur, perm1.remove(i));
				break;
			}

			k += binomial[n-cur-1];
		}

		printKByPermutation(n, k, perm1, perm2, cur+1);
	}

	/**
	 * k 번째 순열 출력하기
	 *
	 * @param n
	 * @param k
	 * @param ll
	 * @param cur
	 */
	static void printPermutationByK(int n, long k, LinkedList<Integer> ll, int cur) {
		if (cur == n-1) {
			print(ll);
			return;
		}

		// 1. 현재 이항계수보다 k 가 큰 경우 - 맨 앞자리 숫자를 바꿔준다.
		int firstIdx = (int)(k / binomial[n-cur-1]);

		// firstIdx 가 나머지 없이 정확히 떨어지는 경우, 1을 빼준다.
		// 왜냐하면.. 정확히 떨어지는 경우에도 앞자리 숫자는 firstIdx 가 1.xxx 인 경우와 동일해야 하기 때문이다.
		// 예를 들어 4자리 숫자일 경우, 맨 앞지리 숫자를 고정할 때 경우의 수가 3! 이다.
		// 이때, 예를 들어 12번째 숫자를 구한다고 하면 맨 앞자리 숫자가 2가 되고 나머지 자리수를 확인해야 하는데, 1을 뺴주지 않는다면 맨 앞지리 숫자가 3이 된다.
		if (firstIdx > 0 && (k % binomial[n-cur-1] == 0)) {
			firstIdx--;
		}

		ll.add(cur, ll.remove(cur + firstIdx));

		// 2. 현재 이항계수보다 k 가 적은 경우 - 맨 앞자리 숫자가 변경되지 않는다.
		printPermutationByK(n, k - (binomial[n-cur-1] * (long)firstIdx), ll, cur+1);
	}

	static void print(LinkedList<Integer> ll) {
		StringBuilder sb = new StringBuilder();

		for (int v : ll) {
			sb.append(v).append(" ");
		}

		System.out.print(sb.toString().trim());
	}

	static LinkedList<Integer> makeLinkedList(int n) {
		LinkedList<Integer> ll = new LinkedList<>();

		for (int i = 1; i <= n; i++) {
			ll.add(i);
		}

		return ll;
	}
}
