package algospot;

import java.util.Scanner;

public class 팰린드롬만들기 {

    // s1 의 접미사이면서 s2 의 접두사인 문자열의 최대 길이를 구한다.
    static int maxOverlap(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();

        int [] pi = getPartialMatch(s2);

        int begin = 0, matched = 0;

        while (begin < n1) {
            // 만약 짚더미의 해당 글자가 바늘의 해당 글자와 같다면
            if (matched < n2 && s1.charAt(begin + matched) == s2.charAt(matched)) {
                matched++;
                if (begin + matched == n1) {
                    return matched;
                }
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                  // begin  matched
                    // |       |
                    // A A B A A D X X
                    // A A B A A C
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }

        return 0;
    }

    static int[] getPartialMatch(String pat) {
        int M = pat.length();

        int [] pi = new int[M];
        // pi 를 구하는 부분에서의 i 는 1부터 시작한다.
        // 접두사, 접미사 배열을 구하는 특성상, 전체 문자열은 제외되기 때문이다.
        int i = 1;
        int len = 0;

        // pattern 문자열을 탐색하여 얻는 결과이니만큼, 범위는 1~M 이 된다.
        // 여기서 1은 KMP 에서 접두사이기도 하고, 접미사이기도 한 값을 구하는데 있어서 전체 문자열은 제외되기 때문이다.
        // 예를 들어, AAA 에서 AAA 는 카운트되지 않는다. (A, AA) 만 카운트 된다.
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                // 현재문자열이 일치하는 경우, 일치하는 접두사의 길이를 증가시킨다.
                len++;
                // 현재까지 증가된 접두사의 길이가 pi 의 최대 길이가 된다.
                pi[i++] = len;
            } else if (len == 0) {
                // 일치하지 않았는데 len == 0 인 경우라면, 아래 패턴의 문자는 맨 처음인데 위의 패턴 문자열이 대응되지 않았다는 뜻이므로,
                // 위의 패턴 문자열을 증가시켜야 한다.
                i++;
            } else {
                // 일치하지 않고, len != 0 인 경우이다.
                // 이 부분이 KMP 에서 건너뛰는 핵심인데, 현재 len 은 불일치한 문자의 위치를 나타낸다.
                // 그러므로 바로 이전의 문자까지가 최대한 일치한 길이를 나타내는 값이 저장되어있고, 인덱스로 보면 pi[len-1] 이다.
                // 이때, 이 값을 다시 len 에 할당하는 것은 해당 길이만큼 건너뛰면, 0부터 시작하는 인덱스의 특성상, len 이 다음에 비교할 문자가 되기 때문이다.
                // 예를 들어, AAAB 의 문자열이 B 에서 불일치가 발생했다고 하면, len=3인 상태이고, 바로 이전까지 매칭된 최대 매칭 문자열의 길이는
                // pi[3-1] = 2 가 된다. 이것은 길이이며, len=2로 할당하게되면, 인덱스의 특성상 AA 문자열 길이(2) 만큼을 건너뛰고 다음 문자 A(인덱스:3) 에
                // 매칭을 시도하게 된다.
                len = pi[len-1];
            }
        }

        return pi;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int C = scanner.nextInt();

        while (C-- > 0) {
            String s = scanner.next();
            int n = s.length();
            int res = maxOverlap(s, new StringBuilder(s).reverse().toString());
            System.out.println(n == res ? n : 2 * n - res);
        }
    }
}
