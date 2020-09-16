package xwang.march2020.firstBatch;

import org.junit.jupiter.api.Test;

/*
 * 5/8/20
 * leetcode # 135. xwang.march2020.firstBatch.Candy
 * link: https://leetcode.com/problems/candy/
 * tags:
 * level: hard
 */
public class Candy {
/*    There are N children standing in a line. Each child is assigned a rating value.

    You are giving candies to these children subjected to the following requirements:

    Each child must have at least one candy.
    Children with a higher rating get more candies than their neighbors.
    What is the minimum candies you must give?

    Example 1:

    Input: [1,0,2]
    Output: 5
    Explanation: You can allocate to the first, second and third child with 2, 1, 2 candies respectively.
    Example 2:

    Input: [1,2,2]
    Output: 4
    Explanation: You can allocate to the first, second and third child with 1, 2, 1 candies respectively.
    The third child gets 1 candy because it satisfies the above two conditions.*/
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;

        int prevEffective = 0; // if prev increase by 1, how many need to follow to increase
        int prevCandy = 1; // the amount of candies the prev has
        int totalCandy = ratings.length;

        for (int i = 1; i < ratings.length; i++) {
            //[1,2,87,87,87,2,1]
            //[1,2,3, 1, 1, 1, 1]
            if (ratings[i] < ratings[i-1]) { // when cur rating is not greater then the prev
                if (prevCandy > 1) { // check if prev's candy is already greater then cur
                    int diff = prevCandy - 1; // if cur increase diff times then it will effect prev, now is safe
                    int safeIncrease = diff - 1;
                    // update prevEff
                    prevEffective = prevEffective + 1  - safeIncrease; // prevEffective + cur - safeIncrease
                } else { // need to increase the prev and its effective for sure
                    totalCandy += prevEffective + 1; // increase prev by 1 and all its effective by 1
                    prevEffective = prevEffective + 1;
                }
                prevCandy = 1;
            } else if (ratings[i] > ratings[i-1]) { // when cur rating is greater then the prev
                prevEffective = 0;
                int curCandy = prevCandy + 1;
                totalCandy += prevCandy;
                prevCandy = curCandy;
            } else { // when cur rating is equal then the prev
                prevEffective = 0;
                prevCandy = 1;
            }
        }
        return totalCandy;
    }

    @Test
    public void test() {
        System.out.println(this.candy(new int[] {1,2,87,87,87,2,1}));
    }
}
