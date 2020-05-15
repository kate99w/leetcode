package firstBatch;

import org.junit.jupiter.api.Test;

/*
 * 5/7/20
 * leetcode # 5. Longest Palindromic Substring
 * link: https://leetcode.com/problems/longest-palindromic-substring/
 * tags:
 * level: medium
 */
public class LongestPalindromicSubstring {
/*    Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

    Example 1:

    Input: "babad"
    Output: "bab"
    Note: "aba" is also a valid answer.
            Example 2:

    Input: "cbbd"
    Output: "bb"*/

    //   '' b a b a d
    //'' 0  0 0 0 0 0
    //d  0  0 0 0 0 1
    //a  0  0 1 0 1 0
    //b  0  1 0 2 0 0
    //a  0  0 2 0 3 0
    //b  0  0 0 3 0 0

    // s: [0, 2]
    // r: [

    //   '' b b b
    //'' 0  0 0 0
    //b  0  1 1 1
    //b  0  0 1 0
    //b  0  0 0 0

    // corner case: abceba
    //   a b c e b a
    // a 1 start s(0) reversed(5)
    // b   2 end s(1) reversed(4)
    // e
    // d
    // b
    // a


    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return s;
        int[][] dp = new int[s.length() + 1][s.length() + 1];

        // row: reversed s: index at s.length() - 1 - (row - 1) = s.length() - row
        // col: s : index at col - 1
        int longest = 0;
        int[] indices = new int[]{0, 0};

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (s.charAt(s.length() - i) == s.charAt(j - 1)) {
                    int prev = dp[i - 1][j-1];
                    dp[i][j] = prev + 1;
                    int startRow = i - prev;
                    int startCol = j - prev;
                    // check if reverseStart == sEnd && reverseEnd == sStart
                    int sStart = startCol - 1, sEnd = j - 1;
                    int rStart = s.length() - startRow, rEnd = s.length() - i;
                    if (sStart == rEnd && sEnd == rStart) {
                        if (longest < dp[i][j]) {
                            longest = dp[i][j];
                            indices[0] = sStart;
                            indices[1] = sEnd;
                        }
                    }
                }
            }
        }
        return s.substring(indices[0], indices[1] + 1);
    }

    @Test
    public void test() {
        System.out.println(this.longestPalindrome("abb"));
    }
}
