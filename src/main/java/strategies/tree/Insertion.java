package strategies.tree;

import java.util.TreeMap;

/**
 * @author baejunbeom
 */
public class Insertion {

	TreeMap<Integer, Integer> reorder(TreeMap<Integer, Integer> inputMap, int[] steps) {

		if (inputMap.size() == 1) {
			return inputMap;
		}

		int step = steps[inputMap.size() - 1];

		if (step != 0) {
			Integer value = inputMap.remove(inputMap.size() - 1 - step);
			// 이렇게 풀어서는 안됨...
			// 왜냐하면 삭제할때마다 index 를 바꿔주어야 하기 때문에, 이래서는 array 를 써서 밀었다 당겼다 하는것과 다를 바 없음
		}

		return null;
	}

	public static void main(String[] args) {

		Treap treap = new Treap();

		Treap.Node root = null;

		for (int i=0 ; i<5 ; i++) {
			root = treap.insert(root, new Treap.Node(i+1));
		}

		int [] steps = {
			0,1,1,2,3
		};

		for (int i=steps.length -1 ; i>=0 ; i--) {
			int step = steps[i];

			Treap.Node kth = treap.kth(root, i + 1 - step);

			System.out.println(kth);

			root = treap.erase(root, kth.key);
		}
	}
}
