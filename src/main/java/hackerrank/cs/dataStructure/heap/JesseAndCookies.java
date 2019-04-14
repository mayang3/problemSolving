package hackerrank.cs.dataStructure.heap;

/*

Jesse loves cookies. He wants the sweetness of all his cookies to be greater than value . To do this, Jesse repeatedly mixes two cookies with the least sweetness. He creates a special combined cookie with:

sweetness  Least sweet cookie   2nd least sweet cookie).

He repeats this procedure until all the cookies in his collection have a sweetness .
You are given Jesse's cookies. Print the number of operations required to give the cookies a sweetness . Print  if this isn't possible.

Input Format

The first line consists of integers , the number of cookies and , the minimum required sweetness, separated by a space.
The next line contains  integers describing the array  where  is the sweetness of the  cookie in Jesse's collection.

Constraints

1 <= N <= 10^6
0 <= K <= 10^9
0 <= A[i] <= 10^6

Output Format

Output the number of operations that are needed to increase the cookie's sweetness .
Output  if this isn't possible.

Sample Input

6 7
1 2 3 9 10 12
Sample Output

2
Explanation

Combine the first two cookies to create a cookie with sweetness  =
After this operation, the cookies are .
Then, combine the cookies with sweetness  and sweetness , to create a cookie with resulting sweetness  =
Now, the cookies are .
All the cookies have a sweetness .

Thus,  operations are required to increase the sweetness.

 */

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author baejunbeom
 */
public class JesseAndCookies {

	private static PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

	public static int operation(int K) {
		// base case 1 : heap 의 min 값이 k 보다 크다면 종료
		if (priorityQueue.peek() >= K) {
			return 0;
		}

		// base case 2 : heap 의 cookie 를 모두 사용했는데도, K 값을 찾지 못한다면 impossible
		int newSweet = (1 * priorityQueue.poll()) + (2 * priorityQueue.poll());

		priorityQueue.add(newSweet);

		return operation(K) + 1;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		int K = scanner.nextInt();

		int [] sweetness = new int[N];

		for (int i=0 ; i<N ; i++) {
			sweetness[i] = scanner.nextInt();
		}

		for (int sweet : sweetness) {
			priorityQueue.add(sweet);
		}

		try {
			System.out.println(operation(K));;
		} catch (NullPointerException ne) {
			System.out.println(-1);
		}
	}
}
