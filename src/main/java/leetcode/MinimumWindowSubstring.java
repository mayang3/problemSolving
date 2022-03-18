package leetcode;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if(s == null || s.length() < t.length() || s.length() == 0){
            return "";
        }

        int start = -1;
        int end = -1;
        int min = Integer.MAX_VALUE;

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            map.merge(t.charAt(i), 1, Integer::sum);
        }

        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            map.merge(s.charAt(right), -1, Integer::sum);

            while (isCover(map) && left <= right) {
                if (right - left < min) {
                    start = left;
                    end = right;
                    min = right - left;
                }

                map.merge(s.charAt(left), 1, Integer::sum);
                left++;
            }
        }

        if (start == -1) {
            return "";
        }

        return s.substring(start, end+1);
    }

    private boolean isCover(Map<Character, Integer> map) {
        for (int val : map.values()) {
            if (val > 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        MinimumWindowSubstring minimumWindowSubstring = new MinimumWindowSubstring();
        System.out.println(minimumWindowSubstring.minWindow("ADOBECODEBANC", "ABC"));
    }
}
