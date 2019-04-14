package stratgies2.dp_advance;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {

	// turn 이 한 줄을 만들었는지를 반환한다.
	static boolean isFinished(char [][] board, char turn) {
		// 1. 가로로 한줄을 만드는 경우
		for (int y=0 ; y<3 ; y++) {
			if (turn == board[y][0]
				&& turn == board[y][1]
				&& turn == board[y][2]) {
				return true;
			}
		}

		// 2. 세로로 한줄을 만드는 경우
		for (int x=0 ; x<3 ; x++) {
			if (turn == board[x][0]
				&& turn == board[x][1]
				&& turn == board[x][2]) {
				return true;
			}
		}

		// 3. 대각선으로 한줄을 만드는 경우
		if (turn == board[1][1] && turn == board[0][0] && turn == board[2][2]) {
			return true;
		}

		if (turn == board[1][1] && turn == board[0][2] && turn == board[2][0]) {
			return true;
		}


		return false;
	}

	// 틱택토 게임판이 주어질 때 [0, 19682] 범위의 정수로 변환한다.
	// ** 게임판을 정수로 변환해 주는 일대일 함수를 구현하는 방법인데,
	// 일대일 함수를 구현하는 간단한 방법은 board 를 아홉자리의 3진수 숫자로 보는 것이다.
	// 3^9 = 19683 이니 시간/공간 제한 내에 충분히 계산할 수 있는 양이 된다.

	// 각 칸마다 0 or 1 or 2 의 상태를 가진다.
	// 그렇게 해서 상태를 가진 칸이 총 9개 이기 때문에 3^9 이 된다.
	// 2진수를 사용한 bit mask 기법의 변형
	static int bijection(char [][] board) {
		int ret = 0;

		for (int y=0 ; y<3 ; y++) {
			for (int x=0; x<3 ; x++) {

				// 여기서 3을 곱함으로써 이전에 구했던 3진수의 값을 한칸 앞으로 민다.
				// 예를 들어 최초 x 를 만나서 2가 구해졌다면,
				// 그 다음번에는 3을 곱함으로써 20(3) 이 되고,
				// 0에 해당되는 3진수 상태값을 다시 구한다
				ret = ret * 3;

				if (board[y][x] == 'o') {
					++ret;
				} else if (board[y][x] == 'x') {
					ret += 2;
				}
			}
		}

		return ret;
	}

	// cache[] 는 -2 로 초기화 한다.
	static int [] cache = new int[19683];

	// 내가 이길 수 있으면 1을, 비길 수 있으면 0을, 지면 -1을 리턴한다.
	static int canWin(char [][] board, char turn) {
		// base case : 마지막에 상대가 둬서 한 줄이 만들어진 경우
		if (isFinished(board, (char)('o'+'x'-turn))) return -1;

		if (cache[bijection(board)] != -2) {
			return cache[bijection(board)];
		}

		// 모든 반환 값의 min 을 취하자.
		int minValue = 2;

		for (int y=0 ; y<3 ; y++) {
			for (int x=0 ; x<3 ; x++) {
				if (board[y][x] == '.') {
					// 현재 위치에 내 돌을 놓고
					board[y][x] = turn;
					// 다음 위치부터 완전탐색
					minValue = Math.min(minValue, canWin(board, (char)('o'+'x'-turn)));
					// 현재 위치원복 후 다음 위치 탐색
					board[y][x] = '.';
				}
			}
		}

		// 플레이할 수 없거나, 어떻게 해도 비기는 것이 최선인 경우
		if (minValue == 2 || minValue == 0) {
			return cache[bijection(board)] = 0;
		}


		return cache[bijection(board)] = -minValue;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int T = scanner.nextInt();

		while (T-- > 0) {
			char [][] board = new char[3][3];

			Arrays.fill(cache, -2);

			int xCnt = 0;
			int oCnt = 0;

			for (int y=0 ; y<3 ; y++) {
				String line = scanner.next();
				for (int x=0 ; x<3 ; x++) {
					board[y][x] = line.charAt(x);
					if (board[y][x] == 'x') {
						xCnt++;
					} else if (board[y][x] == 'o'){
						oCnt++;
					}
				}
			}

			char input =  xCnt > oCnt ? 'o' : 'x';

			int ret = canWin(board, input);

			if (ret == 0) {
				System.out.println("TIE");
			} else if (ret == 1) {
				System.out.println(input);
			} else {
				System.out.println((char)('o'+'x'-input));
			}
		}
	}
}
