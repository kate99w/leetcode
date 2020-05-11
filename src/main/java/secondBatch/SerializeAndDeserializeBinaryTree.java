package secondBatch;

import org.junit.jupiter.api.Test;
import utils.TreeNode;
import utils.TreeUtils;

/*
 * 5/8/20
 * leetcode # 297. Serialize and Deserialize Binary Tree
 * link: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * tags:
 * level: hard
 */
public class SerializeAndDeserializeBinaryTree {

/*    Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

    Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

            Example:

    You may serialize the following tree:

            1
            / \
            2   3
            / \
            4   5

    as "[1,2,3,null,null,4,5]"
    Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

            Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.*/

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    private final String NULL_VAL = "NULL";
    private final String SEPARATOR = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        if (sb.charAt(sb.length() - 1) == SEPARATOR.charAt(0)) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return new String(sb);
    }

    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append(NULL_VAL);
            sb.append(SEPARATOR);
            return;
        }
        sb.append(root.val);
        sb.append(SEPARATOR);
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0 || data.equals(NULL_VAL)) return null;
        String[] nodes = data.split(SEPARATOR);
        TreeNode dummy = new TreeNode(0);
        deserializeHelper(nodes, dummy, true, 0);
        return dummy.left;
    }

    private int deserializeHelper(String[] nodes, TreeNode parent, boolean isLeft, int curIndex) {
        if (curIndex >= nodes.length) return 0;
        if (nodes[curIndex].equals(NULL_VAL)) return 1;
        TreeNode cur = new TreeNode(Integer.parseInt(nodes[curIndex]));
        if (isLeft) {
            parent.left = cur;
        } else {
            parent.right = cur;
        }
        // recursion with left
        int leftCount = deserializeHelper(nodes, cur, true, curIndex + 1);
        // recursion with right
        int rightCount = deserializeHelper(nodes, cur, false, curIndex + leftCount + 1);
        return leftCount + rightCount + 1;
    }

    @Test
    public void test() {
        TreeNode root = new TreeNode(0);
        root.left = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);
        System.out.println("Serializing ...");
        System.out.println(TreeUtils.serialize(root));

        System.out.println("After deserialize ...");
        System.out.println(TreeUtils.serialize(TreeUtils.deserialize(TreeUtils.serialize(root))));
    }

}
