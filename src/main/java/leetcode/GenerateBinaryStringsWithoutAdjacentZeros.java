package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neo82
 */
public class GenerateBinaryStringsWithoutAdjacentZeros {
    public static void main(String[] args) {
        GenerateBinaryStringsWithoutAdjacentZeros generateBinaryStringsWithoutAdjacentZeros = new GenerateBinaryStringsWithoutAdjacentZeros();
        System.out.println(generateBinaryStringsWithoutAdjacentZeros.validStrings(18));
    }

    public List<String> validStrings(int n) {
        List<String> ans = new ArrayList<>();

        solve(ans, new StringBuilder(), n, 0);
        return ans;
    }

    private void solve(List<String> ans, StringBuilder sb, int n, int i) {
        if (i >= n) {
            ans.add(sb.toString());
            return;
        }

        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '0') {
            sb.append("1");
            solve(ans, sb, n, i + 1);
            sb.deleteCharAt(sb.length() - 1);
        } else {
            sb.append("0");
            solve(ans, sb, n, i + 1);
            sb.deleteCharAt(sb.length() - 1);

            sb.append("1");
            solve(ans, sb, n, i + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
