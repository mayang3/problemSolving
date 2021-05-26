package leetcode;

public class FirstBadVersion {
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;

        while (left < right) {
            int m = (int)(((long)left + (long)right) / 2);

            boolean badVersion = isBadVersion(m);

            if (badVersion) {
                right = m;
            } else {
                left = m+1;
            }
        }

        return left;
    }

    private boolean isBadVersion(int m) {
        return m == 1702766719 ? true : false;
    }

    public static void main(String[] args) {
        FirstBadVersion firstBadVersion = new FirstBadVersion();
        System.out.println(firstBadVersion.firstBadVersion(2126753390));
    }
}
