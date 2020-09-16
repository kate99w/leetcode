package xwang.march2020.secondBatch;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * 5/8/20
 * leetcode # 224. Basic Calculator
 * link: https://leetcode.com/problems/basic-calculator/
 * tags:
 * level: hard
 */
public class BasicCalculator {
    /*    Implement a basic calculator to evaluate a simple expression string.

    The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

            Example 1:

    Input: "1 + 1"
    Output: 2
    Example 2:

    Input: " 2-1 + 2 "
    Output: 3
    Example 3:

    Input: "(1+(4+5+2)-3)+(6+8)"
    Output: 23
    Note:
    You may assume that the given expression is always valid.
    Do not use the eval built-in library function.
    */
    public int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') continue;
            if (s.charAt(i) == ')') {
                int num = popAndCal(stack); // cal and remove the '(' from the stack
                pushNum(stack, num); // push cal value into stack, make sure all num is non-negative
            } else {
                stack.offerLast(s.charAt(i));
            }
        }
        return popAndCal(stack);
    }

    private int popAndCal(Deque<Character> stack) { //stack case 1: (1+(4+5+2 |top|      case 2: 4+5+2   case 3: -4+5
        int result = 0;
        while (!stack.isEmpty()) {
            if (stack.peekLast() == '(') {
                stack.pollLast();
                return result;
            }
            int num = popNum(stack);
            if (stack.isEmpty()) return result + num;
            char sign = stack.pollLast();
            if (sign == '-'){
                result -= num;
            } else {
                result += num;
                if (sign == '(') return result;
            }
        }
        return result;
    }

    private int popNum(Deque<Character> stack) {
        int sum = 0;
        int digit = 1;
        while (!stack.isEmpty()
                && stack.peekLast() - '0' >= 0 && stack.peekLast() -'0' <= 9) {
            sum += (stack.pollLast() - '0') * digit;
            digit *= 10;
        }
        return sum;
    }

    private void pushNum(Deque<Character> stack, int num) {
        if (num == 0) {
            if (!stack.isEmpty()) stack.pollLast();
            return;
        } else if (num < 0) {
            num = -num;
            if (stack.isEmpty()) {
                stack.offerLast('-');
            } else {
                char sign = stack.pollLast();
                if (sign == '-') {
                    stack.offerLast('+');
                } else {
                    stack.offerLast('-');
                }
            }
        }
        // cal digit of num
        // 123
        int shift = 1;
        int remin = num;
        while (remin >= 10) {
            remin = remin / 10; // 1
            shift *= 10; // 100
        } //shift: 100
        // 120, shift 100, 20 shift 10, 0 shift 1
        while (shift > 0) {
            char digit = (char) (num / shift + '0'); // 1, 2, 0
            stack.offerLast(digit);
            num %= shift; // 20, 0, 0
            shift /= 10; // 10, 1, 0
        }
    }

    @Test
    public void test() {
        System.out.println(this.calculate("(7)-(0)+(4)"));
    }
}
