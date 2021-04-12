package leetcode;

public class BreakAPalindrome {
    public static void main(String[] args) {
        BreakAPalindrome breakAPalindrome = new BreakAPalindrome();
        System.out.println(breakAPalindrome.breakPalindrome("aa"));
    }

    public String breakPalindrome(String palindrome) {
        if (palindrome == null || palindrome.length() <= 1) {
            return "";
        }

        char [] arr = palindrome.toCharArray();
        int n = palindrome.length();

        for (int i = 0; i < n; i++) {
            char ch = palindrome.charAt(i);

            if (ch == 'a' || isMiddle(n, i)) {
                continue;
            }

            arr[i] = 'a';

            return String.valueOf(arr);
        }

        arr[n-1] = (char)(arr[n-1] + 1);

        return String.valueOf(arr);
    }

    boolean isMiddle(int n, int i) {
        if (n % 2 == 0) {
            return false;
        }

        return i == n / 2;
    }
}
