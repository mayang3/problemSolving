package leetcode;

import java.util.TreeSet;

public class MaximizeDistanceToClosestPerson {
	public int maxDistToClosest(int[] seats) {
		int n = seats.length;

		TreeSet<Integer> treeSet = new TreeSet<>();

		for (int i = 0; i < n; i++) {
			if (seats[i] == 1) {
				treeSet.add(i);
			}
		}

		int max = 0;

		for (int i = 0; i < n; i++) {
			if (seats[i] == 0) {
				Integer higher = treeSet.higher(i);
				Integer lower = treeSet.lower(i);

				int leftDist = lower == null ? Integer.MAX_VALUE : i - lower;
				int rightDist = higher == null ? Integer.MAX_VALUE : higher - i;

				max = Math.max(max, Math.min(leftDist, rightDist));
			}
		}

		return max;
	}

	public static void main(String[] args) {
		int [] seats = {1,0};

		MaximizeDistanceToClosestPerson maximizeDistanceToClosestPerson = new MaximizeDistanceToClosestPerson();
		System.out.println(maximizeDistanceToClosestPerson.maxDistToClosest(seats));
	}
}

