package leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author neo82
 */
public class FindTheWinnerOfTheCircularGame {
    public static void main(String[] args) {
        FindTheWinnerOfTheCircularGame findTheWinnerOfTheCircularGame = new FindTheWinnerOfTheCircularGame();
        System.out.println(findTheWinnerOfTheCircularGame.findTheWinner(500, 500));
    }

    public int findTheWinner(int n, int k) {
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            deque.add(i);
        }

        while (deque.size() > 1) {
            for (int i = 0; i < k - 1; i++) {
                deque.add(deque.pollFirst());
            }

            deque.pollFirst();
        }

        return deque.getFirst();
    }
}
