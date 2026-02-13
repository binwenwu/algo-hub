import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _56 {
    public static void main(String[] args) {

    }

    public int[][] merge1(int[][] intervals) {
        if (intervals.length == 1) {
            return intervals;
        }
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });

        List<int[]> result = new ArrayList<>();

        result.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= result.get(result.size() - 1)[1]) {
                result.get(result.size() - 1)[1] = Math.max(result.get(result.size() - 1)[1], intervals[i][1]);
            } else {
                result.add(intervals[i]);
            }
        }

        return result.toArray(new int[result.size()][]);
    }
}
