package leetcode;

public class EditDistance {
    static int INF = 987654321;

    public int minDistance(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();

        int[][] dp = new int[n2+1][n1+1];



        for (int i = 0; i <= n1; i++) {
            dp[0][i] = i;
        }

        for (int i = 0; i <= n2; i++) {
            dp[i][0] = i;
        }

        for (int word2Index = 1; word2Index <= n2; word2Index++) {
            for (int word1Index = 1; word1Index <= n1; word1Index++) {
                if (word1.charAt(word1Index-1) == word2.charAt(word2Index-1)) {
                    dp[word2Index][word1Index] = dp[word2Index - 1][word1Index - 1];
                } else {
                    dp[word2Index][word1Index] = 1 +
                            Math.min(dp[word2Index - 1][word1Index - 1],
                                    Math.min(dp[word2Index][word1Index - 1], dp[word2Index - 1][word1Index]));
                }
            }
        }

        return dp[n2][n1];
    }

//    private int solve(Integer[][] dp, String word1, String word2, int word1Index, int word2Index) {
//        if (word1Index >= word1.length() && word2Index >= word2.length()) {
//            return 0;
//        } else if (word1Index >= word1.length()) {
//            return word2.length() - word2Index;
//        } else if (word2Index >= word2.length()) {
//            return word1.length() - word1Index;
//        }
//
//        char ch1 = word1.charAt(word1Index);
//        char ch2 = word2.charAt(word2Index);
//
//        int min = INF;
//
//        if (dp[word1Index][word2Index] == null) {
//            if (ch1 == ch2) {
//                min = Math.min(min, solve(dp, word1, word2, word1Index + 1, word2Index + 1));
//            } else {
//                // insert
//                min = Math.min(min, 1 + solve(dp, word1, word2, word1Index, word2Index + 1));
//                // delete
//                min = Math.min(min, 1 + solve(dp, word1, word2, word1Index + 1, word2Index));
//                // update
//                min = Math.min(min, 1 + solve(dp, word1, word2, word1Index + 1, word2Index + 1));
//            }
//
//            dp[word1Index][word2Index] = min;
//        }
//
//        return dp[word1Index][word2Index];
//    }


    public static void main(String[] args) {
        EditDistance ed = new EditDistance();
        System.out.println(ed.minDistance("pneumonoultramicroscopicsilicovolcanoconiosis", "ultramicroscopically"));
    }
}
