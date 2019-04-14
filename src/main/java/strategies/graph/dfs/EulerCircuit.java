package strategies.graph.dfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baejunbeom
 */
public class EulerCircuit {

	// graph 의 인접행렬 표현. adj[i][j] = i 와 j 사이의 간선 수
//	List<List<Integer>> adj;

	static int [][] adj = null;

	// 무향 그래프의 인접 행렬 adj 가 주어질 때, 오일러 서킷을 계산한다.
	// 결과로 얻어지는 circuit 을 뒤집으면 오일러 서킷이 된다.

	void getEulerCircuit(int here, List<Integer> circuit) {
		for (int there=0 ; there < adj.length ; there++) {
			while (adj[here][there] > 0) {
				adj[there][here]--;
				adj[here][there]--;

				getEulerCircuit(there, circuit);
			}
		}

		circuit.add(here);
	}

	public static void main(String[] args) {
		EulerCircuit eulerCircuit = new EulerCircuit();
		adj = new int[8][8];

		connect(0, 1);
		connect(0, 7);
		connect(1, 2);
		connect(2, 3);
		connect(3, 7);
		connect(3, 4);
		connect(4, 5);
		connect(5, 6);
		connect(6, 3);

		List<Integer> circuit = new ArrayList<>();

		eulerCircuit.getEulerCircuit(0, circuit);

		System.out.println(circuit);
	}

	static void connect(int here, int there) {
		adj[here][there] = 1;
		adj[there][here] = 1;
	}
}
