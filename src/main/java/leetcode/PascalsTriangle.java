package leetcode;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> res = new ArrayList<>();

		for (int level = 1; level <= numRows; level++) {
			List<Integer> list = new ArrayList<>();

			for (int i = 0; i < level; i++) {
				if (i == 0 || i == level-1) {
					list.add(1);
				} else {
					list.add(res.get(level-2).get(i-1) + res.get(level-2).get(i));
				}
			}

			res.add(list);
		}

		return res;
	}

	public static void main(String[] args) {
		PascalsTriangle pascalsTriangle = new PascalsTriangle();
		System.out.println(pascalsTriangle.generate(30));
	}
}
