package xwang.march2020.firstBatch;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * 5/8/20
 * leetcode # 150. Evaluate Reverse Polish Notation
 * link: https://leetcode.com/problems/evaluate-reverse-polish-notation/
 * tags:
 * level: medium
 */
public class EvaluateReversePolishNotation {
/*    Evaluate the value of an arithmetic expression in Reverse Polish Notation.

    Valid operators are +, -, *, /. Each operand may be an integer or another expression.

            Note:

    Division between two integers should truncate toward zero.
    The given RPN expression is always valid. That means the expression would always evaluate to a result and there won't be any divide by zero operation.
    Example 1:

    Input: ["2", "1", "+", "3", "*"]
    Output: 9
    Explanation: ((2 + 1) * 3) = 9
    Example 2:

    Input: ["4", "13", "5", "/", "+"]
    Output: 6
    Explanation: (4 + (13 / 5)) = 6
    Example 3:

    Input: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
    Output: 22
    Explanation:
            ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
            = ((10 * (6 / (12 * -11))) + 17) + 5
            = ((10 * (6 / -132)) + 17) + 5
            = ((10 * 0) + 17) + 5
            = (0 + 17) + 5
            = 17 + 5
            = 22*/
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (String s : tokens) {
            Integer i = isNumber(s);
            if (i != null) {
                stack.offerLast(i);
            } else {
                stack.offerLast(calNumber(s, stack.pollLast(), stack.pollLast()));
            }
        }
        return stack.pollLast();
    }

    private Integer isNumber(String s) {
        if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) {
            return null;
        } else {
            int num = 0;
            int digit = 1;
            for (int i = s.length() - 1; i > 0; i--) {
                num += (s.charAt(i) - '0') * digit;
                digit *= 10;
            }
            if (s.charAt(0) == '-') {
                num = 0- num;
            } else {
                num += (s.charAt(0) - '0') * digit;
            }
            return num;
        }
    }
    private Integer calNumber(String s, Integer i, Integer j) {
        switch (s.charAt(0)) {
            case '*' :
                return i * j;
            case '/' :
                return j / i;
            case '+' :
                return i + j;
            case '-' :
                return j - i;
        }
        return null;
    }

    @Test
    public void test() {
        System.out.println(
                this.evalRPN(
                        new String[] {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }
}
