package hackerrank.cs.dataStructure.tree;

import java.util.*;

/**
 * Hint) https://www.geeksforgeeks.org/print-binary-tree-vertical-order-set-2/
 *
 * @author baejunbeom
 */
@SuppressWarnings("ALL")
public class TopView {

	static class Node {
		int data;
		Node left;
		Node right;

		Node(int data) {
			this.data = data;
		}
	}

	/**
	 * vertical order 에서 맨 처음 녀석들만 출력하면 될듯..
	 * @param root
	 */
	void topView(Node root) {

		TreeMap<Integer, List<Integer>> verticalOrderMap = new TreeMap<>();

		makeTreeMap(root, verticalOrderMap, 0);

		printTopView(verticalOrderMap);

	}

	private void printTopView(TreeMap<Integer, List<Integer>> topViewMap) {
		Iterator<List<Integer>> iterator = topViewMap.values().iterator();

		int i=0;

		while (iterator.hasNext()) {

			List<Integer> next = iterator.next();

			System.out.print(next.get(0));

			if (i != topViewMap.values().size() - 1) {
				System.out.print(" ");
			}

			i++;
		}
	}

	/**
	 *
	 * @param root
	 * @param treeMap
	 * @param hp horizontalPoint
	 */
	private void makeTreeMap(Node root, TreeMap<Integer, List<Integer>> treeMap, int hp) {

		if (root == null) {
			return;
		}

		List<Integer> hpList = treeMap.get(hp);

		if (hpList == null) {
			List<Integer> newHpList = new ArrayList<>();
			newHpList.add(root.data);

			treeMap.put(hp, newHpList);
		} else {
			hpList.add(root.data);
			treeMap.put(hp, hpList);
		}

		if (root.left != null) {
			makeTreeMap(root.left, treeMap, hp - 1);
		}

		if (root.right != null) {
			makeTreeMap(root.right, treeMap, hp + 1);
		}
	}

	public static Node insert(Node root, int data) {
		if(root == null){
			return new Node(data);
		}
		else {
			Node cur;
			if(root.left == null){
				cur = insert(root.left, data);
				root.left = cur;
			}
			else if (root.right == null){
				cur = insert(root.right, data);
				root.right = cur;
			}
			return root;
		}
	}
	public static void main(String[] args) {
//		Scanner scan = new Scanner(System.in);
//		int t = scan.nextInt();
//		Node root = null;
//
//
//
//		while(t-- > 0){
//			int data = scan.nextInt();
//			root = insert(root, data);
//		}
//		scan.close();

//		String str = "37 23 108 59 86 64 94 14 105 17 111 65 55 31 79 97 78 25 50 22 66 46 104 98 81 90 68 40 103 77 74 18 69 82 41 4 48 83 67 6 2 95 54 100 99 84 34 88 27 72 32 62 9 56 109 115 33 15 91 29 85 114 112 20 26 30 93 96 87 42 38 60 7 73 35 12 10 57 80 13 52 44 16 70 8 39 107 106 63 24 92 45 75 116 5 61 49 101 71 11 53 43 102 110 1 58 36 28 76 47 113 21 89 51 19 3";
//
//		String [] splited = str.split(" ");
//
//		Node root = null;
//
//		for (String st : splited) {
//			root = insert(root, Integer.parseInt(st));
//		}

		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.right = new Node(4);
		root.left.right.right = new Node(5);
		root.left.right.right.right = new Node(6);

		TopView topView = new TopView();

		topView.topView(root);
	}

}
