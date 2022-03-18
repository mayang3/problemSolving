package leetcode;

public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        Boolean [][] dp = new Boolean[s.length()+1][p.length()+1];

        return solve(dp, s, p, 0, 0);
    }

    private boolean solve(Boolean[][] dp, String s, String p, int sIndex, int pIndex) {
        if (dp[sIndex][pIndex] != null) {
            return dp[sIndex][pIndex];
        }

        // 이와 같이 하면 오류가 발생한다.
        // 만약 s="aa", p="a" 라고 할때, s 의 두번째 인덱스인 sIndex=1 을 검사하고자 하면,
        // 이 문자는 아직 검사가 안되었기 때문에 이 base case 를 통과한다.
        // 하지만, 패턴은 인덱스의 범위를 벗어났기 때문에, 33번 라인의 firstMatch 를 구하는 식의 p.charAt(pIndex) 에서 outOfBounds 오류가 발생한다.

        // 즉, 이 로직은 pattern 을 기반으로 돌아가기 때문에 모든 pattern 을 다 사용할때까지는 계속해서 동작해야 한다.
//        if (sIndex >= s.length()) {
//            return pIndex >= p.length();
//        }

        // 모든 패턴을 다 사용했는데, 남은 문자열이 있다면 유효하지 않은 문자열이다.
        if (pIndex >= p.length()) {
            return sIndex >= s.length();
        }

        boolean res;

        boolean firstMatch = sIndex < s.length() && (p.charAt(pIndex) == '.' || s.charAt(sIndex) == p.charAt(pIndex));

        // 다음 문자가 * 라면,
        if (pIndex < p.length() - 1 && p.charAt(pIndex+1) == '*') {
            res = ((firstMatch && solve(dp, s, p, sIndex+1, pIndex))
                    || solve(dp, s, p, sIndex, pIndex+2)); // 다음 문자가 * 라면, 처음 문자가 매칭 안되어도 패턴을 생략하는 경우에는 true 가 가능할 수 있다.
        } else {
            // 다음 문자가 * 가 아닌 경우
            res = firstMatch && solve(dp, s, p, sIndex+1, pIndex+1);
        }

        return dp[sIndex][pIndex] = res;
    }

    public static void main(String[] args) {
        String s = "aa";
        String p = "a";

        RegularExpressionMatching regularExpressionMatching = new RegularExpressionMatching();
        System.out.println(regularExpressionMatching.isMatch(s, p));
    }
}
