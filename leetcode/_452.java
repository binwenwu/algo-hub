import java.util.Arrays;

public class _452 {
    public static void main(String[] args) {
        _452 s = new _452();
        int[][] points = { { 1, 2 }, { 2, 3 }, { 3, 4 }, { 4, 5 } };
        int minArrowShots = s.findMinArrowShots1(points);
        System.out.println(minArrowShots);

    }

    /**
     * 贪心
     * 
     * 按右端点排序
     * 每次优先射最早结束的气球
     */
    public int findMinArrowShots1(int[][] points) {
        // 如果气球数组为空或长度为0，不需要箭
        if (points.length == 1) {
            return 1;
        }

        // 核心步骤1：根据气球的结束坐标 xend 对数组进行排序
        // 使用 Integer.compare 来防止 a[1] - b[1] 可能导致的整数溢出
        Arrays.sort(points, (a, b) -> {
            return Integer.compare(a[1], b[1]);
        });

        // 初始化箭的数量为1，因为至少需要一支箭来射第一个气球
        int arrowCount = 1;
        // 第一支箭的位置，设置为第一个气球（排序后）的结束坐标
        int arrowPos = points[0][1];

        // 核心步骤2：遍历排序后的气球
        for (int i = 1; i < points.length; i++) {
            // 如果当前气球的开始坐标 > 上一支箭的位置
            // 说明上一支箭无法射中当前气球
            if (points[i][0] > arrowPos) {
                // 我们需要一支新的箭
                arrowCount++;
                // 更新箭的位置为当前气球的结束坐标
                arrowPos = points[i][1];
            }
            // 如果 points[i][0] <= arrowPos，说明当前气球可以被上一支箭射穿，
            // 我们什么也不用做，继续使用旧的 arrowPos，因为它更靠左，约束更强。
        }

        return arrowCount;
    }

}
