package leetcode.contest;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 */
public class MapSumPairs {

	static Node globalRoot = new Node();

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);


		while (true) {
			String cmd = scanner.next();

			if ("insert".equals(cmd)) {
				String key = scanner.next();
				int val = scanner.nextInt();

				insert(key, val);

			} else if ("sum".equals(cmd)) {
				String key = scanner.next();

				System.out.println(search(key));
			} else if ("exit".equals(cmd)) {
				break;
			}
		}
	}


	static class Node {
		Node [] children = new Node[26];
		boolean endOfWord;
		int cost;
	}

	static void insert(String key, int val) {
		Node root = globalRoot;

		key = key.toLowerCase();

		for (int i = 0; i < key.length(); i++) {
			int idx = key.charAt(i) - 'a';

			if (root.children[idx] == null) {
				root.children[idx] = new Node();
			}

			root = root.children[idx];
		}

		root.endOfWord = true;
		root.cost = val;
	}

	static int search(String prefix) {
		Node root = globalRoot;

		for (int i = 0; i < prefix.length(); i++) {
			int idx = prefix.charAt(i) - 'a';

			if (root.children[idx] == null) {
				return 0;
			}

			root = root.children[idx];
		}

		Queue<Node> q = new LinkedList<>();
		q.add(root);

		int cost = 0;

		while (q.isEmpty() == false) {
			Node poll = q.poll();

			if (poll.endOfWord) {
				cost += poll.cost;
			}

			for (Node child : poll.children) {
				if (child != null) {
					q.add(child);
				}
			}
		}


		return cost;
	}
}
