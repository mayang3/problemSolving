package leetcode;

/**
 * @author neo82
 */
public class AverageWaitingTime {
    public double averageWaitingTime(int[][] customers) {
        int end = 0;
        long sum = 0;

        for (int[] customer : customers) {
            int arrive = customer[0];
            int time = customer[1];

            if (arrive >= end) {
                sum += time;
                end = arrive + time;
            } else {
                sum += (end - arrive + time);
                end += time;
            }
        }

        return (double) sum / customers.length;
    }
}
