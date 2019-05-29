package hackerrank.cs.dataStructure.tree;

/**
 * @author neo82
 */
public class HuffmanDecoding {
	public static void main(String[] args) {
		HuffmanDecoding hd = new HuffmanDecoding();


		Node ll = new Node(1, 'B', null, null);
		Node lr = new Node(1, 'C', null, null);

		Node l = new Node(2, null, ll, lr);
		Node r = new Node(3, 'A', null, null);

		Node root = new Node(5, null, l, r);

		hd.decode("1001011", root);
	}

	static class Node {
		int freuqency;
		Character data;
		Node left, right;

		public Node(int freuqency, Character data, Node left, Node right) {
			this.freuqency = freuqency;
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

	void decode(String s, Node root) {

		int i = 0;

		StringBuilder sb = new StringBuilder();

		while (i < s.length()) {

			Node next = root;

			while (true) {
				char ch = s.charAt(i);

				if (ch == '0') {
					next = next.left;
				} else {
					next = next.right;
				}

				i++;

				if (next.left == null && next.right == null) {
					sb.append(next.data);
					break;
				}

			}
		}

		System.out.println(sb.toString());
	}

}
