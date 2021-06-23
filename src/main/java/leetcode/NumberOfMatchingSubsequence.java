package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class NumberOfMatchingSubsequence {
    public int numMatchingSubseq(String s, String[] words) {
        Map<Character, TreeSet<Integer>> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            map.computeIfAbsent(s.charAt(i), t -> new TreeSet<>()).add(i);
        }

        int cnt = 0;

        for (String word : words) {
            int lastIdx = -1;
            boolean subSeq = true;

            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                if (map.containsKey(ch) == false) {
                    subSeq = false;
                    break;
                }

                Integer ceiling = map.get(ch).higher(lastIdx);

                if (ceiling == null) {
                    subSeq = false;
                    break;
                }

                lastIdx = ceiling;
            }

            if (subSeq) {
                cnt++;
            }
        }

        return cnt;
    }

    public static void main(String[] args) {
        String [] words = {"aaaaaa"};

        NumberOfMatchingSubsequence numberOfMatchingSubsequence = new NumberOfMatchingSubsequence();
        System.out.println(numberOfMatchingSubsequence.numMatchingSubseq("aaaaa", words));
    }
}
