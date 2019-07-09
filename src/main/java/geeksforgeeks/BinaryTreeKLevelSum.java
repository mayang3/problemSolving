package geeksforgeeks;

import java.util.*;

/**
 * @author neo82
 */
public class BinaryTreeKLevelSum {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int t = scanner.nextInt();

		while (t-- > 0) {

			int k = scanner.nextInt();
			String input = scanner.next();

			Node root = makeNodes(input);

			System.out.println(getSum(root,k));
		}
	}

	private static int getSum(Node root, int k) {
		Queue<Node> q = new LinkedList<>();
		q.add(root);

		int lv = 0;
		int sum = 0;

		Set<Node> discovered = new HashSet<>();

		while (q.isEmpty() == false) {
			int size = new Integer(q.size());

			for (int i = 0; i < size; i++) {
				Node here = q.poll();

				if (lv == k) {
					sum += here.data;
				}

				if (lv > k) {
					return sum;
				}

				if (discovered.contains(here.left) == false && here.left != null) {
					discovered.add(here.left);
					q.add(here.left);
				}

				if (discovered.contains(here.right) == false && here.right != null) {
					discovered.add(here.right);
					q.add(here.right);
				}
			}

			lv++;
		}

		return sum;
	}

	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		Node(int data) {
			this(data, null, null);
		}
	}

	static Node makeNodes(String input) {
		Stack<Object> stack = new Stack<>();

		String s = "";

		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);

			if (ch == '(') {
				if (s.length() > 0) {
					stack.push(new Node(Integer.valueOf(s)));
					s = "";
				}
				stack.push(ch);
			} else if (Character.isDigit(ch)) {
				s += ch;
			} else {
				// case of ')'
				Object pop = stack.pop();

				if (pop instanceof Node) {
					Node nodePop = (Node)pop;

					stack.pop();

					if (stack.isEmpty()) {
						return (Node)pop;
					}

					if (((Node)stack.peek()).left == null) {
						((Node)stack.peek()).left = nodePop;
					} else {
						((Node)stack.peek()).right = nodePop;
					}
				}
			}
		}

		return null;
	}
}
