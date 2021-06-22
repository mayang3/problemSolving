package leetcode;

public class CountBinarySubstrings {
    public int countBinarySubstrings(String s) {
        int curCount = 1;
        int prevCount = 0;
        int ret = 0;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i-1) == s.charAt(i)) {
                curCount++;
            } else {
                ret += Math.min(curCount, prevCount);
                prevCount = curCount;
                curCount = 1;
            }
        }

        return ret + Math.min(prevCount, curCount);
    }

    public static void main(String[] args) {
        CountBinarySubstrings countBinarySubstrings = new CountBinarySubstrings();
        System.out.println(countBinarySubstrings.countBinarySubstrings("0110001111"));
    }
}
