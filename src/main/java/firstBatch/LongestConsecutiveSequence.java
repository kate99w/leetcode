package firstBatch;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * 5/8/20
 * leetcode # 128. Longest Consecutive Sequence
 * link: https://leetcode.com/problems/longest-consecutive-sequence/
 * tags:
 * level: hard
 */
public class LongestConsecutiveSequence {
/*    Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

    Your algorithm should run in O(n) complexity.

    Example:

    Input: [100, 4, 200, 1, 3, 2]
    Output: 4
    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.*/

    // Input: [100, 4, 200, 1, 3, 2]
    // Set: 100, 4, 200, 1, 3, 2
    // streak: 100, 4, 200, 1 , 3, 4, 2, 3,4

    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int i : nums) set.add(i);

        int longest = 1;
        for (int i : nums) {
            int curLen = 1;
            if (!set.contains(i-1)) {
                while (set.contains(i + 1)) {
                    i++;
                    curLen++;
                }
            }
            longest = Math.max(longest, curLen);
        }

        return longest;
    }

    @Test
    public void test() {
        System.out.println(this.longestConsecutive(new int[] {100, 4, 200, 1, 3, 2}));
    }
}
