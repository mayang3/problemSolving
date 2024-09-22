package leetcode;

/**
 * @author neo82
 */
public class AlternatingGroupsI {
    public int numberOfAlternatingGroups(int[] colors) {
        int N = colors.length;

        int[] extendedColors = new int[N * 2];

        for (int i = 0; i < N * 2; i++) {
            extendedColors[i] = colors[i % N];
        }

        int ans = 0;

        for (int i = 1; i <= N; i++) {
            if (extendedColors[i-1] != extendedColors[i] && extendedColors[i] != extendedColors[i+1]) {
                ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {

    }
}
