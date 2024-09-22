package leetcode;

import java.math.BigInteger;

/**
 * @author neo82
 */
public class _3260 {
    public static void main(String[] args) {
        _3260 obj = new _3260();

        System.out.println(obj.largestPalindrome(100000, 6));
    }

    public String largestPalindrome(int n, int k) {
        if (n == 1) {
            for (int i = 9; i > 0 ; i--) {
                if (i % k == 0) {
                    return String.valueOf(i);
                }
            }
        }

        boolean even = n % 2 == 0;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n / 2; i++) {
            sb.append("9");
        }

        if (!even) {
            sb.append("9");
        }


        for (BigInteger i = new BigInteger(sb.toString()); i.compareTo(BigInteger.ZERO) > 0; i = i.subtract(BigInteger.ONE)) {
            BigInteger original = restore(i, even);

            if (original.mod(BigInteger.valueOf(k)).equals(BigInteger.ZERO)) {
                return original.toString();
            }
        }

        return "";
    }

    private BigInteger restore(BigInteger i, boolean even) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append(i.toString());
        StringBuilder sb2 = new StringBuilder();

        sb2.append(even ? sb1.toString() : sb1.substring(0, sb1.length() - 1));

        return new BigInteger(sb1.append(sb2.reverse()).toString());
    }


}
