package hackerrank.cs.algorithm.recursion;

import java.util.*;

/**
 * @author baejunbeom
 */
@SuppressWarnings("ALL")
public class PasswordCracker2 {

	static String[][] cache;

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

		cache = new String[pass.length][attempt.toCharArray().length+1];

		try {
			return solve(pass, attempt, attempt.length(), 0);
		} catch (Exception e) {
			return "WRONG PASSWORD";
		}
	}

	static String solve(String[] pass, String attempt, int maxIdx, int attemptIdx) {
		if (attemptIdx >= maxIdx) {
			return "";
		}

		for (int i=0 ; i<pass.length ; i++) {
			String ps = pass[i];

			if (attempt.indexOf(ps) == 0) {
				int nextIdx = attemptIdx + ps.length();

				try {
					String ret = ps + " ";

					if (cache[i][nextIdx] != null) {
						if (cache[i][nextIdx] == "") {
							throw new Exception();
						}

						return ret + cache[i][nextIdx];
					}

					return ret += cache[i][nextIdx] = solve(pass, new String(attempt).replaceFirst(ps, ""), maxIdx, nextIdx);

				} catch (Exception e) {
					cache[i][nextIdx] = "";
				}
			}
		}

		throw new RuntimeException();
	}


	public static void main(String[] args) {
				Scanner in = new Scanner(System.in);
				int t = in.nextInt();
				for(int a0 = 0; a0 < t; a0++){
					int n = in.nextInt();
					String[] pass = new String[n];
					for(int pass_i = 0; pass_i < n; pass_i++){
						pass[pass_i] = in.next();
					}
					String attempt = in.next();
					String result = passwordCracker(pass, attempt);
					System.out.println(result);
				}
				in.close();

//		String pass = "a aa aaa aaaa aaaaa aaaaaa aaaaaaa aaaaaaaa aaaaaaaaa aaaaaaaaaa";
//		String attempt =
//			"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
//
//		System.out.println(passwordCracker(pass.split(" "), attempt));
	}
}
