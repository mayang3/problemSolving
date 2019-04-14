package baekjoon.segment_tree;

import java.util.Scanner;

public class 구간합구하기 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int K = scanner.nextInt();

		long [] input = new long[N];

		for (int i = 0; i < N; i++) {
			input[i] = scanner.nextLong();
		}

		FenwickTree fenwickTree = new FenwickTree(input, N);

		for (int i = 0; i < M + K; i++) {
			int cmd = scanner.nextInt();

			if (cmd == 1) {
				// update from a to b
				// 이 부분이 핵심이었고, 엄청 삽질했음..

				// 펜윅트리에서 값을 아예 바꿔치기 하기 위해서 기존 input 값과 비교를 해야 하는데,
				// 결국 input 의 값도 같이 바꿔줘야만 다음번에 제대로 된 결과를 가져올 수 있다!!
				int a = scanner.nextInt() - 1;
				long b = scanner.nextLong();
				long newVal = b - input[a];
				input[a] = b;
				fenwickTree.update(a, newVal);
			} else if (cmd == 2) {
				// sum a between b
				int a = scanner.nextInt()-2;
				int b = scanner.nextInt()-1;
				System.out.println(fenwickTree.getSum(b) - fenwickTree.getSum(a));
			}
		}
	}

	static class FenwickTree {
		long [] tree;

		FenwickTree(long [] input, int n) {
			tree = new long[n+1];

			for (int i = 0; i < n; i++) {
				update(i, input[i]);
			}
		}

		long getSum(int pos) {
			pos++;

			long ret = 0;

			// 모든 1 비트가 다 지워지면 pos 는 0 이 되므로 pos > 0 까지만 수행하면 된다.
			while (pos > 0) {
				// 1. 현재 구간을 더하고,
				ret += tree[pos];
				// 2. 다음 구간을 계속 찾아간다.
				// 최소 원소를 지우는 bit manipulation 방법..
				// ret-1 의 이진수 표현은 ret 에서 켜져 있는 최하위 비트들을 끄고,
				// 그 밑의 비트들을 전부 켠 것이다.
				pos &= (pos-1);
			}

			return ret;
		}

		void update(int pos, long val) {
			pos++;
			while (pos < tree.length) {
				tree[pos] += val;
				pos += (pos & -pos);
			}
		}
	}
}
