package leetcode;

import java.util.HashMap;
import java.util.Map;

public class DesignTicTacToe {
	public static void main(String[] args) {
		TicTacToe ticTacToe = new TicTacToe(3);
		System.out.println(ticTacToe.move(0, 0, 2)); // return 0 (no one wins)
		System.out.println(ticTacToe.move(2, 0, 2)); // return 0 (no one wins)
		System.out.println(ticTacToe.move(1, 0, 2)); // return 0 (no one wins)
	}
}


class TicTacToe {
	Map<Integer, Map<Integer, Integer>> rowMap = new HashMap<>();
	Map<Integer, Map<Integer, Integer>> colMap = new HashMap<>();
	Map<Integer, Integer> inclineDiagonalMap = new HashMap<>();
	Map<Integer, Integer> declineDiagonalMap = new HashMap<>();

	int n;
	/** Initialize your data structure here. */
	public TicTacToe(int n) {
		this.n = n;
	}

	/** Player {player} makes a move at ({row}, {col}).
	 @param row The row of the board.
	 @param col The column of the board.
	 @param player The player, can be either 1 or 2.
	 @return The current winning condition, can be either:
	 0: No one wins.
	 1: Player 1 wins.
	 2: Player 2 wins. */
	public int move(int row, int col, int player) {
		if (isWinning(row, col, player)) {
			return player;
		}

		rowMap.computeIfAbsent(player, t -> new HashMap<>()).merge(row, 1, Integer::sum);
		colMap.computeIfAbsent(player, t -> new HashMap<>()).merge(col, 1, Integer::sum);

		if (row == col) {
			inclineDiagonalMap.merge(player, 1, Integer::sum);

			if (n % 2 != 0 && row == n / 2) {
				declineDiagonalMap.merge(player, 1, Integer::sum);
			}
		} else if (row + col + 1 == n) {
			declineDiagonalMap.merge(player, 1, Integer::sum);
		}

		if (isWinning(row, col, player)) {
			return player;
		}

		return 0;
	}

	private boolean isWinning(int row, int col, int player) {
		if (rowMap.containsKey(player) && rowMap.get(player).getOrDefault(row, 0) == n) {
			return true;
		}

		if (colMap.containsKey(player) && colMap.get(player).getOrDefault(col, 0) == n) {
			return true;
		}

		if (inclineDiagonalMap.getOrDefault(player, 0) == n || declineDiagonalMap.getOrDefault(player, 0) == n) {
			return true;
		}

		return false;
	}
}