package strategies.exhaustiveSearch;

/**
 * @author baejunbeom
 */
public class Boggle {

	int [] dx = {-1, -1, -1, 1, 1, 1, 0, 0};
	int [] dy = {-1, 0, 1, -1, 0, 1, -1, 1};

	public boolean hasWord(char [][] board, int x, int y, String word) {
		char[] chars = word.toCharArray();

		// 범위밖이면 무조건 false
		if (isRange(board, x,y) == false) {
			return false;
		}

		// x,y 에 있는 글자가 원하는 단어의 첫글자가 아닌 경우 항상 실패
		if (board[x][y] != chars[0]) {
			return false;
		}

		// 원하는 단어가 한 글자인 경우 항상 성공
		if (chars.length == 1) {
			return true;
		}

		for (int direction = 0 ; direction < 8 ; direction++) {

			int nextX = x + dx[direction];
			int nextY = y + dy[direction];

			if (hasWord(board, nextX, nextY, word.substring(1))) {
				return true;
			}
		}

		return false;
	}

	private boolean isRange(char[][] board, int x, int y) {
		if (x < board.length && x >= 0) {
			return true;
		}

		if (y >= 0 && y < board[0].length) {
			return true;
		}

		return false;
	}

	public static void main(String[] args) {
		char [][] board = {
			{'N', 'N', 'N', 'N', 'S'},
			{'N', 'E', 'E', 'E', 'N'},
			{'N', 'E', 'Y', 'E', 'N'},
			{'N', 'E', 'E', 'E', 'N'},
			{'N', 'N', 'N', 'N', 'N'}
		};

		Boggle boggle = new Boggle();
		boolean yes = boggle.hasWord(board, 2, 2, "YES");

		System.out.println(yes);
	}
}
