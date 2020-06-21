package leetcode;

import java.util.*;

/**
 * Online Stock Spanner
 */
public class StockSpanner {

	private Stack<Pair> stack;

	static class Pair {
		int price;
		int count;

		public Pair(int price, int count) {
			this.price = price;
			this.count = count;
		}
	}

	public StockSpanner() {
		this.stack = new Stack<>();
	}

	public int next(int price) {
		if (stack.isEmpty()) {
			stack.push(new Pair(price, 1));
			return 1;
		}

		int count = 1;

		while (!stack.isEmpty() && stack.peek().price <= price) {
			count += stack.pop().count;
		}

		stack.add(new Pair(price, count));

		return count;
	}

	public static void main(String[] args) {
		StockSpanner sp = new StockSpanner();

		System.out.println(sp.next(100));
		System.out.println(sp.next(80));
		System.out.println(sp.next(60));
		System.out.println(sp.next(70));
		System.out.println(sp.next(60));
		System.out.println(sp.next(75));
		System.out.println(sp.next(85));
	}
}
