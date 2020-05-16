package firstBatch;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/*
 * 5/8/20
 * leetcode # 85. Maximal Rectangle
 * link: https://leetcode.com/problems/maximal-rectangle/
 * tags:
 * level: hard
 */
public class MaximalRectangle {
    /*    Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

    Example:

    Input:
            [
            ["1","0","1","0","0"],
            ["1","0","1","1","1"],
            ["1","1","1","1","1"],
            ["1","0","0","1","0"]
            ]

    Output: 6*/

    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return 0;
        Cell[][] dp = new Cell[matrix.length][matrix[0].length];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                dp[i][j] = new Cell();
            }
        }
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != '0'){
                    if (i == 0 && j == 0) {
                        dp[i][j].left = 1;
                        dp[i][j].up = 1;
                    } else if (i == 0) {
                        dp[i][j].left = dp[i][j - 1].left + 1;
                        dp[i][j].up = 1;
                    } else if (j == 0) {
                        dp[i][j].left = 1;
                        dp[i][j].up = dp[i-1][j].up + 1;
                    } else {
                        dp[i][j].left = dp[i][j - 1].left + 1;
                        dp[i][j].up = dp[i-1][j].up + 1;
                        int leftArm = Math.min(dp[i][j].left , dp[i - 1][j - 1].left + 1);
                        int upArm = Math.min(dp[i][j].up, dp[i - 1][j - 1].up + 1);
                        max = Math.max(max, Math.max(leftArm * upArm, Math.max(dp[i][j].left, dp[i][j].up)));
                    }
                }
                max = Math.max(max, Math.max(dp[i][j].left, dp[i][j].up));
            }
        }
        return max;
    }

    @Test
    public void test() {
        System.out.println(this.maximalRectangle(new char[][] {{'1','0','1'},{'1', '0','1'}}));
    }

    class Cell {
        int up;
        int left;
    }

}
