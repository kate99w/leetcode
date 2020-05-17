package firstBatch;

import org.junit.jupiter.api.Test;

import java.util.List;

/*
 * 5/8/20
 * leetcode # 139. Word Break
 *            140. Word Break II
 * link: https://leetcode.com/problems/word-break/
 *       https://leetcode.com/problems/word-break-ii/
 * tags:
 * level: medium
 */
public class WordBreak {
/*    Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

            Note:

    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.

    Example 1:

    Input:
    s = "catsanddog"
    wordDict = ["cat", "cats", "and", "sand", "dog"]
    Output:
            [
            "cats and dog",
            "cat sand dog"
            ]
          0 1 2 3 4 5 6 7 8 9
            c a t s a n d d o g
                0 0     3     7
                        4

    Example 2:

    Input:
    s = "pineapplepenapple"
    wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
    Output:
            [
            "pine apple pen apple",
            "pineapple pen apple",
            "pine applepen apple"
            ]
    Explanation: Note that you are allowed to reuse a dictionary word.

    Example 3:

    Input:
    s = "catsandog"
    wordDict = ["cats", "dog", "sand", "and", "cat"]
    Output:
            []*/

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j <= i; j++) {
                if (dp[j - 1] && wordDict.contains(s.substring(j - 1, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[dp.length - 1];
    }

    @Test
    public void test() {
    }


}
