package hackerrank.cs.algorithm.constructive;

import java.util.Scanner;

/**
 * Sean invented a game involving a 2n * 2n matrix where each cell of the matrix contains an integer.
 *
 * He can reverse any of its rows or columns any number of times.
 *
 * The goal of the game is to maximize the sum of the elements in the n * n submatrix located in the upper-left quadrant of the matrix.
 *
 * Given the initial configurations for q matrices, help Sean reverse the rows and columns of each matrix in the best possible way
 *
 * so that the sum of the elements in the matrix's upper-left quadrant is maximal.
 *
 */
public class FlippingTheMatrix {

	/**
	 * 나름 이해하기 어려웠다.. 90프로 정도 이해한듯.
	 *
	 * 각 행 or 열을 reverse 한다는건 결국 4개의 사분면을 서로 swap 하여 원하는 최대값을 1사분면으로 몰아넣을 수 있다는 뜻이다.
	 *
	 * 그 뜻을 또 다시 한번 생각해보면, 십자선을 그어 4개의 사분면으로 나눌 경우, 각 대칭되는 사분면의 좌표의 값들중 최대의 값이 정답이 된다는 뜻이다.
	 *
	 * (각 사분면을 이동시키면서 어떻게든 해당 자리에 집어넣을 수 있으므로..)
	 *
	 *
	 * 그래서 구현은 맨 왼쪽 위, 맨 오른쪽 위, 맨 왼쪽 아래, 맨 오른쪽 아래 의 4가지 값들중 최대값을 뽑아내면 그게 하나의 최대값이 된다.
	 *
	 * 그렇게 해서 모든 사분면의 좌표값을 순회하면 정답이 나온다.
	 *
	 * (1/4 의 정답이 나오므로..)
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int q = scanner.nextInt();

		while (q-- > 0) {
			int n = scanner.nextInt();

			int [][] mat = new int[n*2][n*2];

			for (int y = 0; y < 2*n; y++) {
				for (int x = 0; x < 2*n; x++) {
					mat[y][x] = scanner.nextInt();
				}
			}

			int r1, c1, r2, c2;

			int sum = 0;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					r1 = i; // left row
					r2 = 2*n-i-1; // right row
					c1 = j; // top column
					c2 = 2*n-j-1; // bottom column

					sum += Math.max(
						Math.max(mat[r1][c1], mat[r1][c2]), // 맨 앞 행에 있는 위/아래 양쪽 반대 컬럼중 큰 녀석
						Math.max(mat[r2][c1], mat[r2][c2]) // 맨 뒤 행에 있는 위/아래 양쪽 반대 컬럼중 큰 녀석
					);
				}
			}

			System.out.println(sum);
		}
	}

}
