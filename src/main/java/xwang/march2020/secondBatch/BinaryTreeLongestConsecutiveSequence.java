package xwang.march2020.secondBatch;

import utils.TreeNode;

/*
 * 5/8/20
 * leetcode # 298. Binary Tree Longest Consecutive Sequence
 * link: https://leetcode.com/problems/binary-tree-longest-consecutive-sequence/
 * tags:
 * level: medium
 */
public class BinaryTreeLongestConsecutiveSequence {

/*    Given a binary tree, find the length of the longest consecutive sequence path.

    The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

    Example 1:

    Input:

           1
            \
             3
            / \
            2   4
                 \
                  5

    Output: 3

    Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
    Example 2:

    Input:

            2
            \
            3
            /
            2
            /
            1

    Output: 2

    Explanation: Longest consecutive sequence path is 2-3, not 3-2-1, so return 2.

 */
    int longest = 0;

    public int longestConsecutive(TreeNode root) {
        helper(root);
        return longest;
    }

    private int helper(TreeNode root) {
        if (root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);

        int curMax = Math.max((root.left != null && root.left.val == root.val + 1) ? left : 0,
        (root.right != null && root.right.val == root.val + 1) ? right : 0) + 1;
        longest = Math.max(longest, curMax);

        return curMax;
    }


}
