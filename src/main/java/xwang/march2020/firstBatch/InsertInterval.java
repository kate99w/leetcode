package xwang.march2020.firstBatch;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static utils.PrintUtils.printMatrix;

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
        List<int[]> list = new ArrayList<>();
        boolean inserted = false;
        for (int[] ints : intervals) {
            if (!inserted) {
                if (newInterval[1] < ints[0]) { // case 1: [new] [ints]
                    list.add(newInterval);
                    list.add(ints);
                    inserted = true;
                } else if (newInterval[0] > ints[1]) { // case 6: [ints] [new]
                    list.add(ints);
                } else if (newInterval[0] <= ints[0] && newInterval[1] <= ints[1]) { // case 2: [new .[..].ints]
                    newInterval[1] = ints[1];
                } else if (ints[0] <= newInterval[0] && ints[1] <= newInterval[1]) {  // case 5: [ints..[.]...new]
                    newInterval[0] = ints[0];
                } else if (newInterval[0] >= ints[0] && newInterval[1] <= ints[1]) { // case 3: [ints...[new]...]
                    newInterval[0] = ints[0];
                    newInterval[1] = ints[1];
                } // else case 4: [new...[ints]...]
            } else {
                list.add(ints);
            }
        }
        if (!inserted) list.add(newInterval);
        int[][] res = new int[list.size()][2];
        int n = 0;
        for (int[] i : list) {
            res[n++] = i;
        }
        return res;
    }

    @Test
    public void test() {
        printMatrix(this.insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[]{4,8}));
    }
}
