package firstBatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * 5/8/20
 * leetcode # 56. Merge Intervals
 * link: https://leetcode.com/problems/merge-intervals/
 * tags:
 * level: medium
 */
public class MergeIntervals {
/*
    Given a collection of intervals, merge all overlapping intervals.

    Example 1:

    Input: [[1,3],[2,6],[8,10],[15,18]]
    Output: [[1,6],[8,10],[15,18]]
    Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

    Example 2:

    Input: [[1,4],[4,5]]
    Output: [[1,5]]
    Explanation: Intervals [1,4] and [4,5] are considered overlapping.

            NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
*/
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0) return intervals;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {
            if (merged.isEmpty()) {
                merged.add(interval);
            } else {
                int[] prev = merged.get(merged.size() - 1);
                if (interval[0] > prev[1]) {
                    merged.add(interval);
                } else if (interval[1] > prev[1]) {
                    prev[1] = interval[1];
                }
            }
        }

        return merged.toArray(new int[merged.size()][2]);
    }

}
