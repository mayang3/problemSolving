package strategies.greed;

import java.util.*;

/**
 * 출전 순서 정하기 문제를 해결하는 탐욕적 알고리즘
 *
 * @author baejunbeom
 */
public class MatchOrder {

	static int order(List<Integer> russian, List<Integer> korean) {
		int n = russian.size();
		int wins = 0;

		TreeSet<Integer> ratings = new TreeSet<>(korean);

		// 아직 남아 있는 선수들의 레이팅
		for (int rus = 0 ; rus < n ; rus++) {
			// 가장 레이팅이 높은 한국 선수가 이길 수 없는 경우
			// 가장 레이팅이 낮은 선수와 경기시킨다
			if (ratings.last() < russian.get(rus)) {
				System.out.println(ratings.pollFirst());
			} else {
				// 이 외의 경우 이길 수 있는 선수 중 가장 레이팅이 낮은 선수와 경기시킨다.
				Integer ceiling = ratings.ceiling(russian.get(rus));
				System.out.println(ceiling);
				ratings.remove(ceiling);
				wins++;
			}
		}

		return wins;
	}

	public static void main(String[] args) {
		List<Integer> russian = new ArrayList<>();
		russian.add(3000);
		russian.add(2700);
		russian.add(2800);
		russian.add(2200);
		russian.add(2500);
		russian.add(1900);

		List<Integer> korean = new ArrayList<>();
		korean.add(2800);
		korean.add(2750);
		korean.add(2995);
		korean.add(1800);
		korean.add(2600);
		korean.add(2000);

		int ret = order(russian, korean);

		System.out.println(ret);
	}
}
