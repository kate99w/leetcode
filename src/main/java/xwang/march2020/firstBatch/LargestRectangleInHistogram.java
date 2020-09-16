package xwang.march2020.firstBatch;

import org.junit.jupiter.api.Test;

import java.util.Stack;

/*
 * 5/8/20
 * leetcode # 84. Largest Rectangle in Histogram
 * link: https://leetcode.com/problems/largest-rectangle-in-histogram/
 * tags:
 * level: hard
 */
public class LargestRectangleInHistogram {

    /* Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

    Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

    The largest rectangle is shown in the shaded area, which has area = 10 unit.
            Example:

    Input: [2,1,5,6,2,3]
    stack: 0, i
    Output: 10
    */

    public int largestRectangleArea(int[] heights) {
        Stack< Integer > stack = new Stack < > ();
        stack.push(-1);
        int maxarea = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
            stack.push(i);
        }
        while (stack.peek() != -1)
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() -1));
        return maxarea;
    }

    @Test
    public void test() {
        // System.out.println(this.largestRectangleArea(new int[] {2,1,5,6,2,3}));
        // System.out.println(this.largestRectangleArea(new int[] {1,5,6}));

        // 2, 1, 2
        // stack: 1,
        System.out.println(this.largestRectangleArea(new int[] {2,1,2}));
    }
}
