package leetcode;

import java.util.*;

public class MostVisitedSectorInaCircularTrack {
	public List<Integer> mostVisited(int n, int[] rounds) {
		int [] sectors = new int[n];
		sectors[rounds[0]-1]++;

		for (int i = 1; i < rounds.length; i++) {
			int start = rounds[i-1];
			int end = rounds[i]-1;

			while ((start % (n+1)) != end) {

			}
		}
		
		int max = Integer.MIN_VALUE;
		List<Integer> res = new ArrayList<>();

		for (int i = 0; i < sectors.length; i++) {
			if (max < sectors[i]) {
				res = new ArrayList<>();
				res.add(i+1);
			} else if (max == sectors[i]) {
				res.add(i+1);
			}
		}

		return res;
	}

	public static void main(String[] args) {
		int n = 4;
		int [] rounds = {1,3,1,2};

		MostVisitedSectorInaCircularTrack mostVisitedSectorInaCircularTrack = new MostVisitedSectorInaCircularTrack();
		System.out.println(mostVisitedSectorInaCircularTrack.mostVisited(n, rounds));
	}
}
