package strategies.graph.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author baejunbeom
 */
public class Dictionary {

	// 알파벳의 각 글자에 대한 인접 행렬 표현
	// 간선 i,j 는 알파벳 i가 j보다 앞에 와야 함을 나타낸다.
	List<List<Integer>> adj;

	// 주어진 단어들로부터 알파벳 간의 선후관계 그래프를 생성한다.
	void makeGraph(final List<String> words) {
		adj = new ArrayList<>(26);

		for (int z=0 ; z<26; z++) {
			Integer[] subList = new Integer[26];

			Arrays.fill(subList, 0);

			adj.add(Arrays.asList(subList));
		}

		for (int j=1; j<words.size(); j++) {
			int i=j-1;
			int len = Math.min(words.get(i).length(), words.get(j).length());

			// words[i] 가 words[j] 의 앞에 오는 이유를 찾는다.
			// 앞에 오는데 역할을 하는 단어만 그래프의 간선표시를 한다.
			// 역할을 하지 않는녀석은 굳이 연결할 필요가 없음..
			for (int k=0; k<len; k++) {
				if (words.get(i).toCharArray()[k] != words.get(j).toCharArray()[k]) {
					int a = words.get(i).toCharArray()[k] - 'a';
					int b = words.get(j).toCharArray()[k] - 'a';
					adj.get(a).set(b, 1);
					break;
				}
			}
		}
	}

	// 깊이 우선 탐색을 이용한 위상 정렬
	List<Integer> seen; // 기존의 visited 배열과 동일한 기능. 이미 방문한 정점인지를 확인한다.
	List<Integer> order = new ArrayList<>(26);

	void dfs(int here) {
		// 정점의 visited 확인
		seen.set(here, 1);

		for (int there=0; there<adj.size(); there++) {
			// 인접 정점들을 확인하는데..
			// 단어사전에 간선으로 연결된 정점들중에, visited 가 확인되지 않은 녀석들만 검색한다.
			if (adj.get(here).get(there) == 1 && seen.get(there) != 1) {
				dfs(there);
			}
		}

		// 맨 마지막 노드들부터 더한다.
		order.add(here);
	}

	// adj 에 주어진 그래프를 위상 정렬한 결과를 반환한다.
	// 그래프가 DAG 가 아니라면 빈 리스트를 반환한다.
	List<Integer> topologycalSort() {
		int n = adj.size();

		seen = new ArrayList<>(n);

		for (int i=0 ; i<n ; i++) {
			seen.add(0);
		}

		// 알파벳 26개 마다 깊이우선탐색한다.
		for (int i=0; i<n; i++) {
			if (seen.get(i) != 1) {
				dfs(i);
			}
		}

		// 맨 마지막 노드들부터 recursive 하게 저장된 order 를 역순 정렬하면 위상정렬이 된다.
		// 그냥 스택을 쓰는게 나을듯..
		Collections.reverse(order);

		// (핵심) 만약 그래프가 DAG 가 아니라면 정렬 결과에 역방향 간선이 있다. 이것을 체크한다.
		for (int i=0; i<n; i++) {
			for (int j=i+1; j<n; j++) {
				if (adj.get(order.get(j)).get(order.get(i)) == 1) {
					return new ArrayList<>();
				}
			}
		}

		// 없는 경우라면 우선 탐색에서 얻은 순서를 반환한다.
		return order;
	}

	public static void main(String[] args) {
		List<String> words = new ArrayList<>();
		words.add("gg"); // g->k : 6,10 = true
		words.add("kia"); // k->l : 10, 11 = true
		words.add("lotte"); // o->g : 14, 6 = true
		words.add("lg"); // l->h : 11, 7 = true
		words.add("hanwha");

//		words.add("bb");
//		words.add("aa");
//		words.add("ab");

		words.add("dictionary");
		words.add("english");
		words.add("is");
		words.add("ordered");
		words.add("ordinary");
		words.add("this");

		Dictionary dictionary = new Dictionary();
		dictionary.makeGraph(words);

		List<Integer> integers = dictionary.topologycalSort();

		for (Integer i : integers) {
			System.out.print(Character.toString((char)(i+'a')));
		}
	}

}
