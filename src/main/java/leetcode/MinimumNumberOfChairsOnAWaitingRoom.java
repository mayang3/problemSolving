package leetcode;

/**
 * @author neo82
 */
public class MinimumNumberOfChairsOnAWaitingRoom {
    public int minimumChairs(String s) {
        int count = 0;
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'E') {
                count++;
            } else {
                count--;
            }

            max = Math.max(max, count);
        }

        return max;

    }

    public static void main(String[] args) {
        MinimumNumberOfChairsOnAWaitingRoom minimumNumberOfChairsOnAWaitingRoom = new MinimumNumberOfChairsOnAWaitingRoom();
        System.out.println(minimumNumberOfChairsOnAWaitingRoom.minimumChairs("L"));
    }
}
