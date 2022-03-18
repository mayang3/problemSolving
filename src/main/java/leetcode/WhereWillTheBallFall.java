package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class WhereWillTheBallFall {
	public int[] findBall(int[][] grid) {
		int M = grid.length;
		int N = grid[0].length;

		Queue<Ball> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			q.add(new Ball(i, 0, i));
		}

		int [] answer = new int[N];

		Arrays.fill(answer, -1);

		while (q.isEmpty() == false) {
			Ball ball = q.poll();

			int hereY = ball.y;
			int hereX = ball.x;

			if (isPossible(hereY, hereX, grid, N)) {
				Ball newBall;

				if (grid[hereY][hereX] == 1) {
					newBall = new Ball(ball.num, hereY+1, hereX+1);
				} else {
					newBall = new Ball(ball.num, hereY+1, hereX-1);
				}

				if (hereY == M-1) {
					answer[newBall.num] = newBall.x;
				} else {
					q.add(newBall);
				}
			}
		}

		return answer;
	}

	boolean isPossible(int hereY, int hereX, int[][] grid, int N) {
		if (hereX < N - 1 && grid[hereY][hereX] == 1 && grid[hereY][hereX+1] == 1) {
			return true;
		} else if (hereX > 0 && grid[hereY][hereX] == -1 && grid[hereY][hereX-1] == -1) {
			return true;
		}

		return false;
	}

	static class Ball {
		int num;
		int y;
		int x;

		public Ball(int num, int y, int x) {
			this.num = num;
			this.y = y;
			this.x = x;
		}
	}

	public static void main(String[] args) {
		int [][] grid = {{1,1,1,1,1,1},{-1,-1,-1,-1,-1,-1},{1,1,1,1,1,1},{-1,-1,-1,-1,-1,-1}};

		WhereWillTheBallFall whereWillTheBallFall = new WhereWillTheBallFall();
		System.out.println(Arrays.toString(whereWillTheBallFall.findBall(grid)));
	}
}
