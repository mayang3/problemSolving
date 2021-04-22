package leetcode;

import java.util.*;

public class DesignALeaderBoard {
    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();

        String [] op = {"addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","addScore","reset","addScore","reset","addScore","addScore","addScore","top","top","top","top","top","addScore","reset","reset","reset","reset","addScore","addScore","addScore","reset","addScore","reset","top","reset","reset"};
        int [][] params = {{1,17},{2,66},{3,18},{4,37},{5,59},{6,26},{7,22},{8,54},{9,4},{10,40},{11,93},{12,91},{13,10},{14,99},{15,3},{16,18},{17,19},{18,35},{19,61},{20,52},{21,46},{22,70},{23,90},{24,14},{25,60},{26,62},{27,8},{28,89},{29,72},{30,63},{31,61},{32,32},{33,72},{34,19},{35,45},{36,97},{37,12},{38,62},{39,55},{40,98},{41,48},{42,77},{43,91},{44,49},{45,25},{46,8},{47,14},{48,8},{49,89},{50,93},{1},{31,91},{2},{44,26},{3,60},{40,66},{39},{18},{32},{11},{1},{19,53},{3},{4},{5},{6},{48,32},{25,30},{16,2},{7},{21,69},{8},{13},{9},{10}};


        for (int i = 0; i < op.length; i++) {
            String cmd = op[i];

            System.out.println("COMMAND : " + cmd + " params : " + Arrays.toString(params[i]));

            if (cmd.equals("addScore")) {
                leaderboard.addScore(params[i][0], params[i][1]);
            } else if (cmd.equals("reset")) {
                leaderboard.reset(params[i][0]);
            } else if (cmd.equals("top")) {
                int top = leaderboard.top(params[i][0]);
                System.out.println(top);
            }
        }

    }


}

class Leaderboard {
    Map<Integer, Integer> playerMap = new HashMap<>();
    TreeMap<Integer, Set<Integer>> scoreMap = new TreeMap<>();

    public Leaderboard() {

    }

    public void addScore(int playerId, int score) {
        Integer beforeScore = playerMap.get(playerId);

        if (beforeScore != null) {
            scoreMap.get(beforeScore).remove(playerId);
            if (scoreMap.get(beforeScore).size() == 0) {
                scoreMap.remove(beforeScore);
            }
        }

        playerMap.merge(playerId, score, Integer::sum);
        scoreMap.computeIfAbsent(playerMap.get(playerId), t -> new HashSet<>()).add(playerId);
    }

    public int top(int K) {
        int sum = 0;

        for (int score : scoreMap.descendingKeySet()) {
            if (K <= 0) {
                break;
            }

            int count = scoreMap.get(score).size();
            sum += (score * Math.min(K, count));

            K = K - Math.min(K, count);
        }

        return sum;
    }

    public void reset(int playerId) {
        Integer beforeValue = playerMap.get(playerId);

        if (beforeValue != null) {
            scoreMap.get(beforeValue).remove(playerId);
            playerMap.remove(playerId);

            if (scoreMap.get(beforeValue).size() == 0) {
                scoreMap.remove(beforeValue);
            }
        }
    }
}
