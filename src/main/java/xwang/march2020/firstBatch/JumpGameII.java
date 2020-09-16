package xwang.march2020.firstBatch;

/*
 * 5/8/20
 * leetcode # 45. Jump Game II
 * link: https://leetcode.com/problems/jump-game-ii/
 * tags:
 * level: hard
 */
public class JumpGameII {
/*    Given an array of non-negative integers, you are initially positioned at the first index of the array.

    Each element in the array represents your maximum jump length at that position.

    Your goal is to reach the last index in the minimum number of jumps.

    Example:

    Input: [2,3,1,1,4]
    Output: 2
    Explanation: The minimum number of jumps to reach the last index is 2.
    Jump 1 step from index 0 to 1, then 3 steps to the last index.

            Note:

    You can assume that you can always reach the last index.*/
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        for (int i = nums.length - 2; i >= 0; i--) {
            int step = nums.length;
            for (int j = 1; j <= nums[i] && i + j < dp.length; j++) {
                if (dp[i + j] >= 0) {
                    step = Math.min(step, 1 + dp[i + j]);
                }
            }
            dp[i] = step == nums.length? -1 : step;
        }
        return dp[0];
    }

}
