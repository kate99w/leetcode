package firstBatch;

import org.junit.jupiter.api.Test;
import utils.TreeNode;
import utils.TreeUtils;

/*
 * 5/8/20
 * leetcode # 124. Binary Tree Maximum Path Sum
 * link: https://leetcode.com/problems/binary-tree-maximum-path-sum/
 * tags:
 * level: hard
 */
public class BinaryTreeMaximumPathSum {
/*    Given a non-empty binary tree, find the maximum path sum.

            For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

            Example 1:

    Input: [1,2,3]

            1
            / \
            2   3

    Output: 6
    Example 2:

    Input: [-10,9,20,null,null,15,7]

            -10
            / \
            9  20
            /  \
            15   7

    Output: 42*/

    private int max = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        Integer finalReturn = helper(root);
        max = Math.max(max, finalReturn);
        return max;
    }

    private Integer helper(TreeNode root) {
        if (root == null) return null;
        Integer left = helper(root.left);
        Integer right = helper(root.right);
        if (left == null && right == null) return root.val;

        int leftValue = left == null || left < 0 ? 0 : left;
        int rightValue = right == null || right < 0 ? 0 : right;

        int maxWithRoot = Math.max(leftValue, rightValue) + root.val;
        int maxAcrossRoot = leftValue + rightValue + root.val;
        if (left != null) {
            max = Math.max(max, Math.max(left, maxAcrossRoot));
        }
        if (right != null) {
            max = Math.max(max, Math.max(right, maxAcrossRoot));
        }
        return maxWithRoot;
    }

    @Test
    public void test() {
        TreeNode root = TreeUtils.deserialize("2,-1,null,null,-2,null,null");
        System.out.println(this.maxPathSum(root));
    }


}
