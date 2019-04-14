package hackerrank.cs.algorithm.recursion;

import java.util.*;

/**
 * @author baejunbeom
 */
public class PasswordCrackerSolution {
	static Map<Integer, Boolean> dp = new HashMap<>();

	private static boolean solve(String[] passwords, String loginAttempt, int index, List<String> usedSoFar) {

		if (loginAttempt.length() == index) {
			return true;
		}

		if (dp.containsKey(index)) {
			return false;
		}

		for (String password : passwords) {
			if (loginAttempt.startsWith(password, index)) {
				usedSoFar.add(password);

				if (solve(passwords, loginAttempt, index + password.length(), usedSoFar)) {
					return true;
				}

				usedSoFar.remove(usedSoFar.size() - 1);
			}
		}

		dp.put(index, false);

		return false;
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);

		int cases = scn.nextInt();
		for (int ccase = 0; ccase < cases; ccase++) {
			dp = new HashMap<>();
			int numberOfPasswords = scn.nextInt();
			String[] passwords = new String[numberOfPasswords];

			for (int password = 0; password < numberOfPasswords; password++) {
				passwords[password] = scn.next();
			}

			String loginAttempt = scn.next();
			List<String> solution = new ArrayList<>();

			if (solve(passwords, loginAttempt, 0, solution)) {
				for (String word : solution) {
					System.out.print(word + " ");
				}
				System.out.println();
			} else {
				System.out.println("WRONG PASSWORD");
			}
		}

		scn.close();
	}
}
