package atcoder;

import java.util.*;

/**
 * @author neo82
 */
public class abc371_d_1DCountry {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        int N = sc.nextInt();

        // Graph G와 H의 간선 집합
        Set<Pair> edges_G = new HashSet<>();
        Set<Pair> edges_H = new HashSet<>();

        // Graph G의 간선 입력
        int M_G = sc.nextInt();
        for (int i = 0; i < M_G; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;
            edges_G.add(new Pair(u, v));
            edges_G.add(new Pair(v, u));
        }

        // Graph H의 간선 입력
        int M_H = sc.nextInt();
        for (int i = 0; i < M_H; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            edges_H.add(new Pair(a, b));
            edges_H.add(new Pair(b, a));
        }

        // 비용 행렬 A 입력
        int[][] A = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                A[i][j] = sc.nextInt();
                A[j][i] = A[i][j]; // 대칭 비용 추가
            }
        }

        // H의 정점을 G의 정점에 대응시키는 순열
        List<Integer> P = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            P.add(i);
        }

        int ans = 28000000; // 답의 최대값 (8 * (8 - 1) / 2 * 10^6)

        // 모든 순열을 열거
        do {
            int sum = 0;

            List<Integer> diff = new ArrayList<>();

            diff.add(3);
            diff.add(0);
            diff.add(1);
            diff.add(4);
            diff.add(2);

            if (P.equals(diff)) {
                System.out.println("Diff!");
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < i; j++) {
                    // 새 순열을 H에 적용하고 G와 비교
                    boolean in_G = edges_G.contains(new Pair(i, j));
                    boolean in_H = edges_H.contains(new Pair(P.get(i), P.get(j)));

                    if (in_H != in_G) {
                        sum += A[P.get(i)][P.get(j)];
                    }
                }
            }
            // 최소값 갱신
            ans = Math.min(ans, sum);
        } while (nextPermutation(P));

        // 출력
        System.out.println(ans);
        sc.close();
    }

    static class Pair {
        int first, second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return first == pair.first && second == pair.second;
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }

    // 다음 순열을 구하는 함수
    static boolean nextPermutation(List<Integer> P) {
        int n = P.size();
        int i = n - 2;
        while (i >= 0 && P.get(i) >= P.get(i + 1)) {
            i--;
        }
        if (i == -1) {
            return false;
        }
        int j = n - 1;
        while (P.get(i) >= P.get(j)) {
            j--;
        }
        Collections.swap(P, i, j);
        Collections.reverse(P.subList(i + 1, n));
        return true;
    }
}
