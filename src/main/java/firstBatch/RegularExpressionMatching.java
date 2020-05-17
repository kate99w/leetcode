package firstBatch;

import org.junit.jupiter.api.Test;

/*
 * 5/7/20
 * leetcode # 10. Regular Expression Matching
 * link: https://leetcode.com/problems/regular-expression-matching/
 * tags:
 * level: hard
 */
public class RegularExpressionMatching {
/*    Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

            '.' Matches any single character.
            '*' Matches zero or more of the preceding element.
    The matching should cover the entire input string (not partial).

    Note:

    s could be empty and contains only lowercase letters a-z.
    p could be empty and contains only lowercase letters a-z, and characters like . or *.
    Example 1:

    Input:
    s = "aa"
    p = "a"
    Output: false
    Explanation: "a" does not match the entire string "aa".
    Example 2:

    Input:
    s = "aa"
    p = "a*"
    Output: true
    Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
    Example 3:

    Input:
    s = "ab"
    p = ".*"
    Output: true
    Explanation: ".*" means "zero or more (*) of any character (.)".
    Example 4:

    Input:
    s = "aab"
    p = "c*a*b"
    Output: true
    Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".
    Example 5:

    Input:
    s = "mississippi"
    p = "mis*is*p*."
    Output: false*/

    //   '' c * a * b
    //''  T F T F T F
    //a   F T F T F F
    //a   F F F F
    //b   F F F F F F

    public boolean isMatch(String s, String p) {
        if (s == null && p == null) return true;
        if (s == null || p == null) return false;
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = dp[0][i-1] || dp[0][i-2];
            }
        }
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {
                    if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else if (p.charAt(j - 1) == '*') {
                        dp[i][j] = dp[i][j - 1] || dp[i][j - 2]
                                || (dp[i-1][j] && (p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.'));
                    }
            }
        }
        return dp[s.length()][p.length()];
    }

    //    '' . *
    // '' t  f
    //  a f  t t
    //  a f  f
    //  a f
    //  a f
    //  b f
    //  b f
    //  b f
    //  c f

/*    "mississippi"

    mississ
    mis*is*


               ' m i s * i s * p * .
           '   t f f f f f f f f f
           m   f t f f f f f f f f f
           i   f f t f t f f f f f f
           s   f f f t t f f f f f f
           s   f f f f t f f f f f f
           i   f f f f f t f t f t f
           s   f f f f f f t t f t t
           s   f f f f f f f
           i
           p
           p
           i





            */

    @Test
    public void test() {
        System.out.println(this.isMatch("mississippi", "mis*is*p*."));
    }
}
