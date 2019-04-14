package baekjoon.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 찾기 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String T = scanner.nextLine();
		String P = scanner.nextLine();

		List<Integer> ret = search(T, P);

		System.out.println(ret.size());

		for (int v : ret) {
			System.out.print(v + " ");
		}
	}

	static List<Integer> search(String T, String P) {
		List<Integer> positions = new ArrayList<>();

		int n = T.length();
		int m = P.length();

		int [] pi = computePi(P);

		int i=0;
		int j=0;

		while (i < n) {
			if (T.charAt(i) == P.charAt(j)) {
				i++;
				j++;

				if (j==m) {
					positions.add(i-j+1); // i-j 를 해야 제일 처음 시작 위치가 나온다. 그런데 결과에서는 시작점이 1이므로 index+1 을 해준다.
					j = pi[j-1];
				}

			} else if (i < n && T.charAt(i) != P.charAt(j)) {
				if (j != 0) {
					j = pi[j-1];
				} else {
					i++;
				}
			}
		}

		return positions;
	}

	private static int[] computePi(String P) {
		int m = P.length();

		int i=1;
		int longest=0;

		int [] pi = new int[m];
		pi[0] = 0;

		while (i < m) {
			if (P.charAt(i) == P.charAt(longest)) {
				longest++;
				pi[i] = longest;
				i++;
			} else {
				if (longest != 0) {
					longest = pi[longest-1];
				} else {
					pi[i] = longest;
					i++;
				}
			}
		}

		return pi;
	}
}
