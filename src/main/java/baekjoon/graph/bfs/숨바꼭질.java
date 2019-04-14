package baekjoon.graph.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 숨바꼭질 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int k = scanner.nextInt();

		if (n > k) {
			System.out.println(n - k);
			return;
		}

		System.out.println(solve(n, k));
	}

	enum Direction {
		BACK {
			@Override
			int getNext(int x) {
				return x-1;
			}
		},
		FORWARD {
			@Override
			int getNext(int x) {
				return x+1;
			}
		},
		MULTIPLE {
			@Override
			int getNext(int x) {
				return x*2;
			}
		};

		abstract int getNext(int x);
	}

	static int solve(int n, int k) {
		boolean [] discovered = new boolean[1000001];

		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(n, 0));

		discovered[n] = true;

		while (!q.isEmpty()) {
			Pair pair = q.poll();

			if (pair.x == k) {
				return pair.time;
			}

			int x = pair.x;
			int time = pair.time;

			for (Direction d : Direction.values()) {
				int next = d.getNext(x);

				if (0 <= next && next <= 100000) {
					if (!discovered[next]) {
						q.add(new Pair(next, time+1));
						discovered[next] = true;
					}
				}
			}
		}

		return 0;
	}

	static class Pair {
		int x;
		int time;

		Pair(int x, int time) {
			this.x = x;
			this.time = time;
		}
	}
}
