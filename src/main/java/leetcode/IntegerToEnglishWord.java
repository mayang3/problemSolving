package leetcode;

import java.util.HashMap;
import java.util.Map;

public class IntegerToEnglishWord {

    private static Map<Integer, String> singleDigit = new HashMap<>();
    private static Map<Integer, String> twoDigit = new HashMap<>();

    static {
        singleDigit.put(0, "Zero");
        singleDigit.put(1, "One");
        singleDigit.put(2, "Two");
        singleDigit.put(3, "Three");
        singleDigit.put(4, "Four");
        singleDigit.put(5, "Five");
        singleDigit.put(6, "Six");
        singleDigit.put(7, "Seven");
        singleDigit.put(8, "Eight");
        singleDigit.put(9, "Nine");

        twoDigit.put(0, "");
        twoDigit.put(1, "One");
        twoDigit.put(2, "Two");
        twoDigit.put(3, "Three");
        twoDigit.put(4, "Four");
        twoDigit.put(5, "Five");
        twoDigit.put(6, "Six");
        twoDigit.put(7, "Seven");
        twoDigit.put(8, "Eight");
        twoDigit.put(9, "Nine");
        twoDigit.put(10, "Ten");
        twoDigit.put(11, "Eleven");
        twoDigit.put(12, "Twelve");
        twoDigit.put(13, "Thirteen");
        twoDigit.put(14, "Fourteen");
        twoDigit.put(15, "Fifteen");
        twoDigit.put(16, "Sixteen");
        twoDigit.put(17, "Seventeen");
        twoDigit.put(18, "Eighteen");
        twoDigit.put(19, "Nineteen");
        twoDigit.put(20, "Twenty");
        twoDigit.put(30, "Thirty");
        twoDigit.put(40, "Forty");
        twoDigit.put(50, "Fifty");
        twoDigit.put(60, "Sixty");
        twoDigit.put(70, "Seventy");
        twoDigit.put(80, "Eighty");
        twoDigit.put(90, "Ninety");

        for (int i = 20; i < 100; i+=10) {
            for (int j = 1; j < 10; j++) {
                twoDigit.put(i+j, twoDigit.get(i) + " " + singleDigit.get(j));
            }
        }

    }

    public String numberToWords(int num) {
        String s = String.valueOf(num);
        return solve(s, 0, new StringBuilder());
    }

    private String solve(String s, int cnt, StringBuilder sb) {
        int len = s.length();

        if (s == null || len == 0) {
            return sb.toString().trim();
        }

        StringBuilder sub = new StringBuilder();
        int cur = len;

        if (len == 1) {
            int num = Character.getNumericValue(s.charAt(0));
            String prefix = singleDigit.get(num);
            sub.insert(0, prefix);
            cur -= 1;
        } else {
            if (len >= 2) {
                String prefix = twoDigit.get(Integer.valueOf(s.substring(len-2, len)));

                if (prefix != null && prefix.isEmpty() == false) {
                    sub.insert(0, prefix + " ");
                }

                cur -= 2;
            }

            if (len >= 3) {
                int num = Character.getNumericValue(s.charAt(len-3));

                if (num != 0) {
                    String prefix = singleDigit.get(num);
                    sub.insert(0, prefix + " "  + "Hundred ");
                }

                cur -= 1;
            }
        }

        if (cnt == 3 && sub.length() > 0) {
            addBlank(sub);
            sub.append("Thousand");
        } else if (cnt == 6 && sub.length() > 0) {
            addBlank(sub);
            sub.append("Million");
        } else if (cnt == 9 && sub.length() > 0) {
            addBlank(sub);
            sub.append("Billion");
        }

        if (sb.length() > 0) {
            addBlank(sub);
            sub.append(sb);
        }

        cnt += (len - cur);

        return solve(s.substring(0, cur), cnt, sub);
    }

    private void addBlank(StringBuilder sub) {
        if (sub.length() == 0) {
            return;
        }

        if (sub.charAt(sub.length() - 1) == ' ') {
            return;
        }


        sub.append(" ");
    }

    public static void main(String[] args) {
        IntegerToEnglishWord integerToEnglishWord = new IntegerToEnglishWord();
        System.out.println(integerToEnglishWord.numberToWords(1000010));
    }
}
