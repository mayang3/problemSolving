package strategies;

/**
 * @author baejunbeom
 */
public class BoardCover {

	static final boolean [][] BORAD = {
		{false, true, true, true, true, true, false},
		{false, true, true, true, true, true, false},
		{false, false, true, true, false, false, false}
	};

	static final int [][][] COVER_TYPE = {
		{{0,0}, {1,0}, {0,1}}, // 그림 b
		{{0,0}, {0,1}, {1,1}}, // 그림 c
		{{0,0}, {1,0}, {1,1}}, // 그림 d
		{{0,0}, {1,0}, {1,-1}} // 그림 e
	};

	/**
	 *
	 * @param board board array
	 * @return
	 */
	public int countAll(boolean[][] board) {
		if (isMultiply3(board) == false) {
			return 0;
		}

		return count(0, 0, board);
	}

	static class StartPoint {
		int x;
		int y;

		StartPoint(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private int count(int x, int y, boolean[][] board) {

		StartPoint startPoint = getStartPoint(x, y, board);

		// base case : 모든 좌표가 마킹되었다면 count 1 증가
		if (startPoint == null) {
			return 1;
		}

		int startX = startPoint.x;
		int startY = startPoint.y;

		int ret = 0;

		// 마킹할 부분이 남았다면 4가지 방향에 대해 모두 시도해본다.
		for (int i = 0 ; i<4 ; i++) {
			int x1 = startX + COVER_TYPE[i][0][0];
			int y1 = startY + COVER_TYPE[i][0][1];

			int x2 = startX + COVER_TYPE[i][1][0];
			int y2 = startY + COVER_TYPE[i][1][1];

			int x3 = startX + COVER_TYPE[i][2][0];
			int y3 = startY + COVER_TYPE[i][2][1];

			// 모드 흰 칸인 경우,
			if (board[x1][y1] && board[x2][y2] && board[x3][y3]) {
				// 흰칸을 마킹하고
				board[x1][y1] = board[x2][y2] = board[x3][y3] = false;
				// 다음칸을 진행한다.
				ret += count(startX, startY, board);
				// 끝났으면 현재 칸을 다시 초기화 해준다.
				board[x1][y1] = board[x2][y2] = board[x3][y3] = true;
			}
		}

		return ret;
	}

	private StartPoint getStartPoint(int x, int y, boolean[][] board) {
		for (int i=x ; i<board.length ; i++) {
			// 주의! j=y 부터 시작하면 안된다.
			// 왜냐하면 다음줄에 내려왔을때, j=0부터 흰칸이 존재할 수도 있기 때문이다.
			// i=x 부터 시작해도 된다.
			for (int j=0 ; j<board[i].length ; j++) {
				if (board[i][j]) {
					return new StartPoint(i, j);
				}
			}
		}

		return null;
	}

	private boolean isMultiply3(boolean[][] board) {
		int whitePointCount = 0;

		for (int i=0 ; i<board.length ; i++) {
			for (int j=0 ; j<board[i].length ; j++) {
				if (board[i][j]) {
					whitePointCount++;
				}
			}
		}

		return whitePointCount % 3 == 0;
	}

	public static void main(String[] args) {
		BoardCover boardCover = new BoardCover();
		int i = boardCover.countAll(BORAD);

		System.out.println(i);
	}

}
