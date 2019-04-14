package strategies.graph.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * TODO 문제와 풀이 이해 못함 다시 볼것..
 *
 * 하노이의 탑 문제를 해결하는 양방향 탐색의 구현
 *
 * @author baejunbeom
 */
public class Hanoi4B_2 {

	final static int MAX_DISCS = 12;

	static int get(int state, int index) {
		return (state >> (index * 2)) & 3;
	}

	static int set(int state, int index, int value) {
		return (state & ~(3 << (index * 2))) | (value << (index * 2));
	}

	static int [] c = new int[1 << (MAX_DISCS * 2)];

	// x 의 부호를 반환한다.
	int sgn(int x) {
		if (x == 0) return 0; // C에서의 if (!x) 와 같음
		return x > 0 ? 1 : -1;
	}

	// x 의 절대값을 1 증가시킨다.
	int incr(int x) {
		if (x < 0) return x-1;
		return x+1;
	}

	// disc 개의 원반이 있고, 각 원반의 시작 위치와 목표 위치가 begin, end 에 주어질 때 최소 움직임의 수를 계산한다.
	int bidir(int discs, int begin, int end) {
		if (begin == end) return 0;

		Queue<Integer> q = new LinkedList<>();

		// 초기화를 0 으로 하는데 주의
		Arrays.fill(c, 0);

		// 정방향 탐색은 양수로, 역방향 탐색은 음수로 표시한다.
		q.add(begin); c[begin] = 1;
		q.add(end); c[end] = 1;

		while (!q.isEmpty()) {
			int here = q.poll();

			int [] top = {-1, -1, -1, -1};

			for (int i=discs-1 ; i>=0 ; i--) {
				top[get(here, i)] = i;
			}

			for (int i=0 ; i<4 ; i++) {
				if (top[i] != -1) {
					for (int j=0 ; j<4 ; j++) {
						if (i != j && (top[j] == -1 || top[j] > top[i])) {
							int there = set(here, top[i], j);

							// 아직 방문하지 않은 정점인 경우
							if (c[there] == 0) {
								c[there] = incr(c[here]);
								q.add(there);
							}
							// 가운데에서 벗어난 경우
							else if (sgn(c[there]) != sgn(c[here])) {
								return Math.abs(c[there]) + Math.abs(c[here]) - 1;
							}
						}
					}
				}
			}
		}

		return -1;
	}
}
