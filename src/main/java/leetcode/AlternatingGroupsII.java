package leetcode;

/**
 * @author neo82
 */
public class AlternatingGroupsII {

    public static void main(String[] args) {
        int[] colors = {0, 1, 0, 1, 0};
        int k = 3;

        AlternatingGroupsII alternatingGroupsII = new AlternatingGroupsII();
        System.out.println(alternatingGroupsII.numberOfAlternatingGroups(colors, k));
    }

    public int numberOfAlternatingGroups(int[] colors, int k) {
        int N = colors.length;
        int[] extendedColors = new int[N * 2];

        for (int i = 0; i < 2 * N; i++) {
            extendedColors[i] = colors[i % N];
        }

        int ans = 0;
        int len = 1;

        // len 이 k 보다 크다는 것은 k window 만큼의 해가 여러개 있다는 뜻이 된다.
        // 예를 들어, k=3 일때, [1,0,1,0] 이면 len = 4 가 된다.
        // 이때, [1,0,1] 과 [0,1,0] 모두 해가 된다. 그러므로 ans = 2 가 정답이다.
        for (int i = 1; i < 2 * N; i++) {
            if (extendedColors[i - 1] != extendedColors[i]) {
                len++;
            } else {
                len = 1;
            }

            if (len == k) {
                ans++;
            }
        }

        return ans;
    }
}