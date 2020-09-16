package xwang.march2020.firstBatch;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/*
 * 5/7/20
 * leetcode # 3. Longest Substring Without Repeating Characters
 * link: https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * tags:
 * level: medium
 */
public class LongestSubstringWithoutRepeatingCharacters {
    /*    Given a string, find the length of the longest substring without repeating characters.

    Example 1:

    Input: "abcabcbb"
    Output: 3
    Explanation: The answer is "abc", with the length of 3.
    Example 2:

    Input: "bbbbb"
    Output: 1
    Explanation: The answer is "b", with the length of 1.
    Example 3:

    Input: "pwwkew"
    Output: 3
    Explanation: The answer is "wke", with the length of 3.
    Note that the answer must be a substring, "pwke" is a subsequence and not a substring.*/

    // p w w k e w
    //     l
    //     r
    // map: p 0

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        Set<Character> set = new HashSet<>();
        int l = 0, r = 0, longest = 0;
        while (r < s.length()) {
            if (set.contains(s.charAt(r))) {
                set.remove(s.charAt(l));
                l++;
            } else {
                set.add(s.charAt(r));
                longest = Math.max(longest, r - l + 1);
                r++;
            }
        }
        return longest;
    }

    @Test
    public void test() {
        // a b b a
        // l
        //   r
        // map: a, 0,  b, 1,
        System.out.println(this.lengthOfLongestSubstring("abba"));
    }

}
