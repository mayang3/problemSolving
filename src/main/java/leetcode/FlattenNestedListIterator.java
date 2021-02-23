package leetcode;

import java.util.*;

public class FlattenNestedListIterator {

	public static void main(String[] args) {
		List<NestedInteger> list1 = new ArrayList<>();
		List<NestedInteger> list2 = new ArrayList<>();


		List<NestedInteger> nestedIntegers = new ArrayList<>();
		nestedIntegers.add(new NestedIntegerImpl(list1));
		nestedIntegers.add(new NestedIntegerImpl(list2));
		nestedIntegers.add(new NestedIntegerImpl(-1));


		NestedIterator nestedIterator = new NestedIterator(nestedIntegers);
		System.out.println(nestedIterator.next());
	}

	static class NestedIntegerImpl implements NestedInteger {
		Integer val;
		List<NestedInteger> nestedIntegerList;

		public NestedIntegerImpl(Integer val) {
			this.val = val;
		}

		public NestedIntegerImpl(List<NestedInteger> nestedIntegerList) {
			this.nestedIntegerList = nestedIntegerList;
		}

		@Override
		public boolean isInteger() {
			return this.val != null;
		}

		@Override
		public Integer getInteger() {
			return this.val;
		}

		@Override
		public List<NestedInteger> getList() {
			return this.nestedIntegerList;
		}
	}
	// This is the interface that allows for creating nested lists.
	// You should not implement it, or speculate about its implementation
	public interface NestedInteger {

		// @return true if this NestedInteger holds a single integer, rather than a nested list.
		public boolean isInteger();

		// @return the single integer that this NestedInteger holds, if it holds a single integer
		// Return null if this NestedInteger holds a nested list
		public Integer getInteger();

		// @return the nested list that this NestedInteger holds, if it holds a nested list
		// Return null if this NestedInteger holds a single integer
		public List<NestedInteger> getList();
	}

	public static class NestedIterator implements Iterator<Integer> {

		private Stack<NestedInteger> stack;

		public NestedIterator(List<NestedInteger> nestedList) {
			this.stack = new Stack<>();

			for (int i = nestedList.size() - 1; i >= 0; i--) {
				this.stack.push(nestedList.get(i));
			}
		}

		@Override
		public Integer next() {
			prepare();
			// this operation always ensure return integer value
			return stack.pop().getInteger();
		}

		@Override
		public boolean hasNext() {
			prepare();
			return stack.isEmpty() == false;
		}

		private void prepare() {
			while (stack.isEmpty() == false && stack.peek().isInteger() == false) {
				List<NestedInteger> list = stack.pop().getList();

				for (int i = list.size() - 1; i >= 0 ; i--) {
					this.stack.push(list.get(i));
				}
			}
		}
	}
}
