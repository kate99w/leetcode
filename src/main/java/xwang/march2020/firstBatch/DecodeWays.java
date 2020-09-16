package xwang.march2020.firstBatch;

import org.junit.jupiter.api.Test;

/*
 * 5/8/20
 * leetcode # 91. Decode Ways
 * link: https://leetcode.com/problems/decode-ways/
 * tags:
 * level: medium
 */
public class DecodeWays {
/*    A message containing letters from A-Z is being encoded to numbers using the following mapping:

            'A' -> 1
            'B' -> 2
            ...
            'Z' -> 26
    Given a non-empty string containing only digits, determine the total number of ways to decode it.

            Example 1:

    Input: "12"
    Output: 2
    Explanation: It could be decoded as "AB" (1 2) or "L" (12).
    Example 2:

    Input: "226"
    Output: 3
    Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).*/

    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        return dfs(s, 0);
    }
    private int dfs(String s, int start) {
        if (start >= s.length()) {
            return 1;
        }
        int n = 0;
        if (s.charAt(start) != '0') {
            n += dfs(s, start + 1);
        }
        if (validLetter(s, start)) {
            n += dfs(s, start + 2);
        }
        return n;
    }
    private boolean validLetter(String s, int start) {
        if (((s.charAt(start) - '0') == 1 && start + 1 < s.length()) ||
           ((s.charAt(start) - '0') == 2 && start + 1 < s.length() && s.charAt(start + 1)  - '0' < 7 )) {
            return true;
        }
        return false;
    }

    @Test
    public void test() {
        System.out.println(this.numDecodings("226"));
    }
}
