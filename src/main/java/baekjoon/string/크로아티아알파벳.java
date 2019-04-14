package baekjoon.string;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 */
public class 크로아티아알파벳 {

	static final List<String> croatiaAlphabet = new ArrayList<>();
	static boolean [] found;

	static {
		croatiaAlphabet.add("c=");
		croatiaAlphabet.add("c-");
		croatiaAlphabet.add("dz=");
		croatiaAlphabet.add("d-");
		croatiaAlphabet.add("lj");
		croatiaAlphabet.add("nj");
		croatiaAlphabet.add("s=");
		croatiaAlphabet.add("z=");
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String S = scanner.next();

		System.out.println(fullSolve(S));
	}

	static int fullSolve(String s) {
		int cnt = 0;

		found = new boolean[s.length()];

		for (String croatia : croatiaAlphabet) {
			cnt += solve(s, croatia);
		}

		for (int i=0 ; i<found.length ; i++) {
			if (found[i] == false) {
				cnt++;
			}
		}

		return cnt;
	}

	static int solve(String S, String croatia) {
		int cnt = 0;

		int i=0;
		int j=0;

		while (i<S.length()) {
			if (found[i] == false && (S.charAt(i) == croatia.charAt(j))) {
				if (j == croatia.length() - 1) {
					cnt++;
					markFound(i - j, i);
					j = 0;
					continue;
				}

				j++;
				i++;
			} else {
				if (j == 0) {
					i++;
				} else {
					j = 0;
				}
			}
		}

		return cnt;
	}

	static void markFound(int start, int end) {
		for (int here=start ; here<=end; here++) {
			found[here] = true;
		}
	}
}
