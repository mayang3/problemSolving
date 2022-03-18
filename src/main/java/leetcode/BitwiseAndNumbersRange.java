package leetcode;

public class BitwiseAndNumbersRange {
    public int rangeBitwiseAnd(int left, int right) {
        if (left == 0) {
            return 0;
        }

        int moveFactor = 1;

        while (left != right) {
            left >>= 1; // left 와 right 가 같지 않으면 같지 않은 수만큼 오른쪽으로 밀어낸다.
            right >>= 1;
            moveFactor <<= 1; // left 와 right 를 오른쪽으로 밀어낸 만큼 비트에 0을 채워넣는다.
        }

        return left * moveFactor;
    }

    public static void main(String[] args) {
        BitwiseAndNumbersRange bitwiseAndNumbersRange = new BitwiseAndNumbersRange();
        System.out.println(bitwiseAndNumbersRange.rangeBitwiseAnd(2147483647,
                2147483647));

    }
}
