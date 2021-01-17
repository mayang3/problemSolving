package leetcode;

import java.util.HashMap;
import java.util.Map;

public class PairsOfSongsWithTotalDurationsDivisibleBy60 {
	public int numPairsDivisibleBy60(int[] time) {
		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < time.length; i++) {
			time[i] %= 60;
			map.merge(time[i], 1, Integer::sum);
		}

		int count = 0;

		for (int i = 0; i < time.length; i++) {
			map.merge(time[i], -1, Integer::sum);

			if (map.get(time[i]) == 0) {
				map.remove(time[i]);
			}

			int another = map.getOrDefault(60 - time[i] == 60 ? 0 : 60 - time[i], 0);

			if (another > 0) {
				count += another;
			}
		}

		return count;
	}

	public static void main(String[] args) {
		int [] times = {30,20,150,100,40, 60, 60};

		PairsOfSongsWithTotalDurationsDivisibleBy60 pairs = new PairsOfSongsWithTotalDurationsDivisibleBy60();
		System.out.println(pairs.numPairsDivisibleBy60(times));
	}
}
