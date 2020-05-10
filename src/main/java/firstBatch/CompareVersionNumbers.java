package firstBatch;

import org.junit.jupiter.api.Test;

/*
 * 5/8/20
 * leetcode # 165. Compare Version Numbers
 * link: https://leetcode.com/problems/compare-version-numbers/
 * tags:
 * level: medium
 */
public class CompareVersionNumbers {

/*    Compare two version numbers version1 and version2.

    If version1 > version2 return 1; if version1 < version2 return -1;otherwise return 0.

    You may assume that the version strings are non-empty and contain only digits and the . character.

    The . character does not represent a decimal point and is used to separate number sequences.

    For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

    You may assume the default revision number for each level of a version number to be 0. For example, version number 3.4 has a revision number of 3 and 4 for its first and second level revision number. Its third and fourth level revision number are both 0.

    Example 1:

    Input: version1 = "0.1", version2 = "1.1"
    Output: -1
    Example 2:

    Input: version1 = "1.0.1", version2 = "1"
    Output: 1
    Example 3:

    Input: version1 = "7.5.2.4", version2 = "7.5.3"
    Output: -1
    Example 4:

    Input: version1 = "1.01", version2 = "1.001"
    Output: 0
    Explanation: Ignoring leading zeroes, both “01” and “001" represent the same number “1”
    Example 5:

    Input: version1 = "1.0", version2 = "1.0.0"
    Output: 0
    Explanation: The first version number does not have a third level revision number, which means its third level revision number is default to "0"

    Note:

    Version strings are composed of numeric strings separated by dots . and this numeric strings may have leading zeroes.
    Version strings do not start or end with dots, and they will not be two consecutive dots.*/


    public int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null) return 0;
        int v1 = 0, v2 = 0;
        while (v1 < version1.length() && v2 < version2.length()) {
            // parse v1
            int e1 = v1;
            while (e1 < version1.length() && version1.charAt(e1) != '.') e1++;
            int num1 = parse(version1, v1, e1);
            // parse v2
            int e2 = v2;
            while (e2 < version2.length() && version2.charAt(e2) != '.') e2++;
            int num2 = parse(version2, v2, e2);
            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }
            v1 = e1 + 1;
            v2 = e2 + 1;
        }

        while (v1 < version1.length()) {
            int e1 = v1;
            while (e1 < version1.length() && version1.charAt(e1) != '.') e1++;
            int num1 = parse(version1, v1, e1);
            if (num1 > 0) return 1;
            v1 = e1 + 1;
        }
        while (v2 < version2.length()) {
            int e2 = v2;
            while (e2 < version2.length() && version2.charAt(e2) != '.') e2++;
            int num2 = parse(version2, v2, e2);
            if (num2 > 0) return -1;
            v2 = e2 + 1;
        }

        return 0;
    }

    private int parse(String s, int start, int end) { // end = '.' or end == length()
        int num = 0;
        int digit = 1;
        end--;
        while (end >= start) {
            num += (s.charAt(end) - '0') * digit;
            digit *= 10;
            end--;
        }
        return num;
    }

    @Test
    public void test() {
        System.out.println(this.compareVersion("1", "1.0.2"));
    }
}
