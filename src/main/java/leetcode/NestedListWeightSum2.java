package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NestedListWeightSum2 {

	public static class NestedInteger {
		List<NestedInteger> nestedIntegerList = new ArrayList<>();
		int value;

		public NestedInteger() {}

		public NestedInteger(int value) {
			this.value = value;
		}

		public boolean isInteger() {
			return nestedIntegerList.size() == 0;
		}

		public Integer getInteger() {
			return this.value;
		}

		public void setInteger(int value) {
			this.value = value;
		}

		public void add(NestedInteger ni) {
			nestedIntegerList.add(ni);
		}

		public List<NestedInteger> getList() {
			return this.nestedIntegerList;
		}
	}

	public int depthSumInverse(List<NestedInteger> nestedList) {
		int maxDepth = findMaxDepth(nestedList);

		return solve(nestedList, maxDepth);
	}

	public int solve(List<NestedInteger> nestedList, int depth) {

		int sum = 0;

		for (NestedInteger ni : nestedList) {
			if (ni.isInteger()) {
				sum += (depth * ni.getInteger());
			} else {
				sum += solve(ni.getList(), depth-1);
			}
		}

		return sum;
	}

	public int findMaxDepth(List<NestedInteger> nestedList) {
		if (nestedList == null || nestedList.size() == 0) {
			return 0;
		}

		int maxDepth = 1;

		for (NestedInteger ni : nestedList) {
			if (ni.isInteger() == false) {
				maxDepth = Math.max(maxDepth, findMaxDepth(ni.getList()) + 1);
			}
		}

		return maxDepth;
	}

	// [[1,1],2,[1,1]]
	public static void main(String[] args) {
		NestedInteger first2 = new NestedInteger();
		first2.add(new NestedInteger(1));
		first2.add(new NestedInteger(1));

		NestedInteger first = new NestedInteger();
		first.add(first2);

		NestedInteger second2 = new NestedInteger();


		NestedInteger second = new NestedInteger();
		second.add(second2);

		List<NestedInteger> root = new ArrayList<>();
		root.add(first);
		root.add(second);

		NestedListWeightSum2 nestedListWeightSum2 = new NestedListWeightSum2();
		int res = nestedListWeightSum2.depthSumInverse(root);

		System.out.println(res);
	}
}
