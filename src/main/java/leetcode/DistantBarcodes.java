
package leetcode;

import java.util.*;

public class DistantBarcodes {
	public int[] rearrangeBarcodes(int[] barcodes) {
		PriorityQueue<List<Integer>> pq = new PriorityQueue<>((o1, o2) -> o2.size() - o1.size());

		Arrays.sort(barcodes);

		List<Integer> list = new LinkedList<>();

		for (int i = 0; i < barcodes.length; i++) {
			list.add(barcodes[i]);

			if (i != barcodes.length -1 && barcodes[i] != barcodes[i+1]) {
				pq.add(list);
				list = new ArrayList<>();
			}
		}

		pq.add(list);

		int [] res = new int[barcodes.length];

		int i = 0;
		int before = 0;

		while (pq.isEmpty() == false) {
			List<List<Integer>> tempList = new ArrayList<>();

			while (pq.peek().get(0) == before) {
				tempList.add(pq.poll());
			}

			List<Integer> poll = pq.poll();

			int remove = poll.remove(0);

			res[i++] = remove;
			before = remove;

			if (poll.size() > 0) {
				pq.add(poll);
			}

			for (List<Integer> temp : tempList) {
				pq.add(temp);
			}

		}

		return res;
	}

	public static void main(String[] args) {
		int [] barcodes = {1,1,1,2,2,2};

		DistantBarcodes distantBarcodes = new DistantBarcodes();

		System.out.println(Arrays.toString(distantBarcodes.rearrangeBarcodes(barcodes)));
	}
}
