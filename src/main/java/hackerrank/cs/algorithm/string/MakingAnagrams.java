package hackerrank.cs.algorithm.string;

import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class MakingAnagrams {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		String a = scanner.next();
		String b = scanner.next();

		int [] arr1 = new int[26];

		for (int i = 0; i < a.length(); i++) {
			arr1[a.charAt(i)-97]++;
		}

		int [] arr2 = new int[26];

		for (int i = 0; i < b.length(); i++) {
			arr2[b.charAt(i)-97]++;
		}

		int count = 0;

		for (int i = 0; i < 26; i++) {
			count += Math.abs(arr1[i] - arr2[i]);
		}

		System.out.println(count);
	}
}
