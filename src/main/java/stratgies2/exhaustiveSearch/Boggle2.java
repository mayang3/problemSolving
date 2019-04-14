package stratgies2.exhaustiveSearch;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Boggle2 {
	static char[][] board = {
		{'U', 'R', 'L', 'P', 'M'},
		{'X', 'P', 'R', 'E', 'T'},
		{'G', 'I', 'A', 'E', 'T'},
		{'X', 'T', 'N', 'Z', 'Y'},
		{'X', 'O', 'Q', 'R', 'S'}
	};

	static char[] word;
	// 8 방향
	static List<Direction> directions = new ArrayList<>();

	static {
		directions.add(new Direction(0,1));
		directions.add(new Direction(1,1));
		directions.add(new Direction(1,0));
		directions.add(new Direction(1,-1));
		directions.add(new Direction(0,-1));
		directions.add(new Direction(-1,-1));
		directions.add(new Direction(-1,0));
		directions.add(new Direction(-1,1));
	}

	static class Direction {
		int x;
		int y;

		Direction(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	/**
	 * word 의 길이를 n 이라고 했을 때, 시간복잡도는 O(8^n)이 된다.
	 *
	 * @param x
	 * @param y
	 * @param i
	 * @return
	 */
	static boolean hasWord(int x, int y, int i) {
		if (x > board.length -1 || x < 0) {
			return false;
		}

		if (y > board[x].length -1 || y < 0) {
			return false;
		}

		if (i >= word.length) {
			return true;
		}

		if (board[x][y] != word[i]) {
			return false;
		}

		for (Direction direction : directions) {
			if (hasWord(x + direction.x, y + direction.y, i+1)) {
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {
//		word = "PRETTY".toCharArray();
		word = "GIRL".toCharArray();

		System.out.println(hasWord(2, 0, 0));
	}
}
