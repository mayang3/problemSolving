
package leetcode;

public class AddBinary {

    public String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        StringBuilder sb = new StringBuilder();

        while (i >= 0 || j >= 0) {
            int sum = carry;

            if (i >= 0) {
                sum += Character.getNumericValue(a.charAt(i--));
            }

            if (j >= 0) {
                sum += Character.getNumericValue(b.charAt(j--));
            }

            carry = sum / 2;
            sum %= 2;
            sb.insert(0, sum);
        }

        if (carry > 0) {
            sb.insert(0, carry);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        AddBinary addBinary = new AddBinary();
        System.out.println(addBinary.addBinary("11", "1"));
    }
}
