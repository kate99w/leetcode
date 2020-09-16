package xwang.march2020.secondBatch;

import org.junit.jupiter.api.Test;
import utils.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * 5/8/20
 * leetcode # 173. Binary Search Tree Iterator
 * link: https://leetcode.com/problems/binary-search-tree-iterator/
 * tags:
 * level: medium
 */
public class BinarySearchTreeIterator {

/*Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

    Calling next() will return the next smallest number in the BST.



            Example:

              7
             /  \
            3    15
                 / \
                 9  20



    BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false


    Note:

    next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
    You may assume that next() call will always be valid, that is, there will be at least a next smallest number in the BST when next() is called.
    */

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode() {}
     * TreeNode(int val) { this.val = val; }
     * TreeNode(int val, TreeNode left, TreeNode right) {
     * this.val = val;
     * this.left = left;
     * this.right = right;
     * }
     * }
     */

    Deque<TreeNode> stack;

    public BinarySearchTreeIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        pushLeft(root);
    }

    private void pushLeft(TreeNode root) {
        while (root != null) {
            stack.offerLast(root);
            root = root.left;
        }
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode top = stack.pollLast();
        if (top == null) return -1;
        pushLeft(top.right);
        return top.val;
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.isEmpty();
    }


/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

    @Test
    public void test() {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);
        BinarySearchTreeIterator iterator = new BinarySearchTreeIterator(root);
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
    }

}
