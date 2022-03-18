package leetcode;

public class Solution {
    public String solution(String riddle) {
        char [] arr = riddle.toCharArray();

        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == '?') {
                for (int j = 0; j < 26; j++) {
                    char ch = (char)(j + 'a');

                    // before check
                    if (i > 0 && arr[i-1] == ch) {
                        continue;
                    }

                    // after check
                    if (i < arr.length - 1 && arr[i+1] == ch) {
                        continue;
                    }

                    arr[i] = ch;
                    break;
                }
            }
        }

        return String.valueOf(arr);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("??????"));
    }
}
