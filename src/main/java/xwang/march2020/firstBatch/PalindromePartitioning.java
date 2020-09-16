package xwang.march2020.firstBatch;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/*
 * 5/8/20
 * leetcode # 131. Palindrome Partitioning
 *            132. Palindrome Partitioning II
 * link: https://leetcode.com/problems/palindrome-partitioning/
 *       https://leetcode.com/problems/palindrome-partitioning-ii/
 * tags:
 * level: medium
 */
public class PalindromePartitioning {
/*    Given a string s, partition s such that every substring of the partition is a palindrome.

    Return all possible palindrome partitioning of s.

            Example:

    Input: "aab"
    Output:
            [
            ["aa","b"],
            ["a","a","b"]
            ]
            */
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        List<String> pal = new ArrayList<>();
        dfs(s, 0, pal, res);
        return res;
    }

    private void dfs(String s, int index, List<String> pal, List<List<String>> res) {
        if (index >= s.length()) {
            res.add(new ArrayList<>(pal));
            return;
        }
        /// a a b
        /// i
        /// j
        for (int j = index + 1; j <= s.length(); j++) {
            if (isPal(s, index, j)) {
                pal.add(s.substring(index, j));
                dfs(s, j, pal, res);
                pal.remove(pal.size() - 1);
            }
        }
    }

    private boolean isPal(String s, int i, int j) {
        j--;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    @Test
    public void test() {
        System.out.println(this.partition("aab"));
    }


/*    Given a string s, partition s such that every substring of the partition is a palindrome.

    Return the minimum cuts needed for a palindrome partitioning of s.

            Example:

    Input: "aab"
    Output: 1
    Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.

     a  a  b
 [-1 0  0  0]

    */


    public int minCut(String s) {
        if (s == null || s.length() <= 1) return 0;
        int[] dp = new int[s.length() + 1];
        dp[0] = -1;
        for (int i = 0; i < s.length(); i++) {
            int curMin = i;
            for (int j = 0; j <= i; j++) {
                if (isPal(s, j, i + 1)) {
                    curMin = Math.min(curMin, dp[j] + 1);
                }
            }
            dp[i+1] = curMin;
        }
        return dp[s.length()];
    }

    @Test
    public void test_II() {
        System.out.println(this.minCut("aabaa"));
    }
}
