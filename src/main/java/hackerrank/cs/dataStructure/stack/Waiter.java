package hackerrank.cs.dataStructure.stack;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author neo82
 */
public class Waiter {
	private static final int MAX = 87654321;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int q = scanner.nextInt();

		Stack<Integer>[] stacks = new Stack[2];

		for (int i = 0; i < 2; i++) {
			stacks[i] = new Stack<>();
		}

		for (int i = 0; i < n; i++) {
			stacks[0].push(scanner.nextInt());
		}

		StringBuilder result = new StringBuilder();

		int [] primes = eratosthenes(q);

		// pick up and pile
		for (int i = 0; i < q; i++) {
			Stack<Integer> A = stacks[i%2];

			StringBuilder sb = new StringBuilder();

			while (A.isEmpty() == false) {
				int pop = A.pop();

				if (pop % primes[i] == 0) {
					sb.insert(0, pop + "\n");
				} else {
					stacks[(i+1)%2].push(pop);
				}
			}

			result.append(sb.toString());
		}

		// get plates from last Aq stack
		result.append(getStringInAStack(stacks));

		System.out.println(result.toString());
	}

	private static String getStringInAStack(Stack [] stacks) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 2; i++) {
			Stack<Integer> stack = stacks[i];

			while (stack.isEmpty() == false) {
				sb.append(stack.pop() + "\n");
			}
		}

		return sb.toString();
	}

	private static int [] eratosthenes(int q) {
		int [] primes = new int[q];

		boolean [] isPrime = new boolean[MAX];

		Arrays.fill(isPrime, true);

		isPrime[0] = isPrime[1] = false;

		int primeCount = 0;

		for (int i = 2; i < MAX; i++) {
			if (isPrime[i]) {
				primes[primeCount++] = i;

				if (primeCount == q) {
					return primes;
				}

				for (int j = i*i; j < MAX; j+=i) {
					isPrime[j] = false;
				}
			}
		}

		return null;
	}
}
