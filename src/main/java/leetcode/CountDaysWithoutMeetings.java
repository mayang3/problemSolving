package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author neo82
 */
public class CountDaysWithoutMeetings {
    public int countDays(int days, int[][] meetings) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));

        pq.add(new int[]{days + 1, days + 1});

        for (int[] meeting : meetings) {
            pq.add(meeting);
        }

        int beforeEnd = 0;
        int ans = 0;

        while (!pq.isEmpty()) {
            int[] here = pq.poll();

            int start = here[0];
            int end = here[1];

            while (!pq.isEmpty() && pq.peek()[0] <= end) {
                end = Math.max(end, pq.poll()[1]);
            }

            if (start > beforeEnd) {
                ans += (start - beforeEnd - 1);
            }

            beforeEnd = end;
        }

        return ans;
    }

    public static void main(String[] args) {
        int days = 4;
        int[][] meetings = {{2,3},{1,2},{2,3},{2,4},{1,2},{1,3}};

        CountDaysWithoutMeetings countDaysWithoutMeetings = new CountDaysWithoutMeetings();
        System.out.println(countDaysWithoutMeetings.countDays(days, meetings));
    }
}
