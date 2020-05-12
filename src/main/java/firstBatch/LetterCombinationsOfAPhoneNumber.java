package firstBatch;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/*
 * 5/7/20
 * leetcode # 17. Letter Combinations of a Phone Number
 * link: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * tags:
 * level: medium
 */
public class LetterCombinationsOfAPhoneNumber {
/*    Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

    A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



    Example:

    Input: "23"
    Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
    Note:

    Although the above answer is in lexicographical order, your answer could be in any order you want.*/

    private final String[] TELE = new String[] {" ", "", "abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        StringBuilder comb = new StringBuilder();
        dfs(digits, 0, comb, res);
        return res;
    }
    private void dfs(String digits, int level, StringBuilder comb, List<String> res) {
        if (level >= digits.length()) {
            res.add(new String(comb));
            return;
        }
        String nums = TELE[digits.charAt(level) - '0'];
        for (int i = 0; i < nums.length(); i++) {
            comb.append(nums.charAt(i));
            dfs(digits, level + 1, comb, res);
            comb.deleteCharAt(comb.length() - 1);
        }
    }

    @Test
    public void test() {
        System.out.println(this.letterCombinations("23"));
    }
}
