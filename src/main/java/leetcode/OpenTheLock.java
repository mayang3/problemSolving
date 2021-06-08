package leetcode;

import java.util.*;

public class OpenTheLock {
    static int[] TOGGLE = {-1, 1};

    public int openLock(String[] deadends, String target) {
        Set<String> visited = new HashSet<>();

        for (int i = 0; i < deadends.length; i++) {
            visited.add(deadends[i]);
        }

        char[] wheels = new char[4];
        Arrays.fill(wheels, '0');

        Queue<char[]> q = new LinkedList<>();
        q.add(wheels);

        if (visited.contains(String.valueOf(wheels))) {
            return -1;
        }

        int count = 0;

        while (q.isEmpty() == false) {
            int size = Integer.valueOf(q.size());

            for (int step = 0; step < size; step++) {
                char[] poll = q.poll();

                if (count > (int) 1e4) {
                    break;
                }

                if (String.valueOf(poll).equals(target)) {
                    return count;
                }

                for (int i = 0; i < 4; i++) {
                    for (int t : TOGGLE) {
                        int original = Character.getNumericValue(poll[i]);
                        int newValue = ((original == 0 ? 10 : original) + t) % 10;

                        char [] newPoll = Arrays.copyOf(poll, poll.length);
                        newPoll[i] = Character.forDigit(newValue, 10);
                        String newStr = String.valueOf(newPoll);

                        if (visited.contains(newStr) == false) {
                            visited.add(newStr);
                            q.add(newPoll);
                        }
                    }
                }
            }

            count++;
        }

        return -1;
    }

    public static void main(String[] args) {
        String[] deadends = {"0000"};
        String target = "8888";

        OpenTheLock openTheLock = new OpenTheLock();
        System.out.println(openTheLock.openLock(deadends, target));
    }
}
