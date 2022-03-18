package leetcode;

public class RepeatedStringMatch {
    public int repeatedStringMatch(String a, String b) {
        int count = (int)Math.ceil((double) b.length() / (double) a.length());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count; i++) {
            sb.append(a);
        }

        if (sb.toString().contains(b)) {
            return count;
        }

        if (sb.append(a).toString().contains(b)) {
            return count + 1;
        }

        return -1;
    }

    boolean kmp(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();

        int [] lps = new int[M];
        int j = 0;

        computeLPSArray(pat,lps);

        System.out.println(lps);

        return true;
    }

    private void computeLPSArray(String pat, int[] lps) {
        int len = 0;
        int i = 1;

        while (i < pat.length()) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }


    public static void main(String[] args) {
        String a = "abc";
        String b = "cabcabca";

        RepeatedStringMatch repeatedStringMatch = new RepeatedStringMatch();
        System.out.println(repeatedStringMatch.repeatedStringMatch(a, b));
    }
}
