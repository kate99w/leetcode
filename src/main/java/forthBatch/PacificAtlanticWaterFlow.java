package forthBatch;

import org.junit.jupiter.api.Test;

import java.util.*;

import static utils.PrintUtils.printMatrix;

/*
 * 5/8/20
 * leetcode # 417. Pacific Atlantic Water Flow
 * link: https://leetcode.com/problems/pacific-atlantic-water-flow/
 * tags:
 * level: medium
 */
public class PacificAtlanticWaterFlow {

/*    Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

    Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

    Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

            Note:

    The order of returned grid coordinates does not matter.
    Both m and n are less than 150.


    Example:

    Given the following 5x5 matrix:

    Pacific ~   ~   ~   ~   ~
            ~  1   2   2   3  (5) *
            ~  3   2   3  (4) (4) *
            ~  2   4  (5)  3   1  *
            ~ (6) (7)  1   4   5  *
            ~ (5)  1   1   2   4  *
            *   *   *   *   * Atlantic

    Return:

            [[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).*/

    private final int[][] DIR = new int[][] {{1,0},{0,1},{-1,0},{0,-1}};

    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return res;
        // memory array
        int[][] mem = new int[matrix.length][matrix[0].length];
        mem[0][0] = 1;

        printMatrix(mem);
        System.out.println("__________");

        // pacific queue
        Deque<Cell> pacificQ = new ArrayDeque<>();
        for (int i = 1; i < matrix[0].length; i++) {
            pacificQ.offerLast(new Cell(0, i));
        }
        for (int i = 1; i < matrix.length; i++) {
            pacificQ.offerLast(new Cell(i, 0));
        }

        while (!pacificQ.isEmpty()) {
            Cell cur = pacificQ.pollFirst();
            mem[cur.i][cur.j] = 1;
            int value = matrix[cur.i][cur.j];
            for (int[] dir : DIR) {
                int row = cur.i + dir[0];
                int col = cur.j + dir[1];
                if (row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length
                && mem[row][col] == 0 && matrix[row][col] >= value) {
                    pacificQ.offerLast(new Cell(row, col));
                }
            }
        }
        printMatrix(mem);
        System.out.println("__________");

        // atlantic queue
        Deque<Cell> atlanticQ = new ArrayDeque<>();
        for (int i = matrix[0].length - 1; i >= 0; i--) {
            atlanticQ.offerLast(new Cell(matrix.length - 1, i));
        }
        for (int i = matrix.length - 2; i >= 0; i--) {
            atlanticQ.offerLast(new Cell(i, matrix[0].length - 1));
        }

        while (!atlanticQ.isEmpty()) {
            Cell cur = atlanticQ.pollFirst();
            if (mem[cur.i][cur.j] == 1 || mem[cur.i][cur.j] == -1) mem[cur.i][cur.j] = -1 ;
            else mem[cur.i][cur.j] = -2;
            int value = matrix[cur.i][cur.j];
            for (int[] dir : DIR) {
                int row = cur.i + dir[0];
                int col = cur.j + dir[1];
                if (row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length
                        && mem[row][col] >= 0 && matrix[row][col] >= value) {
                    atlanticQ.offerLast(new Cell(row, col));
                }
            }
        }
        printMatrix(mem);
        System.out.println("__________");

        // find mem where value is 2
        for (int i = 0; i < mem.length; i++) {
            for (int j = 0; j < mem[0].length; j++) {
                if (mem[i][j] == -1) {
                    List<Integer> index = new ArrayList<>();
                    index.add(i);
                    index.add(j);
                    res.add(index);
                }
            }
        }
        return res;
    }

    class Cell {
        public int i;
        public int j;
        public Cell(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    @Test
    public void test() {
        // System.out.println(this.pacificAtlantic(new int[][]{{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}}));
        System.out.println(this.pacificAtlantic(new int[][] {{1}}));
    }
}
