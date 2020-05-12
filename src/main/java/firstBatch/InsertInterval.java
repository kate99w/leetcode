package firstBatch;

/*
 * 5/8/20
 * leetcode # 57. Insert Interval
 * link: https://leetcode.com/problems/insert-interval/
 * tags:
 * level: hard
 */
public class InsertInterval {
/*    Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

    You may assume that the intervals were initially sorted according to their start times.

    Example 1:

    Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
    Output: [[1,5],[6,9]]
    Example 2:

    Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
    Output: [[1,2],[3,10],[12,16]]
    Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
    NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.*/

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] res = new int[intervals.length + 1][2];
        int pos = 0;
        boolean inserted = false;
        for (int[] ints : intervals) {
            if (!inserted) {
                if (newInterval[1] < ints[0]) {

                }
            }
        }
    }
}
