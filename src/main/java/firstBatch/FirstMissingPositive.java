package firstBatch;

import org.junit.jupiter.api.Test;

import static utils.PrintUtils.printArray;

/*
 * 5/8/20
 * leetcode # 41. First Missing Positive
 * link: https://leetcode.com/problems/first-missing-positive/
 * tags:
 * level: hard
 */
public class FirstMissingPositive {
/*    Given an unsorted integer array, find the smallest missing positive integer.

    Example 1:

    Input: [1,2,0]
    Output: 3
    Example 2:

    Input: [3,4,-1,1]
    Output: 2
    Example 3:

    Input: [7,8,9,11,12]
    Output: 1
    Note:

    Your algorithm should run in O(n) time and uses constant extra space.*/

    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        // find 1
        boolean hasOne = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 1) {
                nums[i] = 1;
            } else if (nums[i] == 1) {
                hasOne = true;
            }
        }
        if (!hasOne) return 1;

        // scan the whole array and mark relative position negative
        for (int n : nums) {
            if (Math.abs(n) > nums.length) continue;
            if (nums[Math.abs(n) - 1] > 0) {
                nums[Math.abs(n)  - 1] = -nums[Math.abs(n)  - 1];
            }
        }
        // check the first positive index
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) return i + 1;
        }
        return nums.length + 1;
    }

    @Test
    public void test() {
        System.out.println(this.firstMissingPositive(new int[]{3,1}));
    }
}
