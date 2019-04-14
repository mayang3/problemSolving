package strategies.greed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author baejunbeom
 */
public class ActivitySelectionProblem {

	static int n;
	static int [] begin = new int[100];
	static int [] end = new int[100];

	static class Pair {
		int begin;
		int end;

		Pair(int begin, int end) {
			this.begin = begin;
			this.end = end;
		}
	}

	static int schedule() {
		// 일찍 끝나는 순서대로 정렬한다.
		List<Pair> order = new ArrayList<>();

		for (int i=0 ; i<n ; i++) {
			order.add(new Pair(end[i], begin[i]));
		}

		Collections.sort(order, (o1, o2) -> {
			if (o1.end == o2.end) return 0;
			return o1.end < o2.end ? -1 : 1;
		});

		// earliest : 다음 회의가 시작할 수 있는 가장 빠른 시간
		// selected : 지금까지 선택한 회의의 수
		int earliest = 0, selected = 0;

		for (int i=0 ; i<order.size() ; i++) {
			int meetingBegin = order.get(i).begin;
			int meetingEnd = order.get(i).end;

			if (earliest <= meetingBegin) {
				// earliest 를 마지막 회의가 끝난 시간 이후로 갱신한다.
				earliest = meetingEnd;
				++selected;
			}
		}

		return selected;
	}

	public static void main(String[] args) {

	}
}
