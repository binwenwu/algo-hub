import java.util.Arrays;

public class _435 {
    public static void main(String[] args) {

    }

    /**
     * 我来按照右边界排序，从左向右记录非交叉区间的个数。
     * 最后用区间总数减去非交叉区间的个数就是需要移除的区间个数了。
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 1){
            return 0;
        }

        Arrays.sort(intervals, (a, b) -> {
            return a[1] - b[1];
        });

        int end = intervals[0][1];
        int count = 1;

        for (int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] >= end){
                end = intervals[i][1];
                count++;
            }
        }

        return intervals.length - count;
    }
}
