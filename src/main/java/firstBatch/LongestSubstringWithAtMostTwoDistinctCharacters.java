package firstBatch;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/*
 * 5/8/20
 * leetcode # 159. Longest Substring with At Most Two Distinct Characters
 * link: https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
 * tags:
 * level: medium
 */
public class LongestSubstringWithAtMostTwoDistinctCharacters {

    /*    Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

    Example 1:

    Input: "eceba"
    Output: 3
    Explanation: t is "ece" which its length is 3.
    Example 2:

    Input: "ccaabbb"
    Output: 5
    Explanation: t is "aabbb" which its length is 5.*/

    //  e c e b a
    //0 l
    //0 r
    // map:

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int l = 0, r = 0, longest = 0;
        while (r <= s.length()) {
            if (map.size() <= 2) {
                longest = Math.max(longest, r - l);
                if (r < s.length()) {
                    int count = map.getOrDefault(s.charAt(r), 0);
                    map.put(s.charAt(r), count + 1);
                }
                r++;
            } else {
                int count = map.get(s.charAt(l));
                if (count == 1) {
                    map.remove(s.charAt(l));
                } else {
                    map.put(s.charAt(l), count - 1);
                }
                l++;
            }
        }
        return longest;
    }

    @Test
    public void test() {
        System.out.println(this.lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }
}
