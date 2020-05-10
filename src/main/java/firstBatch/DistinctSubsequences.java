package firstBatch;

import org.junit.jupiter.api.Test;

/*
 * 5/8/20
 * leetcode # 115. Distinct Subsequences
 * link: https://leetcode.com/problems/distinct-subsequences/
 * tags:
 * level: hard
 */
public class DistinctSubsequences {
/*    Given a string S and a string T, count the number of distinct subsequences of S which equals T.
    A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
    It's guaranteed the answer fits on a 32-bit signed integer.

    Example 1:

    Input: S = "rabbbit", T = "rabbit"
    Output: 3
    Explanation:
    As shown below, there are 3 ways you can generate "rabbit" from S.
            (The caret symbol ^ means the chosen letters)

    rabbbit
    ^^^^ ^^
    rabbbit
    ^^ ^^^^
    rabbbit
    ^^^ ^^^
    Example 2:

    Input: S = "babgbag", T = "bag"
    Output: 5
    Explanation:
    As shown below, there are 5 ways you can generate "bag" from S.
            (The caret symbol ^ means the chosen letters)

    babgbag
    ^^ ^
    babgbag
    ^^    ^
    babgbag
    ^    ^^
    babgbag
     ^  ^^
    babgbag
      ^^^*/

    public int numDistinct(String s, String t) {
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        for (int i = 0; i <= t.length(); i++) {
            for (int j = 0; j <= s.length(); j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else if (i == 0) {
                    dp[i][j] = 1;
                } else if ( j == 0){
                    dp[i][j] = 0;
                } else {
                    if (t.charAt(i - 1) == s.charAt(j - 1)) {
                        dp[i][j] = dp[i][j-1] + dp[i-1][j-1];
                    } else {
                        dp[i][j] = dp[i][j-1];
                    }
                }
            }
        }
        return dp[t.length()][s.length()];
    }

    @Test
    public void test() {
        System.out.println(this.numDistinct("rabbbit", "rabbit"));
    }
}
