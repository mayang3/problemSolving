package leetcode;

import java.util.Map;
import java.util.TreeMap;

public class HitCounter {

	private int count;
	private TreeMap<Integer, Integer> treeMap;

	public HitCounter() {
		this.treeMap = new TreeMap<>();
	}

	public void hit(int timestamp) {
		this.count++;
		this.treeMap.put(timestamp, count);
	}

	public int getHits(int timestamp) {
		if (this.treeMap.isEmpty()) {
			return 0;
		}

		int upper = this.count;
		Map.Entry<Integer, Integer> lower = this.treeMap.floorEntry(timestamp - 300);

		if (lower != null) {
			return upper - lower.getValue();
		}

		return upper;
	}

	public static void main(String[] args) {
		HitCounter hc = new HitCounter();

		hc.hit(1);
		hc.hit(1);
		hc.hit(1);
		hc.hit(300);
		System.out.println(hc.getHits(300));
		hc.hit(300);
		System.out.println(hc.getHits(300));
		hc.hit(301);
		System.out.println(hc.getHits(301));

	}
}
