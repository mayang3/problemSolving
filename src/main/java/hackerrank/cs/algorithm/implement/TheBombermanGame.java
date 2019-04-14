package hackerrank.cs.algorithm.implement;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * Bomberman lives in a rectangular grid.
 *
 * Each cell in the grid either contains a bomb or nothing at all.
 *
 * Each bomb can be planted in any cell of the grid but once planted, it will detonate after exactly 3 seconds.
 *
 * Once a bomb detonates, it's destroyed - along with anything in its four neighboring cells.
 *
 * This means that if a bomb detonates in cell i,j, any valid cells (i+-1, j) and (i,j+-1) are cleared.
 *
 * If there is a bomb in a neighboring cell, the neighboring bomb is destroyed without detonating, so there's no chain reaction.
 *
 * Bomberman is immune to bombs, so he can move freely throughout the grid.
 *
 * Here's he what he does:
 *
 * 1. Initially, Bomberman arbitrarily plants bombs in some of the cells, the initial state.
 * 2. After one second, Bomberman does nothing.
 * 3. After one more second, Bomberman plants bombs in a cells without bombs, thus filling the whole grid with bombs.
 *	  No bombs detonate at this point.
 * 4. After one or more second, any bombs planted exactly three seconds ago will detonate.
 * 	  Here, Bomberman stands back and observes.
 * 5. Bomberman then repeats steps 3 and 4 indefinitely
 *
 * Note that during every second Bomberman plants bombs, the bombs are planted simultaneously.
 * (i.e., at the exact same moment),
 *
 * and any bombs planted at the same time will detonate at the same time.
 *
 * Given the initial configuration of the grid with the locations of Bomberman's first branch of planted bombs,
 *
 * determine the state of the grid after N seconds.
 *
 * For example, if the initial grid look like:
 *
 * ...
 * .O.
 * ...
 *
 * It looks the same after the first second.
 * After the second second, Bomberman has placed all his charges:
 *
 * OOO
 * OOO
 * OOO
 *
 * At the third second, the bomb in the middle blows up, emptying all surrounding cells:
 *
 * ...
 * ...
 * ...
 *
 *
 * [Input Format]
 *
 * The first line contains three space-separated integers r,c,and n, columns and seconds to simulate.
 *
 * Each of the next r lines contains a row of the matrix as a single string of c characters.
 *
 * The . character denotes an empty cell, and the 0 character (ascii 79) denotes a bomb.
 *
 * [Constraint]
 *
 * 1 <= r, c <= 200
 * 1 <= n <= 10^9
 *
 * [Output Format]
 * Print the grid's final state.
 * This means R lines where each line contains C characters, and each character is either a . or an 0 (ascii 79).
 * This grid must represent the state of the grid after n seconds.
 *
 *
 */
public class TheBombermanGame {

	private static int r;
	private static int c;
	private static int n;
	private static final char BOMB = (char)79;
	private static final char TEMP = 'T';

	/**
	 * 이 로직의 핵심 알고리즘은 다음과 같다.
	 *
	 * 1. n 이 짝수일 경우 -> 항상 grid 전체가 폭탄으로 가득 차 있다.
	 * 2. n 이 1 일 경우 -> 입력 grid 그대로 리턴하면 된다.
	 * 3. n 이 1이 아닌 홀수일 경우 ->
	 * 이 때가 핵심인데, 1~n 까지 각각 grid 의 상태를 그려보면,
	 * n 이 1이 아닌 홀수일 경우에는.. 잘 보면 규칙이 있다.
	 *
	 * 바로 홀수의 개수가 증가할때마다 grid 의 모양이 반전되는 것이다.
	 * 즉, n=3 일때와 n=5 일때가 각각의 grid 모양이 있고,
	 *
	 * n 이 7,9,11,13,15 ... 로 증가될때 앞의 grid 가 반복해서 나타나게 된다.
	 *
	 * 예를 들어 n=7 일때의 모양은, n=3 일때와 같고, n=9 일때의 모양은 n=5 일때와 같다.
	 *
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		r = scanner.nextInt();
		c = scanner.nextInt();
		n = scanner.nextInt();

		char [][] grid = new char[r][c];

		for (int i = 0; i < r; i++) {
			String line = scanner.next();
			for (int j = 0; j < c; j++) {
				grid[i][j] = line.charAt(j);
			}
		}

		if (n % 2 == 0) {
			fillAll(grid, BOMB);
			print(grid);
			return;
		}

		if (n == 1) {
			print(grid);
			return;
		}

		char [][] mirrorGrid = makeMirrorGrid(grid, 1);
		char [][] mirrorGrid2 = makeMirrorGrid(grid,2 );

		// 3, 5, 7, 9
		int flag = -1;

		for (int i = 3; i <= n ; i+=2) {
			flag *= -1;
		}

		if (flag == 1) {
			print(mirrorGrid);
		} else if (flag == -1) {
			print(mirrorGrid2);
		}
	}

	private static char[][] makeMirrorGrid(char[][] grid, int repeat) {
		char [][] mirrorGrid = new char[r][c];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				mirrorGrid[i][j] = grid[i][j];
			}
		}

		// 반전 로직 자체가 잘못됐다..
		// 반전 할 부분을 BOMB 값이 아닌 다른 녀석으로 체크해야된다..
		for (int k = 0; k < repeat; k++) {
			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (mirrorGrid[i][j] == BOMB) {
						mirrorGrid[i][j] = TEMP;

						// up
						fill(i - 1, j, mirrorGrid);

						// down
						fill(i + 1, j, mirrorGrid);

						// left
						fill(i, j - 1, mirrorGrid);

						// right
						fill(i, j + 1, mirrorGrid);
					}
				}
			}

			for (int i = 0; i < r; i++) {
				for (int j = 0; j < c; j++) {
					if (mirrorGrid[i][j] == TEMP) {
						mirrorGrid[i][j] = '.';
					} else {
						mirrorGrid[i][j] = BOMB;
					}
				}
			}
		}

		return mirrorGrid;
	}

	static void fill(int i, int j, char [][] mirrorGrid) {
		if (i < 0 || i >= r) {
			return;
		} else if (j < 0 || j >= c) {
			return;
		} else if (mirrorGrid[i][j] == BOMB) {
			return;
		}

		mirrorGrid[i][j] = TEMP;
	}

	private static void print(char[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				System.out.print(grid[i][j]);
			}

			System.out.println();
		}
	}

	static void fillAll(char[][] grid, char c) {
		for (char[] g : grid) {
			Arrays.fill(g, c);
		}
	}

}
