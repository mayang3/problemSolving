package leetcode;

public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        Boolean [][] dp = new Boolean[s.length()+1][p.length()+1];

        return solve(dp, s, p, 0, 0);
    }

    boolean solve(Boolean [][] dp, String s, String pattern, int sIndex, int patternIndex) {

        if (dp[sIndex][patternIndex] != null) {
            return dp[sIndex][patternIndex];
        }

        if (patternIndex >= pattern.length()) {
            return sIndex >= s.length();
        }

        boolean res;
        char patterChar = pattern.charAt(patternIndex);

        // ? 에 매칭되는 경우, 종료조건이 s.length 에 대한 조건이 없기 때문에 이 조건에서 추가해준다.
        if (patterChar == '?' && sIndex < s.length()) {
            res = solve(dp, s, pattern, sIndex+1, patternIndex+1);
        } else if (patterChar == '*') {
            while (patternIndex < pattern.length()-1 && pattern.charAt(patternIndex+1) == '*') {
                patternIndex++;
            }

            // * 에 매칭되는 패턴은, 두가지 경우가 있다. 하나는 *를 생략하는 것이다. 다른 하나는 * 에 매칭되는 모든 경우의 수를 하나씩 늘려가면서 확인해보는 것이다.
            // 이때 모든 경우의 수를 체크할때에는 sIndex 를 늘려야 하므로 ?의 경우와 마찬가지로 sIndex 를 체크해주도록 하자.
            res = (solve(dp, s, pattern, sIndex, patternIndex+1) || (sIndex < s.length() && solve(dp, s, pattern, sIndex+1, patternIndex)));
        } else if (sIndex < s.length() && s.charAt(sIndex) == pattern.charAt(patternIndex)){
            res = solve(dp, s, pattern, sIndex+1, patternIndex+1);
        } else {
            res = false;
        }

        return dp[sIndex][patternIndex] = res;
    }

    public static void main(String[] args) {
        String s = "aa";
        String t = "*";

        WildcardMatching wildcardMatching = new WildcardMatching();
        System.out.println(wildcardMatching.isMatch(s, t));
    }
}
