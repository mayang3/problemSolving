package leetcode;

import java.util.*;

public class ReorganizingString_1 {

	public String reorganizeString(String S) {
		PriorityQueue<Pair> pq = new PriorityQueue<>((o1, o2) -> o2.cnt - o1.cnt);
		Map<Character, Pair> map = new HashMap<>();

		for (int i = 0; i < S.length(); i++) {
			char ch = S.charAt(i);
			Pair pair = map.computeIfAbsent(ch, t -> new Pair(ch, 0));
			pair.cnt += 1;
		}

		for (Pair pair : map.values()) {
			pq.add(pair);
		}

		StringBuilder sb = new StringBuilder();

		while (pq.isEmpty() == false) {
			List<Pair> tempList = new ArrayList<>();
			Pair next = getNextPair(pq, sb, tempList);

			if (next == null && tempList.size() > 0) {
				return "";
			}

			for (Pair p : tempList) {
				if (p.cnt > 0) pq.add(p);
			}

			sb.append(next.ch);
		}

		return sb.toString();
	}

	// 다음에 넣을 character 를 찾는다.
	// 출현 빈도 횟수가 가장 많은 character 부터 뽑는데, 다음과 같은 경우의 수가 있을 수 있다.
	// 1. 처음에 뽑힌 가장 많은 빈도의 character 가 현재 바로 앞의 string 과 겹치지 않는 경우
	// 2.               ""                가 현재 바로 앞의 string 과 겹치는 경우
	// 2-1. 겹쳐서 다음 character 를 다시 뽑았는데, 이 character 가 바로 앞의 string 과 겹치지 않는 경우 -> 두번째 character 를 넣고, 첫번째 character 도 다시 집어넣는다.
	// 2-2. 겹쳐서 다음 character 를 뽑아야 하는데, 뽑을 character 가 없는 경우 -> 불가능
	private Pair getNextPair(PriorityQueue<Pair> pq, StringBuilder sb, List<Pair> tempList) {
		Pair next = null;
		while (pq.isEmpty() == false && next == null) {
			Pair p = pq.poll();

			if (sb.length() == 0 || p.ch != sb.charAt(sb.length() - 1)) {
				next = p;
				p.cnt--;
			}

			tempList.add(p);
		}

		return next;
	}

	public static void main(String[] args) {
		ReorganizingString_1 reorganizingString1 = new ReorganizingString_1();
		String s = reorganizingString1.reorganizeString("aaaaaabbbcccd");

		System.out.println(s);
	}

	static class Pair {
		char ch;
		int cnt;

		public Pair(char ch, int cnt) {
			this.ch = ch;
			this.cnt = cnt;
		}
	}
}
