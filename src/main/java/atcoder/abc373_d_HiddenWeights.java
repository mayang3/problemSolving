package atcoder;

import java.util.*;

/**
 * 일반적으로 크기적 측면에서는 10^5 이상이면 재귀적 DFS 보다는 반복적 DFS 를 고려하자.
 *
 * 재귀적으로 함수를 호출하게 되면,
 *
 * 암시적으로 stack memory 사용 이외에도, 재귀 호출 자체에서의 비용이 발생한다.
 *
 * 보통 함수 호출에는 다음과 같은 비용들이 발생하며, 재귀 호출시에 이 비용들이 계속해서 누적되게 된다.
 *
 * 1. 매개변수 처리 (Parameter Passing):
 *   호출하는 함수에 필요한 매개변수를 스택 또는 레지스터에 복사해서 넘겨줘야 합니다.
 *   이 과정에서 매개변수의 복사 비용이 발생합니다. 매개변수가 많거나, 복사해야 할 데이터가 크면 이 비용이 커집니다.
 *
 * 2. 스택 프레임 생성 (Stack Frame Creation):
 *
 * 함수가 호출되면 그 함수에 대한 스택 프레임(stack frame)이 생성됩니다. 스택 프레임에는 그 함수의 매개변수, 지역 변수, 복귀 주소 등이 포함됩니다.
 * 이 스택 프레임은 시스템의 호출 스택(call stack)에 쌓이는데, 스택 프레임을 생성하고 관리하는 비용이 발생합니다.
 *
 *
 * 3. 복귀 주소 저장 (Saving Return Address):
 *
 * 함수 호출이 끝나면 호출한 함수로 돌아가기 위해 복귀 주소가 필요합니다. 복귀 주소는 호출 스택에 저장되며, 함수가 종료되면 이 복귀 주소를 참조하여 제어 흐름이 호출한 지점으로 돌아갑니다.
 *
 *
 * 4. 제어 흐름 변경 (Control Transfer):
 *
 * 함수가 호출되면 프로그램의 실행 흐름이 함수 내부로 이동합니다. 함수가 종료되면 다시 호출한 곳으로 돌아와야 합니다.
 * 이 과정에서 CPU는 제어 흐름을 변경하는 작업을 해야 하며, 이 또한 비용이 발생합니다.
 *
 *
 * 5. 함수 종료 시 스택 정리 (Stack Frame Cleanup):
 *
 * 함수가 종료되면 해당 함수의 스택 프레임을 제거해야 합니다. 이 과정에서 스택에 저장되어 있던 매개변수, 지역 변수, 복귀 주소 등을 정리합니다.
 * 스택 정리는 함수 호출 시와 마찬가지로 추가적인 연산을 필요로 하므로 시간이 소요됩니다.
 *
 * @author neo82
 */
public class abc373_d_HiddenWeights {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int M = scanner.nextInt();

        Map<Integer, List<int[]>> graph = new HashMap<>();

        for (int i = 0; i < N; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int u = scanner.nextInt() - 1;
            int v = scanner.nextInt() - 1;
            int w = scanner.nextInt();

            graph.computeIfAbsent(u, t -> new ArrayList<>()).add(new int[]{v, w});
            graph.computeIfAbsent(v, t -> new ArrayList<>()).add(new int[]{u, -w});
        }

        long[] ans = new long[N];
        boolean[] visited = new boolean[N];

        for (int u = 0; u < N; u++) {
            if (!visited[u]) {
                Stack<Integer> stack = new Stack<>();
                stack.push(u);
                visited[u] = true;

                while (!stack.isEmpty()) {
                    int here = stack.pop();

                    for (int[] adj : graph.get(here)) {
                        int v = adj[0];
                        int w = adj[1];

                        if (!visited[v]) {
                            visited[v] = true;
                            ans[v] = ans[here] + w;
                            stack.add(v);
                        }
                    }

                }
            }
        }

        print(ans);
    }

    private static void print(long[] ans) {
        for (long a : ans) {
            System.out.print(a + " ");
        }
    }
}
