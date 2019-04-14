package strategies.graph.dfs;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author baejunbeom
 */
public class Tarjan {
	int [][] adj;

	// 각 정점의 컴포넌트 번호. 컴포넌트 번호는 0부터 시작하며,
	// 같은 강결합 컴포넌트에 속한 정점들의 컴포넌트 번호가 같다.
	int [] sccId;
	int [] discovered;
	int [] finished;

	// 정점의 번호를 담는 스택
	Stack<Integer> stack;

	int sccCounter;
	int vertexCounter;

	// here 를 루트로 하는 서브트리에서 역방향 간선으로 닿을 수 있는 최소의 발견 순서를 반환한다.
	int scc(int here) {
		int ret = discovered[here] = vertexCounter++;

		// 스택에 here 를 넣는다. here 의 후손들은 모두 스택에서 here 후에 들어간다.
		stack.push(here);

		for (int there=0 ; there<adj[here].length ; there++) {
			if (adj[here][there] != 1) {
				continue;
			}

			// (here,there) 가 트리 간선
			if (discovered[there] == -1) {
				ret = Math.min(ret, scc(there));
			} else if (discovered[there] < discovered[here] && finished[there] != 1) {
				ret = Math.min(ret, discovered[there]);
			}
		}

			// here 가 강결합 컴포넌트의 루트인지 확인한다.
			if (ret == discovered[here]) {
				// here 를 루트로 하는 서브트리에 남아있는 정점들은 전부 하나의 컴포넌트로 묶는다.
				while (true) {
					int t = stack.pop();
					sccId[t] = sccCounter;
					if (t == here) break;
				}

				++sccCounter;
			}

		finished[here] = 1;
		return ret;
	}

	// tarjan 의 SCC 알고리즘
	int [] tarjanSCC() {
		// array init
		Arrays.fill(sccId, -1);
		Arrays.fill(discovered, -1);
		Arrays.fill(finished, -1);

		sccCounter = vertexCounter = 0;

		// 모든 정점에 대해 scc() 호출
		for (int i=0 ; i<adj.length ; i++) {
			if (discovered[i] == -1) scc(i);
		}

		return sccId;
	}

}
