package xwang.march2020.firstBatch;

import org.junit.jupiter.api.Test;

/*
 * 5/8/20
 * leetcode # 72. Edit Distance
 * link: https://leetcode.com/problems/edit-distance/
 * tags:
 * level: hard
 */
public class EditDistance {
/*    Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

    You have the following 3 operations permitted on a word:

    Insert a character
    Delete a character
    Replace a character
    Example 1:

    Input: word1 = "horse", word2 = "ros"
    Output: 3
    Explanation:
    horse -> rorse (replace 'h' with 'r')
    rorse -> rose (remove 'r')
    rose -> ros (remove 'e')
    Example 2:

    Input: word1 = "intention", word2 = "execution"
    Output: 5
    Explanation:
    intention -> inention (remove 't')
    inention -> enention (replace 'i' with 'e')
    enention -> exention (replace 'n' with 'x')
    exention -> exection (replace 'n' with 'c')
    exection -> execution (insert 'u')*/

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        for (int i = 0; i <= word1.length(); i++) {
            for (int j = 0; j <= word2.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 0;
                } else if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j] + 1), dp[i][j-1] + 1);
                    } else {
                        dp[i][j] = Math.min(Math.min(dp[i-1][j-1] , dp[i-1][j]), dp[i][j-1]) + 1;
                    }
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    @Test
    public void test() {
        System.out.println(this.minDistance("horse", "ros"));
    }
}
