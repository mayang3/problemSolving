package leetcode;

import java.util.Arrays;
import java.util.LinkedList;

public class QueueReconstructionByHeight {
	public int[][] reconstructQueue(int[][] people) {
		Arrays.sort(people, (p1, p2) -> p1[0] == p2[0] ? p1[1] - p2[1] : p2[0] - p1[0]);

		LinkedList<int[]> list = new LinkedList<>();

		for (int i = 0; i < people.length; i++)
			list.add(people[i][1], people[i]);

		return list.toArray(people);
	}

	public static void main(String[] args) {
		int [][] people = {
			{7,0},
			{4,4},
			{7,1},
			{5,0},
			{6,1},
			{5,2}
		};


		QueueReconstructionByHeight height = new QueueReconstructionByHeight();
		int[][] ret = height.reconstructQueue(people);

		System.out.println(Arrays.deepToString(ret));
	}

}
