import java.util.ArrayList;
import java.util.List;

public class _57 {

    /**
     * ① 先加入所有“在左边且不重叠”的区间
     * ② 合并所有“与新区间重叠”的区间
     * ③ 加入剩余“在右边”的区间
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();

        int i = 0;
        int n = intervals.length;

        // 1. 左边不重叠
        while (i < n && intervals[i][1] < newInterval[0]) {
            res.add(intervals[i]);
            i++;
        }

        // 2. 合并重叠区间
        /**
         * 当上一个条件不满足退出循环时，这里确认区间重叠还需要加条件，
         * 因为如果intervals[i][0] > newInterval[1]，
         * 说明 newInterval 区间是夹在 intervals[i-1] 和 intervals[i] 之间的，是不重叠的
         */
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        res.add(newInterval);

        // 3. 右边不重叠
        while (i < n) {
            res.add(intervals[i]);
            i++;
        }

        return res.toArray(new int[res.size()][]);
    }
}
