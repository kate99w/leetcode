package xwang.march2020.firstBatch;

import org.junit.jupiter.api.Test;

import static utils.PrintUtils.*;

/*
 * 5/8/20
 * leetcode # 34. Find First and Last Position of Element in Sorted Array
 * link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 * tags:
 * level: medium
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
/*    Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

    Your algorithm's runtime complexity must be in the order of O(log n).

    If the target is not found in the array, return [-1, -1].

    Example 1:

    Input: nums = [5,7,7,8,8,10], target = 8
    Output: [3,4]
    Example 2:

    Input: nums = [5,7,7,8,8,10], target = 6
    Output: [-1,-1]*/
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[] {-1, -1};
        int[] res = new int[2];
        int left = 0, right = nums.length - 1;
        // find first
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        if (nums[left] != target) return new int[] {-1, -1};
        res[0] = left;

        // find last
        left = 0;
        right = nums.length - 1;
        while (left < right - 1) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        if (nums[right] == target) res[1] = right;
        else res[1] = left;

        return res;
    }

    @Test
    public void test() {
        printArray(this.searchRange(new int[]{5,7,7,8,8,10}, 8));
    }
}
