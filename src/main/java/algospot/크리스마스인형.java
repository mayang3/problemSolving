package algospot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 크리스마스인형 {
    static int MOD = 20091101;

    // D[] 의 부분 합 배열 psum[] 과  k 가 주어질 때, 몇 가지 방법으로 살 수 있는지 반환한다.
    // psum[] 의 첫 번째 원소 전에 0을 삽입했다고 가정한다.
    static int waysToBuy(List<Integer> prefixSum, int k) {
        long ret = 0;

        // psum[] 의 각 값을 카운팅 한다.
        long [] count = new long[k];

        for (int i = 0; i < prefixSum.size(); i++) {
            count[prefixSum.get(i)]++;
        }

        // 두 번 이상 본 적이 있다면 이 값 중 두 개를 선택하는 방법의 수를 더한다.
        for (int i = 0; i < k; i++) {
            if (count[i] >= 2) {
                ret = (ret + ((count[i] * (count[i] - 1)) / 2)) % MOD;
            }
        }

        return (int)ret;
    }

    // D[] 의 부분 합 배열 prefixSum[] 과 K 가 주어질 때, 겹치지 않게 몇 번이나 살 수 있는지 반환한다.
    static int maxBuys(List<Integer> prefixSum, int k) {
        // re[i] = 첫 번째 상자부터 i 번째 상자까지 고려했을 때 살 수 있는 최대 횟수
        int [] ret = new int[prefixSum.size()];
        // prev[s] = prefixSum[] 이 s 였던 마지막 위치
        int [] prev = new int[k];
        Arrays.fill(prev, -1);

        for (int i = 0; i < prefixSum.size(); i++) {
            // i번째 상자를 아예 고려하지 않는 경우
            if (i > 0) {
                ret[i] = ret[i-1];
            } else {
                ret[i] = 0;
            }

            // prefixSum[i] 를 전에도 본 적이 있으면, prev[prefixSum[i]] + 1 부터 여기까지 쭉 사 본다.
            int loc = prev[prefixSum.get(i)];
            if (loc != -1) {
                ret[i] = Math.max(ret[i], ret[loc] + 1);
            }

            // prev[] 에 현재 위치를 기록한다.
            prev[prefixSum.get(i)] = i;
        }

        return ret[ret.length-1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int T = scanner.nextInt();

        while (T-- > 0) {
            // 인형상자의 갯수
            int N = scanner.nextInt();
            // 어린이의 명수
            int K = scanner.nextInt();

            List<Integer> prefixSum = new ArrayList<>();
            prefixSum.add(0);

            long sum = 0;

            for (int i = 0; i < N; i++) {
                sum += scanner.nextInt();
                prefixSum.add((int)(sum % K));
            }

            System.out.println(waysToBuy(prefixSum, K) + " " + maxBuys(prefixSum, K));
        }

    }
}
