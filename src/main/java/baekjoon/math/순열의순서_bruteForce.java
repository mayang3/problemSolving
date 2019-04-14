package baekjoon.math;

import java.util.*;

public class 순열의순서_bruteForce {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int operation = scanner.nextInt();

		if (operation == 1) {
			new Permutation(n, scanner.nextLong()).printPerm();
		} else if (operation == 2) {
			int [] specificArr = new int[n];

			for (int i = 0; i < n; i++) {
				specificArr[i] = scanner.nextInt();
			}
			new Permutation(n, specificArr).printPerm();
		}

	}

	static class Permutation {
		int n;
		long k;
		int [] arr;
		int [] specificArr;

		Permutation(int n, long k) {
			this.n = n;
			this.k = k;
			this.arr = new int[n];
		}

		Permutation(int n, int [] specificArr) {
			this.n = n;
			this.arr = new int[n];
			this.specificArr = specificArr;
		}

		void printPerm() {
			LinkedList<Integer> ll = new LinkedList<>();

			for (int i = 1; i <= n; i++) {
				ll.add(i);
			}

			if (specificArr != null) {
				printSpecificPerm(ll, n);
			} else {
				printKPerm(ll, n);
			}
		}

		/**
		 * 특정 순열에 매칭되는 k 구하기
		 * @param ll
		 * @param n
		 */
		void printSpecificPerm(LinkedList<Integer> ll, int n) {
			if (n == 0) {
				k++;
				if (isEqualArr(arr)) {
					System.out.println(this.k);
				}
				return;
			}

			int size = new Integer(ll.size());

			for (int i = 0; i < size; i++) {
				int first = ll.remove(i);
				arr[arr.length-n] = first;
				printSpecificPerm(ll, n-1);
				ll.add(i, first);
			}
		}

		private boolean isEqualArr(int[] arr) {
			for (int i = 0; i < arr.length; i++) {
				if (this.specificArr[i] != arr[i]) {
					return false;
				}
			}

			return true;
		}

		/**
		 * k 에 매칭되는 순열 구하기
		 * @param ll
		 * @param n
		 */
		void printKPerm(LinkedList<Integer> ll, int n) {
//			if (k == 0) {
//				return;
//			}

			if (n == 0) {
				k--;
				if (k == 0) {
//					print(arr);
				}

				System.out.println(Arrays.toString(arr));
				return;
			}

			int size = new Integer(ll.size());

			for (int i = 0; i < size; i++) {
				int first = ll.remove(i);
				arr[arr.length-n] = first;
				printKPerm(ll, n-1);
				ll.add(i, first);
			}
		}

		void print(int [] arr) {
			for (int v : arr) {
				System.out.print(v + " ");
			}
		}
	}

}
