package xwang.march2020.firstBatch;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/*
 * 5/8/20
 * leetcode # 166. Fraction to Recurring Decimal
 * link: https://leetcode.com/problems/fraction-to-recurring-decimal/
 * tags:
 * level: medium
 */
public class FractionToRecurringDecimal {
/*    Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

    If the fractional part is repeating, enclose the repeating part in parentheses.

            Example 1:

    Input: numerator = 1, denominator = 2
    Output: "0.5"
    Example 2:

    Input: numerator = 2, denominator = 1
    Output: "2"
    Example 3:

    Input: numerator = 2, denominator = 3
    Output: "0.(6)"*/
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        Map<Long, Integer> map = new HashMap<>();
        // pre-processing
        if (!((numerator <= 0 && denominator < 0)
                || (numerator >= 0 && denominator > 0))) {
            sb.append("-");
        }
        long numerator1 = numerator < 0 ? -(long)numerator : numerator;
        long denominator1 = denominator < 0 ? -(long)denominator : denominator;
        sb.append(numerator1 / denominator1);

        long reminder = numerator1 % denominator1;
        if (reminder != 0) sb.append('.');

        while (reminder > 0) {
            if (map.containsKey(reminder)) {
                sb.insert(map.get(reminder), "(");
                sb.append(")");
                break;
            } else {
                map.put(reminder, sb.length());
            }
            reminder *= 10;
            sb.append(reminder / denominator1);
            reminder %= denominator1;
        }

        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(this.fractionToDecimal(-1,-2147483648));
    }
}
