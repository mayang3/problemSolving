package baekjoon.dnc;

import java.util.Scanner;

/**
 * https://www.acmicpc.net/problem/2740
 *
 * 분할정복 카테고리에 있는 것은, 슈트라센 알고리즘 때문인듯..
 *
 * https://ko.wikipedia.org/wiki/%EC%8A%88%ED%8A%B8%EB%9D%BC%EC%84%BC_%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98
 *
 */
public class 행렬곱셈 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int M1 = scanner.nextInt();

		int [][] A = new int[N][M1];

		for (int i=0 ; i<N ; i++) {
			for (int j = 0; j < M1; j++) {
				A[i][j] = scanner.nextInt();
			}
		}

		int M2 = scanner.nextInt();
		int K = scanner.nextInt();

		int [][] B = new int[M2][K];

		for (int i=0 ; i<M2 ; i++) {
			for (int j=0 ; j<K ; j++) {
				B[i][j] = scanner.nextInt();
			}
		}

		int [][] matrix = new int[N][K];

		// N,M,K 의 최대값은 100 이므로, 최대시간은 O(100^3) = 100만 이므로 충분할듯..
		for (int i=0 ; i<N ; i++) {
			for (int j=0 ; j<K ; j++) {
				for (int k=0 ; k<M1 ; k++) {
					matrix[i][j] += (A[i][k] * B[k][j]);
				}
			}
		}

		for (int i=0 ; i<N ; i++) {
			for (int j=0 ; j<K ; j++) {
				System.out.print(matrix[i][j] + " ");
			}

			System.out.println();
		}
	}

}
