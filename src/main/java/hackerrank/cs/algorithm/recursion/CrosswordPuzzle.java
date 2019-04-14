package hackerrank.cs.algorithm.recursion;

import java.util.Arrays;
import java.util.Scanner;

/**
 */
public class CrosswordPuzzle {

	static char [][] crossword = new char[10][10];
	static String [] words;
	static boolean goFind = true;

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		for (int i = 0; i < 10; i++) {
			String line = scanner.next();
			for (int j = 0; j < 10; j++) {
				crossword[i][j] = line.charAt(j);
			}
		}

		words = scanner.next().split(";");

		// input complete

		solve(0);
	}

	static void solve(int index) {
		if (!goFind) {
			return;
		}

		// 모든 단어를 다 찾아봤고,
		if (index == words.length) {
			// 계속 찾아야 되는 상태인 경우,
			if (goFind) {
				// 정답을 출력하고 다음 상황을 종료한다.
				print(crossword);
				goFind = false;
			}
		}

		int p,q,k;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				p = i;
				q = j;

				// index 번째 단어의 길이만큼 순회한다.
				// 여기서 k 는 단어중에서 character 의 위치이다.
				for (k = 0; k < words[index].length() && p + k < 10; k++) {
					if (crossword[p+k][q] != '-' && crossword[p+k][q] != words[index].charAt(k)) {
						break;
					}
				}

				if (k == words[index].length()) {
					char [][] original = Arrays.copyOf(crossword, crossword.length);

					for (k=0 ; k<words[index].length() ; k++) {
						crossword[p+k][q] = crossword[index][k];
					}

					solve(index+1);

					crossword = original;
				}

				for (k = 0 ; k < crossword[index].length && q+k < 10 ; k++) {
					if (crossword[p][q+k] != '-' && crossword[p][q+k] != words[index].charAt(k)) {
						break;
					}
				}

				if (k == words[index].length()) {
					char [][] original = Arrays.copyOf(crossword, crossword.length);

					for (k = 0; k<words[index].length() ; k++) {
						crossword[p][q+k] = words[index].charAt(k);
					}

					solve(index+1);
					crossword = original;
				}

			}
		}
	}

	static void print(char [][] crowssword) {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(crowssword[i][j]);
			}

			System.out.println();
		}
	}

}
