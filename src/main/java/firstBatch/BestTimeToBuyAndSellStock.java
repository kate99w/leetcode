package firstBatch;

/*
 * 5/8/20
 * leetcode # 121. Best Time to Buy and Sell Stock
 *            122. Best Time to Buy and Sell Stock II
 *            123. Best Time to Buy and Sell Stock III
 *            188. Best Time to Buy and Sell Stock IV
 *            309. Best Time to Buy and Sell Stock with Cooldown
 * link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 *       https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
 *       https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 *       https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 *       https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * tags:
 * level: easy, medium, hard
 */
public class BestTimeToBuyAndSellStock {

    /* Say you have an array for which the ith element is the price of a given stock on day i.

    If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

    Note that you cannot sell a stock before you buy one.

            Example 1:

    Input: [7,1,5,3,6,4]
    Output: 5
    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
    Not 7-1 = 6, as selling price needs to be larger than buying price.
    Example 2:

    Input: [7,6,4,3,1]
    Output: 0
    Explanation: In this case, no transaction is done, i.e. max profit = 0.
    */

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int profit = 0;
        int cur = prices[0];
        for (int i : prices) {
            if (i > cur) profit = Math.max(profit, i - cur);
            else cur = i;
        }
        return profit;
    }


    public void test_i() {
        BestTimeToBuyAndSellStock b = new BestTimeToBuyAndSellStock();
        int res = b.maxProfit(new int[]{7,6,4,3,1});
        System.out.println(res);
    }

}
