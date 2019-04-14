package hackerrank.compete;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * Accepted
 */
public class HeightAndTotalHeightOfBST {

	static class Node {
		int data;
		Node left;
		Node right;


		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("Node{");
			sb.append("data=").append(data);
			sb.append(", left=").append(left);
			sb.append(", right=").append(right);
			sb.append('}');
			return sb.toString();
		}
	}

	static int[] heightAndTotalHeight(int[] arr) {

		Node root = new Node(arr[0], null, null);

		for (int i=1 ; i<arr.length ; i++) {
			makeBST(root, arr[i]);
		}

		Heights solve = solve(root);

		int [] ret = new int[2];
		ret[0] = solve.height;
		ret[1] = solve.totalHeight;

		return ret;
	}

	private static Heights solve(Node root) {
		if (root.left == null && root.right == null) {
			return new Heights(0, 0);
		}

		int sum = 0;
		int max = Integer.MIN_VALUE;

		if (root.left != null) {
			Heights leftHeights = solve(root.left);
			max = Math.max(leftHeights.height + 1, max);
			sum += leftHeights.totalHeight;
		}

		if (root.right != null) {
			Heights rightHeights = solve(root.right);
			max = Math.max(rightHeights.height + 1, max);
			sum += rightHeights.totalHeight;
		}

		sum += max;

		return new Heights(max, sum);
	}

	static class Heights {
		int height;
		int totalHeight;

		Heights(int height, int totalHeight) {
			this.height = height;
			this.totalHeight = totalHeight;
		}

		@Override
		public String toString() {
			final StringBuilder sb = new StringBuilder("Heights{");
			sb.append("height=").append(height);
			sb.append(", totalHeight=").append(totalHeight);
			sb.append('}');
			return sb.toString();
		}
	}

	private static void makeBST(Node node, int data) {
		if (node.data > data) {
			if (node.left != null) {
				makeBST(node.left, data);
			} else {
				node.left = new Node(data, null, null);
			}
		} else if (node.data < data) {
			if (node.right != null) {
				makeBST(node.right, data);
			} else {
				node.right = new Node(data, null, null);
			}
		}
	}

	private static final Scanner scan = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
		if (bw == null) {
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
		}

		int arrSize = Integer.parseInt(scan.nextLine().trim());

		int[] arr = new int[arrSize];

		String[] arrItems = scan.nextLine().split(" ");

		for (int arrItr = 0; arrItr < arrSize; arrItr++) {
			int arrItem = Integer.parseInt(arrItems[arrItr].trim());
			arr[arrItr] = arrItem;

		}

		int[] result = heightAndTotalHeight(arr);

		for (int resultItr = 0; resultItr < result.length; resultItr++) {
			bw.write(String.valueOf(result[resultItr]));

			if (resultItr != result.length - 1) {
				bw.write("\n");
			}
		}

		bw.newLine();

		bw.close();
	}
}
