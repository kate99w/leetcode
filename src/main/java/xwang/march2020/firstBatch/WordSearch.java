package xwang.march2020.firstBatch;

/*
 * 5/8/20
 * leetcode # 79. Word Search
 *            212. Word Search II
 * link: https://leetcode.com/problems/word-search/
 *       https://leetcode.com/problems/word-search-ii/
 * tags:
 * level: medium
 */
public class WordSearch {
    /*    Given a 2D board and a word, find if the word exists in the grid.

    The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

            Example:

    board =
            [
            ['A','B','C','E'],
            ['S','F','C','S'],
            ['A','D','E','E']
            ]

    Given word = "ABCCED", return true.
    Given word = "SEE", return true.
    Given word = "ABCB", return false.

    */
    private final int[][] DIR = new int[][] {{1, 0},{0, 1}, {-1, 0}, {0, -1}};

    public boolean exist(char[][] board, String word) {
        if (word == null) return false;
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j] == word.charAt(0))
                    if (dfs(board, word, 0, i, j, visited)) return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int level, int row, int col, boolean[][] visited) {
        if (level >= word.length()) return true;
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || visited[row][col]) return false;
        visited[row][col] = true;
        boolean result = false;
        if (board[row][col] == word.charAt(level)) {
            for (int[] dir : DIR) {
                if (dfs(board, word, level + 1, row + dir[0], col + dir[1], visited)) {
                    result = true;
                    break;
                }
            }
        }
        visited[row][col] = false;
        return result;
    }

/*    Given a 2D board and a list of words from the dictionary, find all words in the board.

    Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.



    Example:

    Input:
    board = [
            ['o','a','a','n'],
            ['e','t','a','e'],
            ['i','h','k','r'],
            ['i','f','l','v']
            ]
    words = ["oath","pea","eat","rain"]

    Output: ["eat","oath"]


    Note:

    All inputs are consist of lowercase letters a-z.
    The values of words are distinct.




    */
}
