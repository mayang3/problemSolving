package algospot;

import java.util.Scanner;

public class 졸업학기 {
    static int N;
    static int K;
    static int M;
    static int L;

    final static int INF = 987654321;
    final static int MAX_N = 12;

    // i 번째 과목의 선수과목의 집합
    static int [] prerequisite;
    // i 번째 학기에 개설되는 과목의 집합
    static int [] classes;
    static Integer [][] dp;

    static int graduate(int semester, int taken) {
        // base case : 이미 k 개 이상의 과목을 들은 경우
        if (Integer.bitCount(taken) >= K) {
            return 0;
        }

        // m 학기가 이미 지난 경우
        if (semester == M) {
            return INF;
        }

        if (dp[semester][taken] != null) {
            return dp[semester][taken];
        }

        int ret = INF;

        // 이번 학기에 들을 수 있는 과목 중 아직 듣지 않은 과목들을 찾는다.
        int canTake = (classes[semester] & ~taken);
        // 선수 과목을 다 듣지 않은 과목들을 걸러낸다.
        for (int i = 0; i < N; i++) {
            // 아직 듣지 않은 과목중, 이번학기에 들을 수 있는 과목 중에, i 번째 과목을 뽑아내고 ((canTake & (1 << i)) != 0)
            // 그 i 번째 과목의 선수과목이 이미 들은 과목에 전부 포함되어있는지를 확인한다. ((taken & prerequisite[i]) != prerequisite[i])
            if ((canTake & (1 << i)) != 0 && ((taken & prerequisite[i]) != prerequisite[i])) {
                // i 과목이 선수과목을 이수하지 않은 과목이라면, canTake 에서 지워준다.
                canTake &= ~(1 << i);
            }
        }

        // 이 집합의 모든 부분집합을 순회한다.
        for (int take = canTake; take > 0 ; take = ((take - 1) & canTake)) {
            // 한 학기에 최대 L 과목까지만 들을 수 있다.
            if (Integer.bitCount(take) > L) {
                continue;
            }

            // 이번학기에 수강 가능한 전공과목의 모든 조합을 시도한다.
            ret = Math.min(ret, graduate(semester+1, taken | take) + 1);
        }

        // 이번 학기에 아무 것도 듣지 않을 경우
        return dp[semester][taken] = Math.min(ret, graduate(semester+1, taken));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int TC = scanner.nextInt(); // 테스트케이스의 수

        while (TC-- > 0) {
            N = scanner.nextInt(); // 전공과목의 수
            K = scanner.nextInt(); // 들어야할 과목의 수
            M = scanner.nextInt(); // 학기의 수
            L = scanner.nextInt(); // 한 학기에 최대로 들을 수 있는 과목의 수

            classes = new int[10];
            prerequisite = new int[MAX_N];
            dp = new Integer[10][1<<MAX_N];

            // 선수과목 입력
            for (int i = 0; i < N; i++) {
                int R = scanner.nextInt();

                for (int j = 0; j < R; j++) {
                    prerequisite[i] |= (1 << scanner.nextInt());
                }
            }

            // 학기별 개설 과목
            for (int i = 0; i < M; i++) {
                int C = scanner.nextInt();

                for (int j = 0; j < C; j++) {
                    classes[i] |= (1 << scanner.nextInt());
                }
            }

            int res = graduate(0, 0);

            System.out.println(res == INF ? "IMPOSSIBLE" : res);
        }
    }
}
