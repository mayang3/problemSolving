package leetcode;

import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInStrings {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();

        if (p.length() > s.length()) {
            return res;
        }

        int [] arr = new int[26];

        for (int i = 0; i < p.length(); i++) {
            arr[p.charAt(i) - 'a']--;
        }

        for (int i = 0; i < p.length(); i++) {
            arr[s.charAt(i) - 'a']++;
        }

        if (isAllZero(arr)) {
            res.add(0);
        }

        for (int right = p.length(); right < s.length(); right++) {
            int left = right - p.length();

            arr[s.charAt(right)-'a']++;
            arr[s.charAt(left)-'a']--;

            if (isAllZero(arr)) {
                res.add(left+1);
            }
        }

        return res;
    }

    private boolean isAllZero(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        FindAllAnagramsInStrings findAllAnagramsInStrings = new FindAllAnagramsInStrings();
        System.out.println(findAllAnagramsInStrings.findAnagrams("abab", "ab"));
    }
}
