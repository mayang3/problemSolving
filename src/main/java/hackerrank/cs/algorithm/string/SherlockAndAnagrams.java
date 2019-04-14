package hackerrank.cs.algorithm.string;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Two strings are anagrams of each other if the letters of one string can be rearranged to form the other string.
 *
 * Given a string, find the number of pairs of substrings of the string which are anagrams of each other.
 *
 * For example s = mom,
 *
 * the list of all anagrammatic paris is [m,m],[mo,om] at positions [[0],[2]],[[0,1],[1,2]] respectively.
 *
 * Function Description
 *
 * Complete the function sherlockAndAnagrams in the editor below.
 *
 * It must return an integer representing the number of anagrammatic pairs of substrings in s.
 *
 * sherlockAndAnagrams has the following parameter(s):
 *
 * s: a string.
 *
 * [Input format]
 *
 * The first line contains an integer q, the number of queries.
 *
 * Each of the next q lines contains a string s to analyze.
 *
 * [Constraints]
 *
 * 1 <= q <= 10
 * 2 <= |s| <= 100
 *
 * String s contains only lowercase letters that is subset by ascii [a-z].
 *
 * [Output Format]
 *
 * For each query, return the number of unordered anagrammatic paris.
 *
 *
 */
public class SherlockAndAnagrams {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int q = scanner.nextInt();

		while (q-- > 0) {
			String s = scanner.next();

			solve(s);
		}
	}

	/**
	 * [accept]
	 *
	 * s < 100 이므로 100^3 해도 충분히 통과한다.
	 *
	 * @param s
	 */
	static void solve(String s) {
		int cnt = 0;

		for (int len = 1; len < s.length(); len++) {
			for (int i = 0, j=i+len; j <= s.length(); i++, j=i+len) {
				char [] stdSub = s.substring(i, j).toCharArray();

				Arrays.sort(stdSub);

				String stdSubString = String.valueOf(stdSub);

				for (int l=i+1, m=l+len ; m <= s.length() ; l++, m=l+len) {
					char [] diffSub = s.substring(l, m).toCharArray();

					Arrays.sort(diffSub);

					if (stdSubString.equalsIgnoreCase(String.valueOf(diffSub))) {
						cnt++;
					}
				}
			}
		}

		System.out.println(cnt);

	}
}
