package xwang.march2020.firstBatch;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/*
 * 5/7/20
 * leetcode # 22. Generate Parentheses
 * link: https://leetcode.com/problems/generate-parentheses/
 * tags:
 * level: medium
 */
public class GenerateParentheses {
/*    Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

    For example, given n = 3, a solution set is:

            [
            "((()))",
            "(()())",
            "(())()",
            "()(())",
            "()()()"
            ]*/
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(n, 0, 0, sb, res);
        return res;
    }
    private void dfs(int n, int left, int right, StringBuilder sb, List<String> res) {
        if (sb.length() == 2 * n) {
            res.add(new String(sb));
            return;
        }
        if (left < n) {
            dfs(n, left + 1, right, sb.append('('),res);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (right < left) {
            dfs(n, left, right + 1, sb.append(')'), res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    @Test
    public void test() {
        System.out.println(this.generateParenthesis(3));
    }
}
