package leetcode;

// minimax algorithm, with alpha,beta pruning
// 공부한 기념으로 한번 구현해봄
// 물론 문제는 이렇게 풀면 경우의 수가 너무 많아져서 TLE 가 발생한다.
// 문제 자체는 Math 문제
public class SumGame {
    public boolean sumGame(String num) {
        int bestVal = minimax(num.toCharArray(), 0, 0, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return bestVal > 0;
    }

    int minimax(char[] nums, int i, int depth, boolean maximizer, int alpha, int beta) {
        int score = evaluate(nums);

        if (score == 1000) {
            return score;
        }

        if (score == -1000) {
            return score;
        }

        if (maximizer) {
            int best = Integer.MIN_VALUE;

            for (int j = i; j < nums.length; j++) {
                if (nums[j] == '?') {
                    for (int k = 0; k < 10; k++) {
                        nums[j] = Character.forDigit(k, 10);
                        int val = minimax(nums, j + 1, depth + 1, false, alpha, beta);
                        best = Math.max(best, val);
                        alpha = Math.max(best, alpha);
                        nums[j] = '?';

                        if (beta <= alpha) {
                            break;
                        }
                    }

                    break;
                }
            }

            return best;
        } else {
            int best = Integer.MAX_VALUE;

            for (int j = i; j < nums.length; j++) {
                if (nums[j] == '?') {
                    for (int k = 0; k < 10; k++) {
                        nums[j] = Character.forDigit(k, 10);
                        int val = minimax(nums, j + 1, depth + 1, true, alpha, beta);
                        best = Math.min(best, val);
                        beta = Math.min(beta, best);
                        nums[j] = '?';

                        if (beta <= alpha) {
                            break;
                        }
                    }
                    break;
                }
            }

            return best;
        }
    }

    private int evaluate(char[] nums) {
        int left = 0;
        int right = nums.length - 1;

        int sum = 0;

        while (left < right) {
            if (nums[left] == '?' || nums[right] == '?') {
                return 0;
            }

            sum += Character.getNumericValue(nums[left]);
            sum -= Character.getNumericValue(nums[right]);

            left++;
            right--;
        }

        return sum != 0 ? 1000 : -1000;
    }

    public static void main(String[] args) {
        SumGame sumGame = new SumGame();
        System.out.println(sumGame.sumGame("25??"));
    }
}
