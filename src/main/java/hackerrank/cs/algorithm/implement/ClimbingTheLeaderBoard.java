package hackerrank.cs.algorithm.implement;

import java.util.Scanner;

public class ClimbingTheLeaderBoard {
	static int [] scores;
	static int [] rankings;
	static int [] alice;


	static void solve() {
		for (int i=0 ; i<alice.length ; i++) {
			int rankIdx = bs(0, scores.length-1, alice[i]);

			if (scores[rankIdx] <= alice[i]) { // score 가 작더라도 현재 랭킹을 가져가고, 현재 랭킹에 있던 녀석은 밀려나기 때문에, 현재 랭킹을 찍어준다.
				System.out.println(rankings[rankIdx]);
			} else if (scores[rankIdx] > alice[i]){ //
				System.out.println(rankings[rankIdx] + 1);
			}
		}
	}

	private static int bs(int l, int r, int aliceScore) {
		if (r-l <= 1) {
			if (aliceScore == scores[r] || aliceScore < scores[r]) {
				return r;
			} else if (aliceScore == scores[l]) {
				return l;
			} else if (scores[l] < aliceScore && aliceScore < scores[r]) {
				return l;
			} else if (aliceScore > scores[l]) {
				return l;
			}
		}

		int mid = (l + r) / 2;

		if (scores[mid] == aliceScore) {
			return mid;
		} else if (scores[mid] < aliceScore) {
			return bs(l, mid, aliceScore);
		} else if (scores[mid] > aliceScore) {
			return bs(mid+1, r, aliceScore);
		}

		return -1;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();

		scores = new int[N];
		rankings = new int[N];


		int rank = 1;
		rankings[0] = rank;


		for (int i=0 ; i<N ; i++) {
			scores[i] = scanner.nextInt();

			if (i == 0) {
				continue;
			}

			if (scores[i] == scores[i-1]) {
				rankings[i] = rank;
			} else {
				rankings[i] = ++rank;
			}
		}

		int M = scanner.nextInt();

		alice = new int[M];

		for (int i=0 ; i<M ; i++) {
			alice[i] = scanner.nextInt();
		}

		solve();
	}
}
