package xwang.march2020.firstBatch;

import org.junit.jupiter.api.Test;

import java.util.*;

/*
 * 5/8/20
 * leetcode # 49. Group Anagrams
 * link: https://leetcode.com/problems/group-anagrams/
 * tags:
 * level: medium
 */
public class GroupAnagrams {
/*    Given an array of strings, group anagrams together.

            Example:

    Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
    Output:
            [
            ["ate","eat","tea"],
            ["nat","tan"],
            ["bat"]
            ]
    Note:

    All inputs will be in lowercase.
    The order of your output does not matter.*/

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] charArray = new char[26];
            for (int i = 0; i < s.length(); i++) {
                charArray[s.charAt(i) - 'a']++;
            }
            List<String> anagrams = map.getOrDefault(Arrays.toString(charArray), new ArrayList<>());
            anagrams.add(s);
            map.put(Arrays.toString(charArray), anagrams);
        }
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(this.groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
