package leetcode;

import baekjoon.dp.배낭_BottomUp_Optimize;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SmallestSubsequenceOfDistinctCharacter {

    public String smallestSubsequence(String s) {
        List<String> res = new ArrayList<>();

        Set<Character> set = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            set.add(s.charAt(i));
        }

        solve(res, set.size(), new StringBuilder(), new boolean[26] ,s, 0);

        return res.get(0);
    }

    private void solve(List<String> res, int size, StringBuilder sb, boolean [] visited, String s, int i) {
        if (i >= s.length()) {
            if (sb.length() == size) {
                    if (res.isEmpty()) {
                        res.add(sb.toString());
                    } else if (sb.toString().compareTo(res.get(0)) < 0) {
                        res.set(0, sb.toString());
                    }
            }

            return;
        }

        char ch = s.charAt(i);

        if (visited[ch-'a'] == false) {
            visited[ch-'a'] = true;
            sb.append(ch);

            solve(res, size, sb, visited, s, i+1);

            sb.deleteCharAt(sb.length() - 1);
            visited[ch-'a'] = false;
        }

        solve(res, size, sb, visited, s, i+1);
    }


    public static void main(String[] args) {
        SmallestSubsequenceOfDistinctCharacter subsequence = new SmallestSubsequenceOfDistinctCharacter();
        System.out.println(subsequence.smallestSubsequence("abc"));
    }
}
