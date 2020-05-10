package thirdBatch;

import org.junit.jupiter.api.Test;

import static utils.PrintUtils.*;

/*
 * 5/8/20
 * leetcode # 329. Longest Increasing Path in a Matrix
 * link: https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
 * tags:
 * level: hard
 */
public class LongestIncreasingPathInAMatrix {

/*    Given an integer matrix, find the length of the longest increasing path.

    From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

    Example 1:

    Input: nums =
            [
            [9,9,4],
            [6,6,8],
            [2,1,1]
            ]
    Output: 4
    Explanation: The longest increasing path is [1, 2, 6, 9].
    Example 2:

    Input: nums =
            [
            [3,4,5],
            [3,2,6],
            [2,2,1]
            ]
    Output: 4
    Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.*/

    private final int[][] DIR = new int[][] {{1,0},{0,1},{-1, 0},{0, -1}};

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        int longest = 1;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                longest = Math.max(longest, dfs(matrix, i, j, dp, Integer.MIN_VALUE));
            }
        }

        printMatrix(dp);
        return longest;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] dp, int parentValue) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || matrix[i][j] <= parentValue) return 0;
        if (dp[i][j] > 0) {
            return dp[i][j];
        }
        int max = 0;
        for (int[] dir : DIR) {
            max = Math.max(max, dfs(matrix, i + dir[0], j + dir[1], dp, matrix[i][j]));
        }
        dp[i][j] = max + 1;
        return max + 1;
    }

    @Test
    public void test() {
        // System.out.println((this.longestIncreasingPath(new int[][] {{9,9,4},{6,6,8},{2,1,1}})));
        System.out.println((this.longestIncreasingPath(new int[][] {{1,2}})));
    }


}
