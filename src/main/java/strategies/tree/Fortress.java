package strategies.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author baejunbeom
 */
public class Fortress {

	static class TreeNode {
		List<TreeNode> childeren;
	}

	// 지금까지 찾은 가장 긴 잎-잎 경로의 길이를 구한다.
	int longest;

	// root 를 루트로 하는 서브트리의 높이를 계산한다.
	public int height(TreeNode root) {
		// 각 자식을 루트로 하는 서브트리의 높이를 계산한다.
		List<Integer> heights = new ArrayList<>();

		for (int i=0 ; i < root.childeren.size() ; i++) {
			heights.add(height(root.childeren.get(i)));
		}

		// 만약 자식이 하나도 없다면 0 을 반환한다.
		if (heights.isEmpty()) {
			return 0;
		}

		Collections.sort(heights);

		// root 를 최상위 노드로 하는 경로를 고려하자.
		if (heights.size() >= 2) {
			longest = Math.max(longest, 2 + heights.get(heights.size() - 2) + heights.get(heights.size() - 1));
		}

		// 트리의 높이는 서브트리 높이의 최대치에 1을 더해 계산한다.
		// 정렬이 된 상태이므로 heights 의 가장 마지막 element 가 서브트리의 최대 높이가 된다.
		return heights.get(heights.size() - 1) + 1;
	}

	// 두 노드 사이의 가장 긴 경로를 계산한다.
	int solve(TreeNode root) {
		longest = 0;
		// 트리의 높이와 최대 잎-잎 경로 길이 중 큰 것을 선택한다.
		// height 를 수행하면서 전역변수 longest(최대 잎-잎 길이) 를 바꿈
		int h = height(root);

		return Math.max(longest, h);
	}

	// 주어진 번호의 성벽에 포함된 구역들을 표현하는 트리를 생성한다.
	// root 성벽을 루트로 하는 트리를 생성한다.
	TreeNode getTree(int root) {
		TreeNode ret = new TreeNode();

		for (int ch = 0 ; ch < n ; ch++) {
			// ch 성벽이 root 성벽에 직접적으로 포함되어 있다면,
			// 트리를 만들고 자손목록에 추가한다.
			if (isChild(root, ch)) {
				ret.childeren.add(getTree(ch));
			}
		}

		return ret;
	}

	// 아래는 한 성벽이 다른 성벽에 포함되었는지, 그리고 직접 포함되었는지 확인하는 함수들

	int n;
	int [] y = new int[100];
	int [] x = new int[100];
	int [] radius = new int[100];

	int sqr(int x) {
		return x*x;
	}

	// 두 성벽 a,b 의 중심점 간의 거리의 제곱을 반환한다.
	int sqrdist(int a, int b) {
		return sqr(y[a] - y[b]) + sqr(x[a] - x[b]);
	}

	// 성벽 a가 성벽 b를 포함하는지 확인한다.
	boolean encloses(int a, int b) {
		return radius[a] > radius[b] &&
				sqrdist(a, b) < sqr(radius[a] - radius[b]);
	}

	// '성벽' 트리에서 parent 가 child 의 부모인지를 확인한다.
	// parent 는 child 를 꼭 직접 포함해야 한다.
	private boolean isChild(int parent, int child) {
		if (!encloses(parent, child)) {
			return false;
		}

		for (int i=0 ; i<n ; i++) {
			// parent 와 child 사이에 포함관계를 가지는 녀석이 더 있는지를 확인한다.
			if (i != parent && i != child && encloses(parent, i) && encloses(i, child)) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) {


		Fortress fortress = new Fortress();
		System.out.println(fortress.solve(null));
	}


}
