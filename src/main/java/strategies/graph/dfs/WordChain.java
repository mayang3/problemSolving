package strategies.graph.dfs;

import java.util.*;

/**
 * @author baejunbeom
 */
public class WordChain {

	// 그래프의 인접 행렬 표현. adj[i][j] = i 와 j 사이의 간선의 수
	static int[][] adj = new int[26][26];

	// graph[i][j]=i 로 시작해서 j로 끝나는 단어의 목록
	static String [][] graph = new String[26][26];

	// indegree[i]= i로 시작하는 단어의 수
	static int[] indegree = new int[26];
	// outdegree[i]= i로 끝나는 단어의 수
	static int[] outdegree = new int[26];

	static {
		for (String [] inner : graph) {
			Arrays.fill(inner, "");
		}
	}

	/**
	 * 끝말잇기 문제의 입력을 그래프로 만들기
	 * @param words
	 */
	static void makeGraph(final List<String> words) {
		for (int i=0 ; i <words.size() ; i++) {

			int a = words.get(i).toCharArray()[0] - 'a'; // a~z 까지의 문자를 0~26 까지의 int 값으로 표현
			int b = words.get(i).toCharArray()[words.get(i).length() - 1] - 'a';

			graph[a][b] += words.get(i);
			// a, b 사이의 간선의 수
			adj[a][b]++;
			// a 로 나가는 간선의 수
			outdegree[a]++;
			// a 로 들어오는 간선의 수
			indegree[b]++;
		}
	}

	/*
		방향 그래프에서 오일러 서킷 혹은 트레일을 찾아내기
	 */

	// 유향 그래프의 인접 행렬 adj 가 주어질 때, 오일러 서킷 혹은 트레일을 계산한다.
	static void getEulerCircuit(int here, List<Integer> circuit) {
		for (int there=0 ; there<adj.length ; there++) {
			while (adj[here][there] > 0) {
				adj[here][there]--; // 간선을 지운다.
				getEulerCircuit(there, circuit);
			}
		}

		circuit.add(here);
	}

	static List<Integer> getEulerTrailOrCircuit() {
		List<Integer> circuit = new ArrayList<>();

		// 우선 오일러 트레일을 찾아본다 : 시작점이 존재하는 경우..
		for (int i=0 ; i<26 ; i++) {
			if (outdegree[i] == indegree[i] + 1) {
				getEulerCircuit(i, circuit);
				return circuit;
			}
		}

		// 아니면 서킷이니, 간선에 인접한 아무 정점에서나 시작한다.
		for (int i=0 ; i<26 ; i++) {
			if (outdegree[i] == 1) {
				getEulerCircuit(i, circuit);
				return circuit;
			}
		}

		// 모두 실패하면, 빈 배열을 반환한다.
		return circuit;
	}

	static boolean checkEuler() {
		// 예비 시작점과 끝점의 수
		int plus1=0, minus1=0;

		for (int i=0 ; i<26 ; i++) {
			int delta = outdegree[i] - indegree[i];

			// 모든 정점의 차수는 -1,1,0 중에 하나여야 한다.
			if (delta < -1 || delta > 1) return false;
			if (delta == 1) plus1++;
			if (delta == -1) minus1++;
		}

		// 오일러 서킷 or 트레일이 되려면...
		// 시작점과 끝점은 각 하나씩 있거나(트레일), 하나도 없어야 한다(서킷)
		return (plus1 == 1 && minus1 ==1) || (plus1 == 0 && minus1 == 0);
	}

	static String solve(final List<String> words) {
		makeGraph(words);

		// 차수가 맞지 않으면 실패!
		if (!checkEuler()) return "IMPOSSIBLE";

		// 오일러 서킷이나 경로를 찾아낸다.
		List<Integer> circuit = getEulerTrailOrCircuit();

		// 모든 간선을 방문하지 못했다면 실패. (즉, 오일러가 리턴되지 않았다면...)
		if (circuit.size() != words.size() + 1) return "IMPOSSIBLE";

		// 오일러인 경우, 방문 순서를 뒤집은 뒤, 간선들을 모아 문자열로 만들어 반환한다.
		Collections.reverse(circuit);

		String ret = "";

		for (int i=1 ; i<circuit.size() ; i++) {
			int a = circuit.get(i-1);
			int b = circuit.get(i);

			if (ret.length() > 0) ret += " ";

			ret += graph[a][b];
			graph[a][b] = "";
		}

		return ret;
	}


	public static void main(String[] args) {
		List<String> words = new ArrayList<>();
//		words.add("dog");
//		words.add("god");
//		words.add("dragon");
//		words.add("need");

//		words.add("ab");
//		words.add("cd");

		words.add("aa");
		words.add("ab");
		words.add("bb");

		System.out.println(solve(words));

//		System.out.println(Arrays.deepToString(graph));
//		System.out.println(Arrays.deepToString(adj));
//		System.out.println(Arrays.toString(outdegree));
//		System.out.println(Arrays.toString(indegree));
	}
}
