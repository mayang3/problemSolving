package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinder {
	PriorityQueue<Double> max = new PriorityQueue<>(1000, Comparator.reverseOrder());
	PriorityQueue<Double> min = new PriorityQueue<>();

	public static void main(String[] args) {
		MedianFinder medianFinder = new MedianFinder();
		medianFinder.addNum(-1);
		medianFinder.addNum(-2);
		System.out.println(medianFinder.findMedian());
	}

	/** initialize your data structure here. */
	public MedianFinder() {

	}

	public void addNum(int num) {
		double dn = (double)num;

		if (max.size() > 0 && dn <= max.peek()) {
			max.add(dn);
		} else if (min.size() > 0 && min.peek() <= dn) {
			min.add(dn);
		} else if (max.size() <= min.size()){
			max.add(dn);
		} else {
			min.add(dn);
		}

		reBalance();
	}

	private void reBalance() {
		int diff = Math.abs(max.size() - min.size());

		if (diff < 2) {
			return;
		}

		if (max.size() < min.size()) {
			for (int i = 0; i < diff-1; i++) {
				max.add(min.poll());
			}
		} else {
			for (int i = 0; i < diff-1; i++) {
				min.add(max.poll());
			}
		}
	}

	public double findMedian() {
		boolean even = (max.size() + min.size()) % 2 == 0;

		double rMax = max.peek() == null ? 0D : max.peek();
		double rMin = min.peek() == null ? 0D : min.peek();

		if (even) {
			return (rMax + rMin) / 2;
		}

		if (max.size() < min.size()) {
			return rMin;
		}

		return rMax;
	}
}
