package hackerrank.cs.algorithm.recursion;

import java.util.*;

/**
 * @author baejunbeom
 */
public class PasswordCracker {

	static String passwordCracker(String[] pass, String attempt) {
		Set<Character> passCh = new HashSet<>();

		for (String ps : pass) {
			for (char c : ps.toCharArray())
				passCh.add(c);
		}

		char[] chars = attempt.toCharArray();


		for (char c : chars) {
			if (passCh.contains(c) == false) {
				return "WRONG PASSWORD";
			}
		}


		try {
			return solve(pass, attempt);
		} catch (Exception e) {
			return "WRONG PASSWORD";
		}
	}

	static String solve(String[] pass, String attempt) {
		if (attempt == null || attempt.isEmpty()) return "";

		for (String ps : pass) {
			int index = attempt.indexOf(ps);

			if (index == 0) {
				try {
					 return ps + " " + solve(pass, new String(attempt).replaceFirst(ps, ""));
				} catch (Exception e) {
				}
			}
		}


		throw new RuntimeException();
	}

	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		int t = in.nextInt();
//		for(int a0 = 0; a0 < t; a0++){
//			int n = in.nextInt();
//			String[] pass = new String[n];
//			for(int pass_i = 0; pass_i < n; pass_i++){
//				pass[pass_i] = in.next();
//			}
//			String attempt = in.next();
//			String result = passwordCracker(pass, attempt);
//			System.out.println(result);
//		}
//		in.close();

		String pass = "ba ab baab ababba baabab abba bababaabab babaab abbaabab babaabab";
		String attempt =
			"baabbabaabbaabbabaabbaabbaababbababaabababbabaabbaababbaababbaabbaababababbabababaabbaabbaababababbabaababbabaabbaababbaabbabaababbaabbaabbabaabbaabbaababbaabababbabaabbaabbababaabbaababbaabbaabbaababbaabbabaabbaabbaabbabaabababbaabbaababababbaabbaabababbababaabbaabbaabbaabbabababaabababbababababababaabbaabbabaabbabaabbababaababbaababbaabbaabbaabbabaabababbaabbaababbabaabbaabbabaababbababaabbaababbaabbaabbabaabbaabbaabbaabbaababbaababbaababbabaababbaababbaabbababaabbaabbaababbabababaabbabaabbaabbaabbaababbaabbaababbaabababbababaababbabaabbaabbabaabbaabababbaababbaababbaabbabababaababbaabbaababbabaabbaabbaabbababaabbabaabbaababbaabbaabbaababbaabbaabbaabbaabbaababbaabababababababababbaabbaabbabaabbaabbaabbaabbaababbaabbaabbaababababbaababbaabbaababababbaabbaababbaabababbaabbaabababbaabababbaababbaabbaabbabaabbaababbabaabababbaabbaabbaabbaabbabaabbababaabababbaabbaabbaabbaabbaababbaabbaabbabaabbabababaababbaabababbababaabbaabbaabbaababbaababbaabbabaabbabaabbaababbaabbabaabbaabbabababababaabababbabaabbaababbaabbaababbaabbaabbaababbaababbaabbaababbaabbaabbabaabbaabbaabbabaabbabaabbaabbabaabababbabaabbaabbabaabbaabbababaabbaabbababaababababababbabababaababbaababababbaababababbaabbaababbaababbaabbabababaabbabaababbabaabbaabbababaabbaabbaababbaabbababaabbabaababbaabbaababbaabbabaababbabaabbabababaababbabaababababababbaababbababaabbaabbaabbabaabbaabbaabbaabbaababbabaabbaabbabaabbaabbabaababbaababbaabababababbaabbababaabbabaabbaababbaabababbaabbabababaabbabaababbaabababbababababaabbabaabbaabbababaababbaabbaababbabaabbaababbaabbabababaababbbbaababbbbababaabbaababbaababbaabbababababaabbaabbaabbaabbabaabbaabbabaababbabaabbaababbaababbaabbaababababbaababbaababbaabababbababaabbabaababbaabbaabbaababbaababbaabbaababbaabbaabbabaababababbaabbaabababbaababbaabbaababbaababbabababababaabbaababbababaababbababaababbabaababbaabbababaababbabaababbabababaabbabaababbaabbabaababbabaabbabaabbaabbaababbababaababbaabbaabbababaabbaabbaabbaabbaabababbabaababbaabbaabbaabbabababaabbaabbaabbaababbaabba";

		System.out.println(passwordCracker(pass.split(" "), attempt));
	}
}
