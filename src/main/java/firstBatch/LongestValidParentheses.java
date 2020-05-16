package firstBatch;

import org.junit.jupiter.api.Test;

/*
 * 5/7/20
 * leetcode # 32. Longest Valid Parentheses
 * link: https://leetcode.com/problems/longest-valid-parentheses/
 * tags:
 * level: hard
 */
public class LongestValidParentheses {
    /*    Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

    Example 1:

    Input: "(()"
    Output: 2
    Explanation: The longest valid parentheses substring is "()"
    Example 2:

    Input: ")()())"
    Output: 4
    Explanation: The longest valid parentheses substring is "()()"*/

    // ((()))
    // 000246

    // 0123456
    // (()(())
    // 0020026

    // ()()()
    // 020

    // ())
    // 00

    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] dp = new int[s.length()];
        int longest = 0;
        for (int i = 1; i < dp.length; i++) {
            if (s.charAt(i) == ')') {
                // case 1: prev = (
                // case 2: prev = )
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i - 2 >= 0) ? dp[i-2] + 2 : 2;
                } else if (dp[i-1] > 0
                        && i - dp[i-1] - 1 >= 0
                        && s.charAt(i - dp[i-1] - 1) == '(') {
                    dp[i] = (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0)
                    + dp[i-1] + 2;
                }
            }
            longest = Math.max(longest, dp[i]);
        }
        return longest;
    }

    @Test
    public void test() {
        System.out.println(this.longestValidParentheses("((()))"));
    }
}
