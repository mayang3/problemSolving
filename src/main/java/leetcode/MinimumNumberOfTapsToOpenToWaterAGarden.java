package leetcode;

import java.util.*;

public class MinimumNumberOfTapsToOpenToWaterAGarden {
    public int minTaps(int n, int[] ranges) {
        Tap [] taps = new Tap[n+1];

        for (int i = 0; i < ranges.length; i++) {
            taps[i] = new Tap(Math.max(0, i-ranges[i]), Math.min(n, i+ranges[i]));
        }

        Arrays.sort(taps, Comparator.comparingInt(o -> o.end));

        TreeMap<Integer, Integer> dp = new TreeMap<>();

        for (int i = 0; i < taps.length; i++) {
            int start = taps[i].start;
            int end = taps[i].end;

            int min = Integer.MAX_VALUE;

            if (start != 0) {
                SortedMap<Integer, Integer> subMap = dp.subMap(start, end);

                for (Map.Entry<Integer, Integer> entry : subMap.entrySet()) {
                    min = Math.min(min, entry.getValue());
                }
            }

            if (start == 0) {
                dp.put(end, 1);
            } else if (min != Integer.MAX_VALUE) {
                if (dp.containsKey(end)) {
                    dp.put(end, Math.min(dp.get(end), min+1));
                } else {
                    dp.put(end, min+1);
                }
            }
        }

        Map.Entry<Integer, Integer> lastEntry = dp.lastEntry();

        if (lastEntry.getKey() < n) {
            return -1;
        }

        return lastEntry.getValue();
    }

    static class Tap {
        int start;
        int end;

        public Tap(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) {
        int n = 9;
        int[] ranges = {0,5,0,3,3,3,1,4,0,4};

        MinimumNumberOfTapsToOpenToWaterAGarden minimumNumberOfTapsToOpenToWaterAGarden = new MinimumNumberOfTapsToOpenToWaterAGarden();
        System.out.println(minimumNumberOfTapsToOpenToWaterAGarden.minTaps(n, ranges));
    }
}
