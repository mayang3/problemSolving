package baekjoon.dnc;

import java.util.Scanner;

public class 종이의개수 {
	static int [] ret = new int[3];

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		int [][] paper = new int[n][n];

		for (int i=0 ; i<n ; i++) {
			for (int j=0 ; j<n ; j++) {
				paper[i][j] = scanner.nextInt();
			}
		}

		dnc(paper);

		for (int i=0 ; i<3 ; i++) {
			System.out.println(ret[i]);
		}
	}

	static void dnc(int [][] paper) {
		int[] counts = getCounts(paper);
		int n = paper.length * paper.length;

		if (counts[0] == n) {
			ret[0]++;
			return;
		} else if (counts[1] == n) {
			ret[1]++;
			return;
		} else if (counts[2] == n) {
			ret[2]++;
			return;
		}

		for (int i=0 ; i<3 ; i++) {
			for (int j=0 ; j<3 ; j++) {
				dnc(slice(paper, i, j));
			}
		}
	}

	private static int[][] slice(int[][] paper, int i, int j) {
		int size = paper.length / 3;

		int py = i * size;
		int px = j * size;

		int [][] sub = new int[size][size];

		for (int y=0 ; y<size ; y++) {
			for (int x=0 ; x<size ; x++) {
				sub[y][x] = paper[py+y][px+x];
			}
		}

		return sub;
	}

	static int [] getCounts(int[][] paper) {
		int [] counts = new int[3];

		for (int i=0 ; i<paper.length ; i++) {
			for (int j=0 ; j<paper[i].length ; j++) {
				counts[paper[i][j]+1]++;
			}
		}

		return counts;
	}

}
