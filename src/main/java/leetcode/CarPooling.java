package leetcode;

import java.util.HashMap;
import java.util.Map;

public class CarPooling {
    public boolean carPooling(int[][] trips, int capacity) {
        Map<Integer, Integer> start = new HashMap<>();
        Map<Integer, Integer> end = new HashMap<>();

        for (int [] trip : trips) {
            start.put(trip[1], start.getOrDefault(trip[1], 0) + trip[0]);
            end.put(trip[2], end.getOrDefault(trip[2], 0) + trip[0]);
        }

        for (int i = 0; i < 1001; i++) {
            if (end.containsKey(i)) {
                capacity += end.get(i);
            }

            if (start.containsKey(i)) {
                capacity -= start.get(i);
            }

            if (capacity < 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int [][] trips = {{9,3,4},{9,1,7},{4,2,4},{7,4,5}};
        int c = 23;

        CarPooling carPooling = new CarPooling();
        System.out.println(carPooling.carPooling(trips, c));
    }
}
