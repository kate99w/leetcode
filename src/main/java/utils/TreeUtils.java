package utils;

/*
 * 5/11/20
 * leetcode #
 * link:
 * tags:
 * level: medium
 */
public class TreeUtils {

    private static final String NULL_VAL = "null";
    private static final char SEPARATOR = ',';

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        if (sb.charAt(sb.length() - 1) == SEPARATOR) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return new String(sb);
    }
    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data == null || data.length() == 0 || data.equals(NULL_VAL)) return null;
        String[] nodes = data.split(String.valueOf(SEPARATOR));
        TreeNode dummy = new TreeNode(0);
        deserializeHelper(nodes, dummy, true, 0);
        return dummy.left;
    }

    // helpers
    private static void serializeHelper(TreeNode root, StringBuilder sb) {
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
    private static int deserializeHelper(String[] nodes, TreeNode parent, boolean isLeft, int curIndex) {
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
}
