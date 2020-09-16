package xwang.march2020.firstBatch;

import org.junit.jupiter.api.Test;

/*
 * 5/8/20
 * leetcode # 42. Trapping Rain Water
 *            407. Trapping Rain Water II
 * link: https://leetcode.com/problems/trapping-rain-water/
 *       https://leetcode.com/problems/trapping-rain-water-ii/
 * tags:
 * level: hard
 */
public class TrappingRainWater {

/*    Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


    The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

    Example:

    Input: [0,1,0,2,1,0,1,3,2,1,2,1]
    Output: 6*/

    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int[] left = new int[height.length];
        left[0] = height[0];
        int[] right = new int[height.length];
        right[height.length - 1] = height[height.length - 1];
        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(left[i-1], height[i]);
        }
        for (int i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i+1], height[i]);
        }
        int total = 0;
        for (int i = 0; i < height.length; i++) {
            int cur = Math.min(left[i], right[i]) - height[i];
            total += Math.max(cur, 0);
        }
        return total;
    }

    @Test
    public void test() {
        System.out.println(this.trap(new int[] {0,1,0,2,1,0,1,3,2,1,2,1}));
    }


}
