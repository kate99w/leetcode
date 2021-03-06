package xwang.march2020.firstBatch;

import org.junit.jupiter.api.Test;

/*
 * 5/8/20
 * leetcode # 162. Find Peak Element
 * link: https://leetcode.com/problems/find-peak-element/
 * tags:
 * level: medium
 */
public class FindPeakElement {

/*    A peak element is an element that is greater than its neighbors.

    Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

    The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

    You may imagine that nums[-1] = nums[n] = -∞.

    Example 1:

    Input: nums = [1,2,3,1]
    Output: 2
    Explanation: 3 is a peak element and your function should return the index number 2.
    Example 2:

    Input: nums = [1,2,1,3,5,6,4]
    Output: 1 or 5
    Explanation: Your function can return either index number 1 where the peak element is 2,
    or index number 5 where the peak element is 6.
    Note:

    Your solution should be in logarithmic complexity.*/

    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int l = mid - 1 >= 0 ? nums[mid - 1] : Integer.MIN_VALUE;
            int r = mid + 1 < nums.length ? nums[mid + 1] : Integer.MIN_VALUE;
            if (nums[mid] > l && nums[mid] > r) {
                return mid;
            } else if (r <= nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return 0; // [-2147483648]
    }

    @Test
    public void test() {
        System.out.println(this.findPeakElement(new int[] {1,2,3,1}));
    }
}
