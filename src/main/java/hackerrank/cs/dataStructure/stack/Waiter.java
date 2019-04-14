package hackerrank.cs.dataStructure.stack;

import java.util.*;

/**
 *
 */
public class Waiter {

	public static void main(String[] args) {

		List<Integer> primeList = bitWiseSieve(25000);

		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int q = scanner.nextInt();

		Stack<Integer> A = new Stack<Integer>();

		for (int i = 0; i < n; i++) {
			A.push(scanner.nextInt());
		}

		List<Stack<Integer>> bList = new ArrayList<Stack<Integer>>();

		for (int i = 0; i < q; i++) {
			int prime = primeList.get(i);
			int size = new Integer(A.size());

			Stack<Integer> newA = new Stack<Integer>();
			Stack<Integer> B = new Stack<Integer>();

			while (size-- > 0) {
				int pop = A.pop();

				if (pop % prime == 0) {
					B.push(pop);
				} else {
					newA.push(pop);
				}
			}

			bList.add(B);
			A = newA;
		}

		for (Stack<Integer> B : bList) {
			while (!B.isEmpty()) {
				System.out.println(B.pop());
			}
		}

		while (!A.isEmpty()) {
			System.out.println(A.pop());
		}

	}


	// Checks whether x is prime or composite
	static int ifnotPrime(int prime[], int x)
	{
		// checking whether the value of element is set or not.
		// Using prime[x/64]. we find the slot in prime array.
		// To find the bit number, we divide x by 2 and take its mod with 32.
		return (prime[x/64] & (1 << ((x >> 1) & 31)));
	}

	// Marks x composite in prime[]
	static void makeComposite(int prime[], int x)
	{
		// Set a bit corresponding to given element.
		// Using prime[x/64], we find the slot
		// in prime array. To find the bit number,
		// we divide x by 2 and take its mod with 32.
		prime[x/64] |= (1 << ((x >> 1) & 31));
	}

	// Prints all prime numbers smaller than n.
	static List<Integer> bitWiseSieve(int n)
	{
		// Assuming that n takes 32 bits,
		// we reduce size to n/64 from n/2.
		int prime[] = new int[n/64 + 1];


		// 2 is the only even prime so we
		// can ignore that loop starts from
		// 3 as we have used in sieve of
		// Eratosthenes .
		for (int i = 3; i * i <= n; i += 2) {

			// If i is prime, mark all its
			// multiples as composite
			if (ifnotPrime(prime, i)==0) {
				for (int j = i * i, k = i << 1; j < n; j += k) {
					makeComposite(prime, j);
				}
			}
		}


		List<Integer> primeList = new ArrayList<Integer>();
		primeList.add(2);

		// Printing other primes
		for (int i = 3; i <= n; i += 2) {
			if (ifnotPrime(prime, i) == 0) {
				primeList.add(i);
			}
		}

		return primeList;
	}
}
