package xwang.march2020.firstBatch;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/*
 * 5/8/20
 * leetcode # 76. Minimum Window Substring
 * link: https://leetcode.com/problems/minimum-window-substring/
 * tags:
 * level: hard
 */
public class MinimumWindowSubstring {
/*
    Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

    Example:

    Input: S = "ADOBECODEBANC", T = "ABC"
    Output: "BANC"

    Note:

    If there is no such window in S that covers all characters in T, return the empty string "".
    If there is such window, you are guaranteed that there will always be only one unique minimum window in S.

*/

    //   A D O B E C O D E B A N C
    //   l
    //               r
    // map: A 0, B 0, C 0
    // rest: 0
    public String minWindow(String s, String t) {
        if (t == null || t.length() == 0 || s == null || s.length() == 0) return t;
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }
        int rest = t.length();
        int[] minIndices = new int[]{0, 0, s.length() + 1};
        int left = 0, right = 0;
        while (right < s.length() && map.get(s.charAt(right)) == null) {
            left++;
            right++;
        } // pre processing ,start the index from the first letter in t;
        while (right <= s.length()) {
            if (rest == 0) { // pre-check, right not included
                if (right - left < minIndices[2]) {
                    minIndices[0] = left;
                    minIndices[1] = right;
                    minIndices[2] = right - left;
                }
                // then move left to reduce the length
                int curRest = map.get(s.charAt(left));
                map.put(s.charAt(left), curRest + 1);
                if (curRest >= 0) rest++;
                left++;
                while (left < s.length() && map.get(s.charAt(left)) == null) left++;
            } else { // not include all chars
                while (right < s.length() && map.get(s.charAt(right)) == null) right++;
                if (right < s.length()) {
                    int curRest = map.get(s.charAt(right));
                    map.put(s.charAt(right), curRest - 1);
                    if (curRest > 0) rest--;
                }
                right++;
            }
        }
        return s.substring(minIndices[0], minIndices[1]);
    }

    @Test
    public void test() {
        System.out.println(this.minWindow("aa", "aa"));
    }
}
