package leetcode;

import java.util.*;

public class ReTurn {
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < barcodes.length; i++) {
            map.merge(barcodes[i], 1, Integer::sum);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(entry);
        }

        int [] output = new int[barcodes.length];
        int i = 0;

        while (pq.isEmpty() == false) {
            Map.Entry<Integer, Integer> entry = pq.poll();
            Map.Entry<Integer, Integer> entry2 = null;

            if (i > 0 && entry.getKey() == output[i-1]) {
                entry2 = entry;
                entry = pq.poll();
            }

            int num = entry.getKey();
            int count = entry.getValue();

            output[i++] = num;

            if (count > 1) {
                entry.setValue(count-1);
                pq.add(entry);
            }

            if (entry2 != null) {
                pq.add(entry2);
            }
        }

        return output;
    }

    public static void main(String[] args) {
        int [] barcodes = {1,1,1,1,2,2,3,3};

        ReTurn reTurn = new ReTurn();
        System.out.println(Arrays.toString(reTurn.rearrangeBarcodes(barcodes)));
    }
}
