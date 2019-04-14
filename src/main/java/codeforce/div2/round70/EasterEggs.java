package codeforce.div2.round70;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * http://codeforces.com/problemset/problem/78/B
 *
 * 아이디어) 주위 4개가 겹치지 않으면 된다고 하였으므로,
 *
 * 최초 한개가 온 이후에 세개만 다른 수가 온다면 그 다음수부터는 다시 똑같은 수가 와도 된다.
 *
 * 즉, 1,2,3,4,1,2,3,4 를 반복해서 이뤄나가면 네개 수 중에 절대 겹칠일이 없다.
 *
 * 다만 모든 7 개의 color 를 써야 한다는 조건이 있으므로, 수열을 모두 완성한 이후에
 *
 * 3가지 color 를 바꿔치기 해준다.
 */
public class EasterEggs {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		char [] colors = {
			'R',
			'O',
			'Y',
			'G',
		};


		Queue<Character> q = new LinkedList<Character>();

		for (int i = 0; i < n; i++) {
			int index = i % 4;

			q.add(colors[index]);
		}

		for (int i = 0; i < 3; i++) {
			q.poll();
		}


		((LinkedList<Character>)q).addFirst('B');
		((LinkedList<Character>)q).addFirst('I');
		((LinkedList<Character>)q).addFirst('V');

		StringBuilder sb = new StringBuilder();

		while (!q.isEmpty()) {
			sb.append(q.poll());
		}

		System.out.println(sb.toString());
	}
}
