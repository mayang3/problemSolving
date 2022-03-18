package leetcode;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length()-1;
        int n = s.length();

        while (left <= right) {
            while (left < n && Character.isAlphabetic(s.charAt(left)) == false) {
                left++;
            }

            while (right >= 0 && Character.isAlphabetic(s.charAt(right)) == false) {
                right--;
            }

            if (left >= right) {
                break;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome validPalindrome = new ValidPalindrome();
        System.out.println(validPalindrome.isPalindrome("a A d w er f "));
    }
}
