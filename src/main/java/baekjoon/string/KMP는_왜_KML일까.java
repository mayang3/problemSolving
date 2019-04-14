package baekjoon.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class KMP는_왜_KML일까 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String T = scanner.nextLine();

		List<Character> ret = search(T, "-");

		String res = "";

		for (char ch : ret) {
			res += ch;
		}

		System.out.println(res);
	}

	static List<Character> search(String T, String P) {
		List<Character> characters = new ArrayList<>();
		characters.add(T.charAt(0));

		int n = T.length();
		int m = P.length();

		int i=0;
		int j=0;

		int [] pi = computePi(P);

		while (i < n) {
			if (T.charAt(i) == P.charAt(j)) {
				i++;
				j++;
			}

			if (j == m) {
				characters.add(T.charAt(i-j+1));
				j = pi[j-1];
			} else if (i < n && T.charAt(i) != P.charAt(j)) {
				if (j != 0) {
					j = pi[j-1];
				} else {
					i++;
				}
			}

		}

		return characters;
	}

	private static int[] computePi(String P) {
		int m = P.length();

		int i=1;
		int longest=0;

		int [] pi = new int[m];
		pi[0] = 0;

		while(i < m) {
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
