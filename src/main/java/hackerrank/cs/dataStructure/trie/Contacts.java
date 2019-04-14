package hackerrank.cs.dataStructure.trie;

import java.util.Scanner;

/**
 * We're going to make our own Contacts application!
 *
 * The application must perform two types of operations:
 *
 * 1. add name, where name is a string denoting a contact name.
 *    This must store name as a new contact in the application.
 *
 * 2. find partial, where partial is a string denoting a partial name to search the application for.
 * 	  It must count the number of contacts starting with partial and print the count on a new line.
 *
 * Given n sequential add and find operations, perform each operation in order.
 *
 * [Input Format]
 *
 * The first line contains a single integer, n, denoting the number of operations to perform.
 *
 * Each line i of the n subsequent lines contains an operation in one of the two forms defined above.
 * (각각의 라인 i 는 위에 정의된 두가지 형식중 하나의 명령을 포함하고 있다.)
 *
 * [Constraints]
 * 1 <= n <= 10^5
 * 1 <= |name| <= 21
 * 1 <= |partial| <= 21
 * It's guaranteed that name and partial contain lowercase English letters only.
 * The input doesn't have any duplicate name for the add operation.
 *
 * [Output Format]
 *
 * For each find partial operation, print the number of contact names starting with partial on a new line.
 *
 */
public class Contacts {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();

		for (int i = 0; i < n; i++) {
			String cmd = scanner.next();

			if ("add".equalsIgnoreCase(cmd)) {
				insert(scanner.next());
			} else if ("find".equalsIgnoreCase(cmd)) {
				System.out.println(search(scanner.next()));
			}
		}
	}

	static class TrieNode {
		boolean terminate;
		int count = 1;
		TrieNode [] childrens = new TrieNode[26];
	}

	static TrieNode root = new TrieNode();

	static void insert(String key) {
		TrieNode trieNode = root;

		for (int level = 0; level < key.length(); level++) {
			int idx = key.charAt(level) - 'a';

			if (trieNode.childrens[idx] == null) {
				trieNode.childrens[idx] = new TrieNode();
			} else {
				trieNode.childrens[idx].count++;
			}

			trieNode = trieNode.childrens[idx];
		}

		trieNode.terminate = true;
	}

	static int search(String key) {
		TrieNode trieNode = root;

		for (int level = 0; level < key.length(); level++) {
			int idx = key.charAt(level) - 'a';

			if (trieNode.childrens[idx] == null) {
				return 0;
			}

			trieNode = trieNode.childrens[idx];
		}

		return trieNode.count;
	}
}
